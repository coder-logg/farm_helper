package edu.itmo.isbd.config;


import edu.itmo.isbd.filter.RegistrationFilter;
import edu.itmo.isbd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	private final String[] swagger = {"/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**"};

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and()
				.headers().httpStrictTransportSecurity().disable()
				.and()
				.formLogin().disable()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/registration", "/test/**").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers(swagger).permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/farmer/*").hasRole("FARMER")
				.antMatchers("/driver/*").hasRole("DRIVER")
				.antMatchers("/admin/*").hasRole("ADMIN")
				.anyRequest().authenticated()
				.expressionHandler(webExpressionHandler())
//			.and()
//				.logout()
//				.permitAll()
//				.logoutSuccessUrl("/")
			.and().httpBasic();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER\n" +
				"ROLE_ADMIN > ROLE_DRIVER\n" +
				"ROLE_ADMIN > ROLE_FARMER\n" +
				"ROLE_DRIVER > ROLE_USER\n" +
				"ROLE_FARMER > ROLE_USER");
		return hierarchy;
	}

	private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
		return defaultWebSecurityExpressionHandler;
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService);
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setPasswordEncoder(passwordEncoder());
		daoProvider.setUserDetailsService(userService);
		builder.authenticationProvider(daoProvider);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		corsConfiguration.setMaxAge(Duration.ofMinutes(60));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

	@Bean
	public FilterRegistrationBean<RegistrationFilter> registrationFilter(){
		FilterRegistrationBean<RegistrationFilter> filer = new FilterRegistrationBean<>();
		filer.setFilter(new RegistrationFilter());
		filer.setUrlPatterns(Arrays.asList("/registration", "/admin/registration", "/driver/registration", "/farmer/registration"));
		return filer;
	}
}
