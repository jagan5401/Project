package com.te.userflow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.userflow.exception.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // for preAuthorized annotation
public class SecurityConfig {

	@Autowired
	private JwtFilter filter;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	private static final String[] AUTH_SWAGGER_LIST = { "/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**",
			"/swagger-resources/**" };

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		// System.out.println("8");
		return authenticationConfiguration.getAuthenticationManager();
	}

//without password encode
//	@Bean
//	public PasswordEncoder encoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	// password encode
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// System.out.println("9");
		httpSecurity.csrf().disable().authorizeHttpRequests()
		// .requestMatchers("/Doctor/add").hasRole("DOCTOR") //it will be replaced by
		// preAuthorized Annotation
		// .requestMatchers("/Appointment/add/{userId}/{doctorId}").hasRole("USER")
//		      .requestMatchers("/**").permitAll()
				.requestMatchers("/User/getAuthenticate").permitAll()
				// .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
				.requestMatchers(AUTH_SWAGGER_LIST).permitAll().anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

}
