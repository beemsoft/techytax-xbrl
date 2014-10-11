package org.techytax.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {
	
    public Server() throws Exception {
//        System.out.println("Starting Server");
//        MobileServiceImpl implementor = new MobileServiceImpl();
//        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
//        svrFactory.setServiceClass(MobileService.class);
//        svrFactory.setAddress("http://localhost:8080/service");
//        svrFactory.setServiceBean(implementor);
//        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
//        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
//        svrFactory.create();
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }

}
