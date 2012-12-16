
/**
 * AanleverServiceFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package nl.logius.digipoort.wus._2_0.aanleverservice._1_2;

public class AanleverServiceFault extends java.lang.Exception{

    private static final long serialVersionUID = 1355005859394L;
    
    private nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument faultMessage;

    
        public AanleverServiceFault() {
            super("AanleverServiceFault");
        }

        public AanleverServiceFault(java.lang.String s) {
           super(s);
        }

        public AanleverServiceFault(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public AanleverServiceFault(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument msg){
       faultMessage = msg;
    }
    
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument getFaultMessage(){
       return faultMessage;
    }
}
    