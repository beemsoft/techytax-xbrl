/**
 * Copyright 2013 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.digipoort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.techytax.domain.Periode;
import org.techytax.domain.VatDeclarationData;
import org.techytax.props.PropsFactory;
import org.techytax.security.ClientPasswordCallback;
import org.techytax.security.SecureConnectionHelper;
import org.techytax.util.DateHelper;
import org.techytax.ws.AanleverRequest;
import org.techytax.ws.AanleverResponse;
import org.techytax.ws.AanleverServiceFault;
import org.techytax.ws.AanleverServiceV12;
import org.techytax.ws.AanleverServiceV12_Service;
import org.techytax.ws.BerichtInhoudType;
import org.techytax.ws.GetBerichtsoortenRequest;
import org.techytax.ws.GetBerichtsoortenResponse;
import org.techytax.ws.GetNieuweStatussenProcesRequest;
import org.techytax.ws.GetNieuweStatussenProcesResponse;
import org.techytax.ws.GetNieuweStatussenRequest;
import org.techytax.ws.GetNieuweStatussenResponse;
import org.techytax.ws.GetProcessenRequest;
import org.techytax.ws.GetProcessenResponse;
import org.techytax.ws.GetStatussenProcesRequest;
import org.techytax.ws.GetStatussenProcesResponse;
import org.techytax.ws.IdentiteitType;
import org.techytax.ws.ObjectFactory;
import org.techytax.wus.status.StatusinformatieServiceFault;
import org.techytax.wus.status.StatusinformatieServiceV12;
import org.techytax.wus.status.StatusinformatieServiceV12_Service;
import org.techytax.xbrl.DynamicWsaSignaturePartsInterceptor;

public class DigipoortServiceImpl implements DigipoortService {

	private static final String OMZETBELASTING = "Omzetbelasting";
	private static final String FISCAL_TYPE = "Fi";
	private static final String AUTORISATIE_ADRES = "http://geenausp.nl";
	private static final QName AANLEVER_SERVICE_NAME = new QName("http://logius.nl/digipoort/wus/2.0/aanleverservice/1.2/", "AanleverService_V1_2");
	private static final QName STATUS_SERVICE_NAME = new QName("http://logius.nl/digipoort/wus/2.0/statusinformatieservice/1.2/",
			"StatusinformatieService_V1_2");
	private Properties keyProperties = new Properties();
	private Properties trustProperties = new Properties();

	private ObjectFactory objectFactory = new ObjectFactory();
	
	public DigipoortServiceImpl() {
		try {
			keyProperties.load(DigipoortServiceImpl.class.getResourceAsStream("client_sign.properties"));
			trustProperties.load(DigipoortServiceImpl.class.getResourceAsStream("client_verify.properties"));
			String keyStorePassword = keyProperties.getProperty("org.apache.ws.security.crypto.merlin.keystore.password");
			ClientPasswordCallback.setKeyStorePassword(keyStorePassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AanleverResponse aanleveren(VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException, GeneralSecurityException, AanleverServiceFault {
		AanleverServiceV12 port = setupWebServicePort();
		System.out.println("Invoking aanleveren...");
		AanleverRequest aanleverRequest = null;
		AanleverResponse aanleverResponse = null;
		try {
			aanleverRequest = createAanleverRequest(vatDeclarationData);
			aanleverResponse = port.aanleveren(aanleverRequest);
			System.out.println("aanleveren.result=" + aanleverResponse);
			System.out.println("Aangeleverd=" + aanleverResponse.getTijdstempelAangeleverd());
			System.out.println("Statuscode=" + aanleverResponse.getStatuscode());
			System.out.println("Kenmerk=" + aanleverResponse.getKenmerk());
		} catch (AanleverServiceFault e) {
			System.out.println("Expected exception: AanleverServiceFault has occurred.");
			System.out.println("Foutcode: " + e.getFaultInfo().getFoutcode());
			System.out.println("Foutbeschrijving: " + e.getFaultInfo().getFoutbeschrijving());
			throw e;
		} catch (SOAPFaultException sfe) {
			System.out.println("SOAP error: " + sfe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aanleverResponse;
	}
	
	private AanleverServiceV12 setupWebServicePort() throws FileNotFoundException, IOException, GeneralSecurityException {
		URL wsdlURL = getWsdlUrlForAanleveren();
		AanleverServiceV12_Service ss = new AanleverServiceV12_Service(wsdlURL, AANLEVER_SERVICE_NAME);
		AanleverServiceV12 port = ss.getAanleverServiceV12();
		SecureConnectionHelper.setupTLS(port, keyProperties, trustProperties);
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		addLogging(cxfEndpoint);
		addSignatureProperties(cxfEndpoint);
		return port;
	}

	private StatusinformatieServiceV12 setupPortForStatus() throws FileNotFoundException, IOException, GeneralSecurityException {
		URL wsdlURL = getWsdlUrlForStatus();
		StatusinformatieServiceV12_Service ss = new StatusinformatieServiceV12_Service(wsdlURL, STATUS_SERVICE_NAME);
		StatusinformatieServiceV12 port = ss.getStatusinformatieServiceV12();
		SecureConnectionHelper.setupTLS(port, keyProperties, trustProperties);
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		addLogging(cxfEndpoint);
		addSignatureProperties(cxfEndpoint);
		return port;
	}

	private URL getWsdlUrlForAanleveren() throws IOException {
		String digipoort = PropsFactory.getProperty("digipoort");
		String wsdlName = null;
		URL wsdlURL = null;
		if (digipoort.equals("prod")) {
			wsdlName = "Aanleverservice_Digipoort_WUS 2.0 Bedrijven_v1.2_prod.wsdl";
		} else {
			wsdlName = "aanlever_test.wsdl";
		}
		wsdlURL = this.getClass().getResource(wsdlName);
		if (wsdlURL == null) {
			java.util.logging.Logger.getLogger(AanleverServiceV12_Service.class.getName()).log(java.util.logging.Level.SEVERE,
					"Can not initialize the default wsdl from {0}", wsdlName);
		}
		return wsdlURL;
	}

	private URL getWsdlUrlForStatus() throws IOException {
		String digipoort = PropsFactory.getProperty("digipoort");
		String wsdlName = null;
		URL wsdlURL = null;
		if (digipoort.equals("prod")) {
			wsdlName = "Statusinformatieservice_Digipoort_WUS 2.0 Bedrijven_v1.2_prod.wsdl";
		} else {
			wsdlName = "status_test.wsdl";
		}
		wsdlURL = this.getClass().getResource(wsdlName);
		if (wsdlURL == null) {
			java.util.logging.Logger.getLogger(StatusinformatieServiceV12.class.getName()).log(java.util.logging.Level.SEVERE,
					"Can not initialize the default wsdl from {0}", wsdlName);
		}
		return wsdlURL;
	}

	private void addLogging(org.apache.cxf.endpoint.Endpoint cxfEndpoint) {
		cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
		cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());
	}

	private void addSignatureProperties(org.apache.cxf.endpoint.Endpoint cxfEndpoint) {
		Map<String, Object> outProps = new HashMap<String, Object>();
		String keyStoreAlias = keyProperties.getProperty("org.apache.ws.security.crypto.merlin.keystore.alias");
		outProps.put(WSHandlerConstants.USER, WSHandlerConstants.USE_REQ_SIG_CERT);
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIGNATURE_USER, keyStoreAlias);
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());
		outProps.put(
				WSHandlerConstants.SIGNATURE_PARTS,
				"{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body;{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp");
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "client_sign.properties");
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "client_verify.properties");
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		cxfEndpoint.getInInterceptors().add(wssIn);
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		cxfEndpoint.getOutInterceptors().add(wssOut);
		cxfEndpoint.getOutInterceptors().add(new DynamicWsaSignaturePartsInterceptor());
	}

	private AanleverRequest createAanleverRequest(VatDeclarationData vatDeclarationData) throws IOException {
		AanleverRequest aanleverRequest = new AanleverRequest();
		aanleverRequest.setAutorisatieAdres(AUTORISATIE_ADRES);
		aanleverRequest.setBerichtsoort(OMZETBELASTING);
		Periode period = DateHelper.getLatestVatPeriod();
		vatDeclarationData.setStartDate(period.getBeginDatum());
		vatDeclarationData.setEndDate(period.getEindDatum());
		aanleverRequest.setRolBelanghebbende("Bedrijf");
		BerichtInhoudType berichtInhoud = new BerichtInhoudType();
		berichtInhoud.setMimeType("application/xml");
		berichtInhoud.setBestandsnaam("Omzetbelasting.xbrl");
		String digipoort = PropsFactory.getProperty("digipoort");
		if (digipoort.equals("prod")) {
			berichtInhoud.setInhoud(XbrlHelper.createXbrlInstance(vatDeclarationData).replaceAll(">\\s+<", "><").trim().getBytes("UTF-8"));
		} else {
			berichtInhoud.setInhoud(XbrlHelper.createTestXbrlInstance().replaceAll(">\\s+<", "><").trim().getBytes("UTF-8"));
			vatDeclarationData.setFiscalNumber(XbrlHelper.getTestFiscalNumber());
		}
		addIdentiteit(vatDeclarationData, aanleverRequest);
		aanleverRequest.setBerichtInhoud(berichtInhoud);
		return aanleverRequest;
	}

	private void addIdentiteit(VatDeclarationData vatDeclarationData, AanleverRequest aanleverRequest) {
		IdentiteitType identiteitBelanghebbende = new IdentiteitType();
		identiteitBelanghebbende.setNummer(vatDeclarationData.getFiscalNumber());
		identiteitBelanghebbende.setType(FISCAL_TYPE);
		aanleverRequest.setIdentiteitBelanghebbende(identiteitBelanghebbende);
	}

	@Override
	public GetNieuweStatussenResponse getNieuweStatussen(VatDeclarationData vatDeclarationData) throws IOException, GeneralSecurityException {
		StatusinformatieServiceV12 port = setupPortForStatus();
		try {
			GetNieuweStatussenRequest request = objectFactory.createGetNieuweStatussenRequest();
			request.setAutorisatieAdres(AUTORISATIE_ADRES);
			request.setBerichtsoort(OMZETBELASTING);
			IdentiteitType identiteitType = objectFactory.createIdentiteitType();
			identiteitType.setNummer(vatDeclarationData.getFiscalNumber());
			identiteitType.setType(FISCAL_TYPE);
			request.setIdentiteitBelanghebbende(identiteitType);
			request.setTijdstempelVanaf(DateHelper.getDateForXml(vatDeclarationData.getStartDate()));
			request.setTijdstempelTot(DateHelper.getDateForXml(vatDeclarationData.getEndDate()));			
			return port.getNieuweStatussen(request);
		} catch (StatusinformatieServiceFault e) {
			System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
			System.out.println(e.toString());
		}
		return null;
	}

	@Override
	public GetNieuweStatussenProcesResponse getNieuweStatussenProces(VatDeclarationData vatDeclarationData, String kenmerk)
			throws FileNotFoundException, IOException, GeneralSecurityException {
		StatusinformatieServiceV12 port = setupPortForStatus();
		try {
			GetNieuweStatussenProcesRequest request = objectFactory.createGetNieuweStatussenProcesRequest();
			request.setKenmerk(kenmerk);
			request.setAutorisatieAdres(AUTORISATIE_ADRES);
			request.setTijdstempelVanaf(DateHelper.getDateForXml(vatDeclarationData.getStartDate()));
			request.setTijdstempelTot(DateHelper.getDateForXml(vatDeclarationData.getEndDate()));
			return port.getNieuweStatussenProces(request);
		} catch (StatusinformatieServiceFault e) {
			System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
			System.out.println(e.toString());
		}
		return null;
	}

	@Override
	public GetProcessenResponse getProcessen(VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException,
			GeneralSecurityException, StatusinformatieServiceFault {
		StatusinformatieServiceV12 port = setupPortForStatus();
		try {
			GetProcessenRequest request = objectFactory.createGetProcessenRequest();
			request.setBerichtsoort(OMZETBELASTING);
			IdentiteitType identiteitType = objectFactory.createIdentiteitType();
			identiteitType.setNummer(vatDeclarationData.getFiscalNumber());
			identiteitType.setType(FISCAL_TYPE);
			request.setIdentiteitBelanghebbende(identiteitType);
			request.setAutorisatieAdres(AUTORISATIE_ADRES);
			return port.getProcessen(request);
		} catch (StatusinformatieServiceFault e) {
			System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
			System.out.println(e.toString());
			throw e;
		}
	}

	@Override
	public GetStatussenProcesResponse getStatussenProces(VatDeclarationData vatDeclarationData, String kenmerk) throws FileNotFoundException,
			IOException, GeneralSecurityException {
		StatusinformatieServiceV12 port = setupPortForStatus();
		try {
			GetStatussenProcesRequest request = objectFactory.createGetStatussenProcesRequest();
			request.setKenmerk(kenmerk);
			request.setTijdstempelVanaf(DateHelper.getDateForXml(vatDeclarationData.getStartDate()));
			request.setTijdstempelTot(DateHelper.getDateForXml(vatDeclarationData.getEndDate()));
			request.setAutorisatieAdres(AUTORISATIE_ADRES);
			return port.getStatussenProces(request);
		} catch (StatusinformatieServiceFault e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GetBerichtsoortenResponse getBerichtsoorten(VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException,
			GeneralSecurityException {
		StatusinformatieServiceV12 port = setupPortForStatus();
		try {
			GetBerichtsoortenRequest request = objectFactory.createGetBerichtsoortenRequest();
			IdentiteitType identiteitType = objectFactory.createIdentiteitType();
			identiteitType.setNummer(vatDeclarationData.getFiscalNumber());
			identiteitType.setType(FISCAL_TYPE);
			request.setIdentiteitBelanghebbende(identiteitType);
			request.setAutorisatieAdres(AUTORISATIE_ADRES);
			return port.getBerichtsoorten(request);
		} catch (StatusinformatieServiceFault e) {
			e.printStackTrace();
		}
		return null;
	}

}
