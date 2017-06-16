package com.spring.security.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.spring.security.service.IPTokenBasedRememberMeService;
import com.spring.security.service.MemberAccessDeniedHandler;
import com.spring.security.service.MemberAuthenticationFailureHandler;
import com.spring.security.service.MemberAuthenticationProvider;
import com.spring.security.service.MemberService;
import com.spring.security.service.PasswordEncoding;
import com.spring.security.service.ReloadableFilterInvocationSecurityMetadataSource;
import com.spring.security.service.SecuredObjectService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	MemberAuthenticationProvider provider;
	MemberService memberService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(authenticationProvider());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**");
	}
	
	@Bean
	public SecuredObjectService securedObjectService() {
		SecuredObjectService securedObjectService = new SecuredObjectService();
		return securedObjectService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		if (this.provider == null) {
			this.provider = new MemberAuthenticationProvider();
			System.out.println("create MemberAuthenticationProvider");
		}
		return this.provider;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		if (this.memberService == null) {
			this.memberService = new MemberService();
			System.out.println("create MemberService");
		}
		return this.memberService;
	}
	
	@Bean
	public IPTokenBasedRememberMeService ipTokenBasedRememberMeServices() {
		IPTokenBasedRememberMeService service = new IPTokenBasedRememberMeService("rememberMeKey", userDetailsService());
		service.setParameter("remember_me");
		service.setCookieName("REMEMBER_ME");
		return service;
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		AuthenticationFailureHandler handler = new MemberAuthenticationFailureHandler("/login?error");
		return handler;
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		AuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler("/welcome");
		return handler;
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		MemberAccessDeniedHandler handler = new MemberAccessDeniedHandler();
		handler.setErrorPage("/WEB-INF/views/accessDenied.jsp");
		return handler;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoding encoder = new PasswordEncoding();
		return encoder;
	}
	
	@Bean
	public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter> decisionVoters = new ArrayList<>();
		decisionVoters.add(roleVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
		affirmativeBased.setAllowIfAllAbstainDecisions(false);
		return affirmativeBased;
	}
	
	@Bean
	public RoleVoter roleVoter() {
		RoleVoter voters = new RoleVoter();
		voters.setRolePrefix("");
		return voters;
	}
	
	/*@Bean
	public UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() throws Exception {
		UrlResourcesMapFactoryBean factoryBean = new UrlResourcesMapFactoryBean();
		factoryBean.init();
		return factoryBean;
	}*/
	
	@Bean
	public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() throws Exception {
		Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = securedObjectService().getUrlAndRole().entrySet().iterator();
		Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
		while(iterator.hasNext()) {
			Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
			requestMap.put(entry.getKey(), entry.getValue());
		}
		
		ReloadableFilterInvocationSecurityMetadataSource metadataSource 
			= new ReloadableFilterInvocationSecurityMetadataSource(requestMap);
		return metadataSource;
	}
	
	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
		
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(authenticationProvider());
		AuthenticationManager authenticationManager = new ProviderManager(providers);
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
		
		return filterSecurityInterceptor;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.anonymous().authorities("ANONYMOUS")
			.and()
			.formLogin()
				//.failureUrl("/login?error")
				.failureHandler(authenticationFailureHandler())
				.successHandler(authenticationSuccessHandler())
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login")
				.loginProcessingUrl("/check")
				.defaultSuccessUrl("/welcome", true)
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
			.rememberMe()
				.key("rememberMeKey")
				.tokenValiditySeconds(1209600)
				.userDetailsService(userDetailsService())
			.and()
			.addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}
	

}
