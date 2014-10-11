package org.techytax.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.AbstractInDatabindingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.security.AuthenticationException;
import org.techytax.security.SecurityService;
import org.techytax.security.SecurityServiceImpl;
import org.techytax.zk.login.Sha;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

public class BasicAuthenticationInterceptor extends AbstractInDatabindingInterceptor
{
	public BasicAuthenticationInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault
	{
		// This is set by CXF
		AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
		// If the policy is not set, the user did not specify
		// credentials. A 401 is sent to the client to indicate
		// that authentication is required
		if (policy == null)
		{
			sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			return;
		}

		// Verify the password
		SecurityService securityService = new SecurityServiceImpl();
		User user = null;
		try {
			 user = securityService.authenticateForService(policy.getUserName(), policy.getPassword());
		} catch (Exception e) {
			Messagebox.show(e.getMessage(), null, new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.EXCLAMATION, null);
		}		

			if (user == null)
			{
				sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
			} else {
				EntityManagerHelper.setUser(user);
			}
	}

	@SuppressWarnings("unchecked")
	private void sendErrorResponse(Message message, int responseCode)
	{
		Message outMessage = getOutMessage(message);
		outMessage.put(Message.RESPONSE_CODE, responseCode);
		// Set the response headers
		Map<String, List<String>> responseHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		if (responseHeaders != null)
		{
			responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[] { "Basic realm=realm" }));
			responseHeaders.put("Content-length", Arrays.asList(new String[] { "0" }));
		}
		message.getInterceptorChain().abort();
		try
		{
			getConduit(message).prepare(outMessage);
			close(outMessage);
		}
		catch (IOException e)
		{
		}
	}

	private Message getOutMessage(Message inMessage)
	{
		Exchange exchange = inMessage.getExchange();
		Message outMessage = exchange.getOutMessage();
		if (outMessage == null)
		{
			Endpoint endpoint = exchange.get(Endpoint.class);
			outMessage = endpoint.getBinding().createMessage();
			exchange.setOutMessage(outMessage);
		}
		outMessage.putAll(inMessage);
		return outMessage;
	}

	private Conduit getConduit(Message inMessage) throws IOException
	{
		Exchange exchange = inMessage.getExchange();
		EndpointReferenceType target = exchange.get(EndpointReferenceType.class);
		Conduit conduit = exchange.getDestination().getBackChannel(inMessage, null, target);
		exchange.setConduit(conduit);
		return conduit;
	}

	private void close(Message outMessage) throws IOException
	{
		OutputStream os = outMessage.getContent(OutputStream.class);
		os.flush();
		os.close();
	}


}
