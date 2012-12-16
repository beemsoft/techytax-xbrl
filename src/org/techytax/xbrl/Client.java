package org.techytax.xbrl;


import javax.xml.parsers.ParserConfigurationException;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.w3c.dom.Document;

public class Client {

    /**
     * Absolute value of the axis2 configuration path This path must have a
     * directory named "modules" which contains rampart
     */
    private String axis2ConfPath;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String clientAxis2Path = null;
        String user = null;
//        if (args == null | args.length == 0) {
//            System.err.println("Missing arguments");
//            System.exit(0);
//        }
//        clientAxis2Path = args[0];
//        user = args[1];

        String action = "http://petals.ow2.org/helloworld/sayHello";

        Client client = new Client(clientAxis2Path);
        SOAPEnvelope result;

        System.out.println("UnSecure WS call...");
        result = client.callSayHello(false, user, action);
        if (result == null) {
            System.out.println("\n" + "SUCCESS !!!");
        } else {
            System.out.println("ERROR : An error was expected");
        }

        System.out.println("Secure WS call...");
        result = client.callSayHello(true, user, action);

        if (result != null) {
            System.out.println("RESPONSE " + "\n" + result.toString());
            System.out.println("SUCCESS !!!");
        } else {
            System.out.println("NO RESULT RETURNED");
        }
    }

    /**
     * Creates a new instance of {@link Client}
     *
     */
    public Client(String path) {
        this.axis2ConfPath = path;
    }

    /**
     * Call the sayHello operation
     *
     * @param secure
     *            call in secure mode or not
     * @return
     */
    public SOAPEnvelope callSayHello(boolean secure, String user, String action) {
        SOAPEnvelope responseEnvelope = null;
        EndpointReference targetEPR = new EndpointReference(
                "http://127.0.0.1:8084/petals/services/UserPasswordSecuredService");

        String sayHelloStr = "Helloworld secured service";
        try {

            ConfigurationContext ctx = ConfigurationContextFactory
                    .createConfigurationContextFromFileSystem(this.axis2ConfPath, null);

            ServiceClient client = new ServiceClient(ctx, null);

            Options options = new Options();

            options.setTo(targetEPR);
            options.setAction(action);
            client.setOptions(options);

//            client.engageModule("rampart");

            MessageContext msgContext = new MessageContext();
            SOAPEnvelope envelope = this.createWssSoapEnveloppe(sayHelloStr, secure,user,"linuxRocks");
            msgContext.setEnvelope(envelope);

            OperationClient opClient = client.createClient(ServiceClient.ANON_OUT_IN_OP);
            opClient.addMessageContext(msgContext);
            System.out.println(envelope);
            opClient.execute(true);

            MessageContext responseMsgCtx = opClient
                    .getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            responseEnvelope = responseMsgCtx.getEnvelope();

        } catch (Exception e) {
            System.err.println(" ERROR : " + e.getMessage());
        }
        return responseEnvelope;
    }

    /**
     * Build the message payload
     *
     * @param data
     * @return
     */
    private OMElement getSayHelloOMElement(String data) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://petals.ow2.org/helloworld", "hel");
        OMElement method = fac.createOMElement("sayHello", omNs);
        method.addChild(fac.createOMText(method, data));

        return method;
    }
    
    private OMElement getAanleverRequest() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://petals.ow2.org/helloworld", "hel");
        OMElement method = fac.createOMElement("sayHello", omNs);
//        method.addChild(fac.createOMText(method, data));
//        fac.
        return method;
    }    

    private SOAPEnvelope createWssSoapEnveloppe(String data, Boolean isSecure,String user,String password)
            throws ParserConfigurationException {
        SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();

        if (isSecure) {
            envelope = this.addSecureSoapHeader(envelope,user,password);
        }
//        envelope.getBody().addChild(this.getSayHelloOMElement(data));
        envelope.getBody().addChild(this.getSayHelloOMElement(data));
        return envelope;
    }

    /**
     * Add a secure soap header in the given soap envelope
     *
     * @throws SOAPException
     * @throws ParserConfigurationException
     *
     */
    private SOAPEnvelope addSecureSoapHeader(SOAPEnvelope envelope,String user,String password)
            throws ParserConfigurationException {

        Document doc = null;
//        doc = SAAJUtil.getDocumentFromSOAPEnvelope(envelope);
//
//        WSSecHeader header = new WSSecHeader();
//        header.setMustUnderstand(true);
//        header.insertSecurityHeader(doc);
//
//        WSSecUsernameToken usernameToken = new WSSecUsernameToken();
//        usernameToken.addNonce();
//        usernameToken.addCreated();
//        usernameToken.setPasswordType(WSConstants.PASSWORD_TEXT);
//        usernameToken.setUserInfo(user, password);
//        usernameToken.prepare(doc);
//        usernameToken.build(doc, header);
//
//        WSSecTimestamp timeStamp = new WSSecTimestamp();
//        timeStamp.setTimeToLive(100);
//        timeStamp.prepare(doc);
//        timeStamp.build(doc, header);
//
//        envelope = SAAJUtil.getSOAPEnvelopeFromDOOMDocument(doc);

        return envelope;
    }
}
