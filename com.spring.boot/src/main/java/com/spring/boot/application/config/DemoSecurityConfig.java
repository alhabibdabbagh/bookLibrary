package com.spring.boot.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
// login icin konfigrasyon 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication

		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("habib").password("test123").roles("EMPLOYEE"))
				.withUser(users.username("muhammed").password("test123").roles("EMPLOYEE", "MANAGER"))
				.withUser(users.username("betul").password("test123").roles("EMPLOYEE", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/employees/showForm*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN").antMatchers("/employees/delete")
				.hasRole("ADMIN").antMatchers("/employees/**").hasRole("EMPLOYEE").antMatchers("/resources/**")
				.permitAll().and().formLogin().loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser")
				.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/access-denied");

	}

}
