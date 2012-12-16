/**
 * AanleverService_V1_2Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package nl.logius.digipoort.wus._2_0.aanleverservice._1_2;

/*
 *  AanleverService_V1_2Stub java implementation
 */

public class AanleverService_V1_2Stub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return java.lang.Long.toString(java.lang.System.currentTimeMillis())
				+ "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService(
				"AanleverService_V1_2" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/",
				"aanleveren"));
		_service.addOperation(__operation);

		(__operation)
				.getMessage(
						org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_OUT_VALUE)
				.getPolicySubject()
				.attachPolicy(
						getPolicy("<wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><wsp:ExactlyOne><wsp:All><sp:SignedParts>\n					<sp:Body />\n					<sp:Header Name=\"To\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"From\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"FaultTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"ReplyTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"MessageID\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"RelatesTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"Action\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n				</sp:SignedParts><sp:SignedElements>\n					<sp:XPath>/*[namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/' and local-name()='Envelope']/*[namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/' and local-name()='Header']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' and local-name()='Security']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' and local-name()='Timestamp']</sp:XPath>\n					<sp:XPath>/*[namespace-uri()='http://www.w3.org/2003/05/soap-envelope' and local-name()='Envelope']/*[namespace-uri()='http://www.w3.org/2003/05/soap-envelope' and local-name()='Header']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' and local-name()='Security']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' and local-name()='Timestamp']</sp:XPath>\n				</sp:SignedElements><sp:AsymmetricBinding xmlns:sp=\"http://docs.oasis-open.org/ws-sx/ws-securitypolicy/200702\"><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:InitiatorToken><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:X509Token><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:WssX509V3Token10 /></wsp:Policy></sp:X509Token></wsp:Policy></sp:InitiatorToken><sp:RecipientToken><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:X509Token><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:WssX509V3Token10 /></wsp:Policy></sp:X509Token></wsp:Policy></sp:RecipientToken><sp:AlgorithmSuite><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:Basic128Rsa15 /><sp:Basic256Rsa15 /><sp:TripleDesRsa15 /></wsp:Policy></sp:AlgorithmSuite><sp:IncludeTimestamp /><sp:EncryptBeforeSigning /><sp:OnlySignEntireHeadersAndBody /></wsp:Policy></sp:AsymmetricBinding><wsam:Addressing xmlns:wsam=\"http://www.w3.org/2007/05/addressing/metadata\"><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><wsp:ExactlyOne><wsp:All><wsam:Anonymous>required</wsam:Anonymous></wsp:All><wsp:All><AnonymousResponses xmlns=\"\"></AnonymousResponses></wsp:All></wsp:ExactlyOne></wsp:Policy></wsam:Addressing><wsoma:OptimizedMimeSerialization wsp:Optional=\"true\" xmlns:wsoma=\"http://schemas.xmlsoap.org/ws/2004/09/policy/optimizedmimeserialization\" /></wsp:All></wsp:ExactlyOne></wsp:Policy>"));

		(__operation)
				.getMessage(
						org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE)
				.getPolicySubject()
				.attachPolicy(
						getPolicy("<wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><wsp:ExactlyOne><wsp:All><sp:SignedParts>\n					<sp:Body />\n					<sp:Header Name=\"To\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"From\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"FaultTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"ReplyTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"MessageID\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"RelatesTo\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n					<sp:Header Name=\"Action\" Namespace=\"http://www.w3.org/2005/08/addressing\" />\n				</sp:SignedParts><sp:SignedElements>\n					<sp:XPath>/*[namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/' and local-name()='Envelope']/*[namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/' and local-name()='Header']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' and local-name()='Security']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' and local-name()='Timestamp']</sp:XPath>\n					<sp:XPath>/*[namespace-uri()='http://www.w3.org/2003/05/soap-envelope' and local-name()='Envelope']/*[namespace-uri()='http://www.w3.org/2003/05/soap-envelope' and local-name()='Header']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' and local-name()='Security']/*[namespace-uri()='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' and local-name()='Timestamp']</sp:XPath>\n				</sp:SignedElements><sp:AsymmetricBinding xmlns:sp=\"http://docs.oasis-open.org/ws-sx/ws-securitypolicy/200702\"><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:InitiatorToken><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:X509Token><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:WssX509V3Token10 /></wsp:Policy></sp:X509Token></wsp:Policy></sp:InitiatorToken><sp:RecipientToken><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:X509Token><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:WssX509V3Token10 /></wsp:Policy></sp:X509Token></wsp:Policy></sp:RecipientToken><sp:AlgorithmSuite><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><sp:Basic128Rsa15 /><sp:Basic256Rsa15 /><sp:TripleDesRsa15 /></wsp:Policy></sp:AlgorithmSuite><sp:IncludeTimestamp /><sp:EncryptBeforeSigning /><sp:OnlySignEntireHeadersAndBody /></wsp:Policy></sp:AsymmetricBinding><wsam:Addressing xmlns:wsam=\"http://www.w3.org/2007/05/addressing/metadata\"><wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><wsp:ExactlyOne><wsp:All><wsam:Anonymous>required</wsam:Anonymous></wsp:All><wsp:All><AnonymousResponses xmlns=\"\"></AnonymousResponses></wsp:All></wsp:ExactlyOne></wsp:Policy></wsam:Addressing><wsoma:OptimizedMimeSerialization wsp:Optional=\"true\" xmlns:wsoma=\"http://schemas.xmlsoap.org/ws/2004/09/policy/optimizedmimeserialization\" /></wsp:All></wsp:ExactlyOne></wsp:Policy>"));

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

		faultExceptionNameMap
				.put(new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName(
								"http://logius.nl/digipoort/koppelvlakservices/1.2/",
								"aanleverFault"), "aanleveren"),
						"nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault");
		faultExceptionClassNameMap
				.put(new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName(
								"http://logius.nl/digipoort/koppelvlakservices/1.2/",
								"aanleverFault"), "aanleveren"),
						"nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault");
		faultMessageMap
				.put(new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName(
								"http://logius.nl/digipoort/koppelvlakservices/1.2/",
								"aanleverFault"), "aanleveren"),
						"nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument");

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public AanleverService_V1_2Stub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public AanleverService_V1_2Stub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener)
			throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(
				configurationContext, _service);

		_service.applyPolicy();

		_serviceClient.getOptions().setTo(
				new org.apache.axis2.addressing.EndpointReference(
						targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public AanleverService_V1_2Stub(
			org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext,
				"https://preprod.procesinfrastructuur.nl/wus/2.0/aanleverservice/1.2");

	}

	/**
	 * Default Constructor
	 */
	public AanleverService_V1_2Stub() throws org.apache.axis2.AxisFault {

		this(
				"https://preprod.procesinfrastructuur.nl/wus/2.0/aanleverservice/1.2");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public AanleverService_V1_2Stub(java.lang.String targetEndpoint)
			throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverService_V1_2#aanleveren
	 * @param aanleverRequest0
	 * 
	 * @throws nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault
	 *             :
	 */

	public nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument aanleveren(

			nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument aanleverRequest0)

	throws java.rmi.RemoteException

	, nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient
					.getOptions()
					.setAction(
							"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/AanleverService/aanleverenRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions()
							.getSoapVersionURI()),
					aanleverRequest0,
					optimizeContent(new javax.xml.namespace.QName(
							"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/",
							"aanleveren")),
					new javax.xml.namespace.QName(
							"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/",
							"aanleveren"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.class,
					getEnvelopeNamespaces(_returnEnv));

			return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(
								faultElt.getQName(), "aanleveren"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(
										faultElt.getQName(), "aanleveren"));
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor
								.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(
										faultElt.getQName(), "aanleveren"));
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault) {
							throw (nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender()
						.cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverService_V1_2#startaanleveren
	 * @param aanleverRequest0
	 */
	public void startaanleveren(

			nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument aanleverRequest0,

			final nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverService_V1_2CallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[0].getName());
		_operationClient
				.getOptions()
				.setAction(
						"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/AanleverService/aanleverenRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(
				getFactory(_operationClient.getOptions().getSoapVersionURI()),
				aanleverRequest0,
				optimizeContent(new javax.xml.namespace.QName(
						"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/",
						"aanleveren")),
				new javax.xml.namespace.QName(
						"http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/",
						"aanleveren"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.class,
									getEnvelopeNamespaces(resultEnv));
							callback.receiveResultaanleveren((nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErroraanleveren(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap
										.containsKey(new org.apache.axis2.client.FaultMapKey(
												faultElt.getQName(),
												"aanleveren"))) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(new org.apache.axis2.client.FaultMapKey(
														faultElt.getQName(),
														"aanleveren"));
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.reflect.Constructor constructor = exceptionClass
												.getConstructor(String.class);
										java.lang.Exception ex = (java.lang.Exception) constructor
												.newInstance(f.getMessage());
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(new org.apache.axis2.client.FaultMapKey(
														faultElt.getQName(),
														"aanleveren"));
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m.invoke(
												ex,
												new java.lang.Object[] { messageObject });

										if (ex instanceof nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault) {
											callback.receiveErroraanleveren((nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault) ex);
											return;
										}

										callback.receiveErroraanleveren(new java.rmi.RemoteException(
												ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErroraanleveren(f);
									}
								} else {
									callback.receiveErroraanleveren(f);
								}
							} else {
								callback.receiveErroraanleveren(f);
							}
						} else {
							callback.receiveErroraanleveren(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErroraanleveren(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(
			org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator
					.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	// //////////////////////////////////////////////////////////////////////

	private static org.apache.neethi.Policy getPolicy(
			java.lang.String policyString) {
		return org.apache.neethi.PolicyEngine
				.getPolicy(org.apache.axiom.om.OMXMLBuilderFactory
						.createOMBuilder(new java.io.StringReader(policyString))
						.getDocument().getXMLStreamReader(false));
	}

	// ///////////////////////////////////////////////////////////////////////

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// https://preprod.procesinfrastructuur.nl/wus/2.0/aanleverservice/1.2

	private org.apache.axiom.om.OMElement toOM(
			nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		return toOM(param);

	}

	private org.apache.axiom.om.OMElement toOM(
			final nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument param)
			throws org.apache.axis2.AxisFault {

		org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
		xmlOptions.setSaveNoXmlDecl();
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSaveNamespacesFirst();
		org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory
				.createOMBuilder(new javax.xml.transform.sax.SAXSource(
						new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param,
								xmlOptions), new org.xml.sax.InputSource()));
		try {
			return builder.getDocumentElement(true);
		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private org.apache.axiom.om.OMElement toOM(
			nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		return toOM(param);

	}

	private org.apache.axiom.om.OMElement toOM(
			final nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument param)
			throws org.apache.axis2.AxisFault {

		org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
		xmlOptions.setSaveNoXmlDecl();
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSaveNamespacesFirst();
		org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory
				.createOMBuilder(new javax.xml.transform.sax.SAXSource(
						new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param,
								xmlOptions), new org.xml.sax.InputSource()));
		try {
			return builder.getDocumentElement(true);
		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private org.apache.axiom.om.OMElement toOM(
			nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		return toOM(param);

	}

	private org.apache.axiom.om.OMElement toOM(
			final nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument param)
			throws org.apache.axis2.AxisFault {

		org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
		xmlOptions.setSaveNoXmlDecl();
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSaveNamespacesFirst();
		org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory
				.createOMBuilder(new javax.xml.transform.sax.SAXSource(
						new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param,
								xmlOptions), new org.xml.sax.InputSource()));
		try {
			return builder.getDocumentElement(true);
		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument param,
			boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		org.apache.axiom.soap.SOAPEnvelope envelope = factory
				.getDefaultEnvelope();
		if (param != null) {
			envelope.getBody().addChild(toOM(param, optimizeContent));
		}
		return envelope;
	}

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	public org.apache.xmlbeans.XmlObject fromOM(
			org.apache.axiom.om.OMElement param, java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {
		try {

			if (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.class
					.equals(type)) {
				if (extraNamespaces != null) {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching(),
									new org.apache.xmlbeans.XmlOptions()
											.setLoadAdditionalNamespaces(extraNamespaces));
				} else {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching());
				}
			}

			if (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.class
					.equals(type)) {
				if (extraNamespaces != null) {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching(),
									new org.apache.xmlbeans.XmlOptions()
											.setLoadAdditionalNamespaces(extraNamespaces));
				} else {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching());
				}
			}

			if (nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument.class
					.equals(type)) {
				if (extraNamespaces != null) {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching(),
									new org.apache.xmlbeans.XmlOptions()
											.setLoadAdditionalNamespaces(extraNamespaces));
				} else {
					return nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument.Factory
							.parse(param.getXMLStreamReaderWithoutCaching());
				}
			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
