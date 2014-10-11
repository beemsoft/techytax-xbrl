package org.techytax.service;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

public class MobileServlet extends CXFNonSpringJaxrsServlet {
	
	private static final long serialVersionUID = 1442269819442347710L;

	@Override
	public void loadBus(ServletConfig servletConfig)
	{
		super.loadBus(servletConfig);
		Bus bus = getBus();
		BusFactory.setDefaultBus(bus);

//		Endpoint.publish("/service", new MobileServiceImpl());

		bus.getInInterceptors().add(new BasicAuthenticationInterceptor());
		bus.getInInterceptors().add(new LoggingInInterceptor());
		bus.getOutInterceptors().add(new LoggingOutInterceptor());

	}	

}
