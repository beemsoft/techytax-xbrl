
/**
 * AanleverService_V1_2CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package nl.logius.digipoort.wus._2_0.aanleverservice._1_2;

    /**
     *  AanleverService_V1_2CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AanleverService_V1_2CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AanleverService_V1_2CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AanleverService_V1_2CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for aanleveren method
            * override this method for handling normal response from aanleveren operation
            */
           public void receiveResultaanleveren(
                    nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from aanleveren operation
           */
            public void receiveErroraanleveren(java.lang.Exception e) {
            }
                


    }
    