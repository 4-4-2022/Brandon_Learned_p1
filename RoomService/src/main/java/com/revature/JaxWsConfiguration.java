package com.revature;

import javax.xml.ws.Endpoint;   

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.service.RoomServiceImpl;

@Configuration
public class JaxWsConfiguration {

	/*
	 * As a note, if you name your bean "dispatcherServlet", Spring will throw an exception as when a bean of that
	 * name is manually registered, Spring also requires a bean of another type to go into your container.
	 */
	
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet(){
		//All services are going to be located under "/soap-service". Note that "*" is just a wildcard.
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/soap-service/*");
	}
	
	/*
	 * The Bus is the central place in CXF. It is is primarily responsible for providing access to different extensions.
	 * It is also responsible for wiring up CXF internals.
	 * 
	 * Please note that the "SpringBus" abstracts away the bus configuration needed for CXF.
	 * 
	 * This bean MUST be named "cxf".
	 */
	@Bean(name = "cxf")
	public SpringBus springBus() {
		return new SpringBus();
	}
	
	@Bean
	public RoomServiceImpl roomServiceImpl() {
		return new RoomServiceImpl();
	}
	
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), roomServiceImpl());
		/*
		 * We need to expose the endpoing under an address. We have to publish endpoint ourself; it's not immediately
		 * consumable.
		 */
		endpoint.publish("/room-service");
		return endpoint;
	}
	
	
}