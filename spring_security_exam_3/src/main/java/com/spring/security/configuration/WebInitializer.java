package com.spring.security.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		rootContext.register(SecurityConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext appServletContext = new AnnotationConfigWebApplicationContext();
		appServletContext.register(ServletConfig.class);
		
		FilterRegistration.Dynamic frEnc = servletContext.addFilter("CharacterEncodingFilter"
				, org.springframework.web.filter.CharacterEncodingFilter.class);
		frEnc.setInitParameter("encoding", "utf-8");
		frEnc.setInitParameter("forceEncoding", "true");
		frEnc.addMappingForUrlPatterns(null, true, "/*");
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", new DispatcherServlet(appServletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
//		FilterRegistration.Dynamic frSec = servletContext.addFilter("springSecurityFilterChain"
//				, org.springframework.web.filter.DelegatingFilterProxy.class);
//		frSec.addMappingForUrlPatterns(null, true, "/*");
		
//		servletContext.addFilter("springSecurityFilterChain", new org.springframework.web.filter.DelegatingFilterProxy("springSecurityFilterChain"))
//        .addMappingForUrlPatterns(null, false, "/*");
	}
	
}
