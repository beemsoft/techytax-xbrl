/* SelectorsExt.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Apr 20, 2012 11:44:27 AM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zkmax.ui.select;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.impl.Reflections;
import org.zkoss.zk.ui.select.impl.Reflections.MethodRunner;
import org.zkoss.zk.ui.util.UiLifeCycle;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;

/**
 * Extended utilities for {@link org.zkoss.zk.ui.select.Selectors} and 
 * {@link org.zkoss.zk.ui.select.SelectorComposer}.
 * @author simonpai
 * @since 6.0.1
 */
public class SelectorsExt {
	
	/** Subscribe member methods with {@link Subscribe} annotation in the controller object.
	 * @since 6.0.1
	 */
	public static Object subscribeEventQueues(final Object controller) {
		return subscribeEventQueues(controller, false);
	}
	
	/*package*/ static Object resubscribeEventQueues(final Object controller) {
		return subscribeEventQueues(controller, true);
	}
	
	private static List<SubscriptionInfo> subscribeEventQueues(final Object controller, final boolean rewire) {
		final List<SubscriptionInfo> subsInfo = new ArrayList<SubscriptionInfo>();
		Reflections.forMethods(controller.getClass(), Subscribe.class, 
				new MethodRunner<Subscribe>() {
			public void onMethod(Class<?> clazz, Method method, Subscribe anno) {
				// check method signature
				if ((method.getModifiers() & Modifier.STATIC) != 0) 
					throw new UiException("Cannot add forward to static method: " + 
							method.getName());

				EventQueue<Event> eq = EventQueues.lookup(anno.value(), anno.scope(), true);
				//zk - 1446, NotSerializableException when using @Subscribe
				SubscribeMethodEventListener l = new SubscribeMethodEventListener(method, controller, anno.eventName());
				if(!rewire || !eq.isSubscribed(l)){
					eq.subscribe(l);
					subsInfo.add(new SubscriptionInfo(anno.value(),anno.scope(),anno.autoUnsubscribe(),l));
				}
			}
		});
		return subsInfo.size() > 0 ? subsInfo : null;
	}

	/*package*/ @SuppressWarnings("unchecked")
	static void postSubscriptionHandling(Object subsInfo,Object target) {
		
		if(!(subsInfo instanceof List) || !(target instanceof Component) || ((Component)target).getDesktop()==null){
			return;
		}
		for(SubscriptionInfo si:(List<SubscriptionInfo>)subsInfo){
			if(si.isAutoUnsubscribe()){
				((Component)target).getDesktop().addListener(new UnsubscribeListener(((Component)target),(List<SubscriptionInfo>)subsInfo));
				//just add once if there is one set autoSubscribe
				break;
			}
		}
	}
	
	//zk-1438-auto-unsubscribe
	private static class UnsubscribeListener implements Serializable,UiLifeCycle{
		private static final long serialVersionUID = 1L;
		
		private final List<SubscriptionInfo> _subsInfo;
		private final Component _self;
		
		public UnsubscribeListener(Component _self,List<SubscriptionInfo> _subsinfo) {
			this._subsInfo = _subsinfo;
			this._self = _self;
		}
		
		public void afterComponentAttached(Component comp, Page page) {}
		
		public void afterComponentDetached(Component comp, Page prevpage) {
			if(_self == comp || Components.isAncestor(comp, _self)){
				//remove queue
				if(_subsInfo!=null && _subsInfo.size()>0){
					for(SubscriptionInfo si:_subsInfo){
						if(!si.isAutoUnsubscribe()) continue;
						
						EventQueue<Event> eq = EventQueues.lookup(si.getQueueName(),si.getScope(),false);
						if(eq!=null){
							eq.unsubscribe(si.getListener());
						}
					}
				}
				prevpage.getDesktop().removeListener(this);
			}
		}
		
		public void afterComponentMoved(Component parent, Component child, Component prevparent) {}
		
		public void afterPageAttached(Page page, Desktop desktop) {}
		
		public void afterPageDetached(Page page, Desktop prevdesktop) {}
		
	}
	
	//zk-1438-auto-unsubscribe
	private static class SubscriptionInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		private EventListener<Event> _listener;
		private String _qname;
		private String _scope;
		private boolean _autoUnsubscribe;

		public SubscriptionInfo(String qname, String scope, boolean autoUnsubscribe, EventListener<Event> listener) {
			this._listener = listener;
			this._qname = qname;
			this._scope = scope;
			this._autoUnsubscribe = autoUnsubscribe;
		}

		public EventListener<Event> getListener() {
			return _listener;
		}

		public String getQueueName() {
			return _qname;
		}

		public String getScope() {
			return _scope;
		}
		
		public boolean isAutoUnsubscribe() {
			return _autoUnsubscribe;
		}
	}
	
	//zk - 1446, NotSerializableException when using @Subscribe
	private static class SubscribeMethodEventListener implements EventListener<Event>,Serializable {
		private static final long serialVersionUID = 1L;
		private transient Method _ctrlMethod;
		private transient boolean _noMethodSet = false; 
		private final Object _ctrl;
		private final String _sign;
		private final String _eventName;


		public SubscribeMethodEventListener(Method method, Object controller, String eventName) {
			_ctrlMethod = method;
			_ctrl = controller;
			_sign = toSignature(method);
			_eventName = eventName;
		}
		
		private static String toSignature(Method m){
			return m.toString();
		}
		
		private Method getCtrlMethodIfAny(){
			try{
				return getCtrlMethod();
			}catch(NoSuchMethodError x){
				return null;
			}
		}
		
		private Method getCtrlMethod(){
			if(_ctrlMethod!=null) return _ctrlMethod;
			if(_noMethodSet) throw new NoSuchMethodError(_sign);//no need to check again
			
			synchronized(_sign){
				Reflections.forMethods(_ctrl.getClass(), Subscribe.class, 
						new MethodRunner<Subscribe>() {
					public void onMethod(Class<?> clazz, Method method, Subscribe anno) {
						if(_ctrlMethod==null && _sign.equals(toSignature(method))){
							_ctrlMethod = method;
						}
					}
				});
				if(_ctrlMethod==null){
					_noMethodSet = true;
					throw new NoSuchMethodError(_sign);
				}
			}
			return _ctrlMethod;
		}
		
		
		public void onEvent(Event event) throws Exception {
			if(!_eventName.equals("") && !_eventName.equals(event.getName()))
				return;
			
			Method m = getCtrlMethod();
			Class<?>[] types = m.getParameterTypes();
			if (types.length == 0)
				m.invoke(_ctrl);
			else {
				List<Object> params = new ArrayList<Object>(types.length);
				
//				if(types[0].equals(Event.class))
					params.add(event);
				
//				if(params.size() != types.length)
//					params.addAll(Arrays.asList((Object[]) event.getData()));
				
				m.invoke(_ctrl, params.toArray());
			}
		}
				
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((_ctrl == null) ? 0 : _ctrl.hashCode());
			result = prime * result + ((getCtrlMethodIfAny() == null) ? 0 : getCtrlMethodIfAny().hashCode());
			
			return result;
		}
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SubscribeMethodEventListener other = (SubscribeMethodEventListener) obj;
			if (_ctrl == null) {
				if (other._ctrl != null)
					return false;
			} else if (!_ctrl.equals(other._ctrl))
				return false;
			if (getCtrlMethodIfAny() == null) {
				if (other.getCtrlMethodIfAny() != null)
					return false;
			} else if (!getCtrlMethodIfAny().equals(other.getCtrlMethodIfAny()))
				return false;
			return true;
		}
	}
	
	/** Extended utility implementation for {@link org.zkoss.zk.ui.select.SelectorComposer}.
	 * @author simonpai
	 * @since 6.0.1
	 */
	public static class ExtendedUtilityHandlerImpl extends 
			SelectorComposer.UtilityHandlerImpl {
		private static final long serialVersionUID = 1L;
				
		public Object subscribeEventQueues(Object controller) {
			return SelectorsExt.subscribeEventQueues(controller);
		}
		
		public Object resubscribeEventQueues(Object controller) {
			return SelectorsExt.resubscribeEventQueues(controller);
		}
		
		public void postSubscriptionHandling(Object subsInfo,Object target) {
			SelectorsExt.postSubscriptionHandling(subsInfo,target);
		}
	}
	
}
