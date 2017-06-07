package com.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.security.service.IPTokenBasedRememberMeService;
import com.spring.security.service.MemberService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	MemberService m;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(new ShaPasswordEncoder(256));
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		if (this.m == null) {
			this.m = new MemberService();
			System.out.println("create MemberService");
		}
		return this.m;
	}
	
	@Bean
	public IPTokenBasedRememberMeService ipTokenBasedRememberMeServices() {
		IPTokenBasedRememberMeService service = new IPTokenBasedRememberMeService("rememberMeKey", userDetailsService());
		service.setParameter("remember_me");
		service.setCookieName("REMEMBER_ME");
		return service;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/welcome").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
			.and()
			.formLogin()
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login")
				.loginProcessingUrl("/check")
				.defaultSuccessUrl("/welcome")
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
			.exceptionHandling().accessDeniedPage("/accessDenied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**");
	}
	

}
