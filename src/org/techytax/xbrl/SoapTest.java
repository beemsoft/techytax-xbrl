package org.techytax.xbrl;

import java.rmi.RemoteException;

import nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverServiceFault;
import nl.logius.digipoort.wus._2_0.aanleverservice._1_2.AanleverService_V1_2Stub;

import org.apache.axis2.AxisFault;

public class SoapTest {
	
	public static void main(String[] args) {
		
		try {
			AanleverService_V1_2Stub stub = new AanleverService_V1_2Stub();
			nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument document = nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.Factory.newInstance();
			document.addNewAanleverRequest();
			stub.aanleveren(document);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AanleverServiceFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
