package com.nodedynamics.productservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.BeanIds;
import com.nodedynamics.productservices.security.JwtAuthenticationEntryPoint;
import com.nodedynamics.productservices.security.JwtAuthenticationFilter;
//import com.nodedynamics.userservices.service.userservice.AuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	//@Autowired
	//AuthService services;
	
	/*
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	

	
	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(services)
                .passwordEncoder(passwordEncoder());
    }
    
	
	 @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    */
	 
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .authorizeRequests()
	                    .antMatchers("/",
	                        "/favicon.ico",
	                        "/**/*.png",
	                        "/**/*.gif",
	                        "/**/*.svg",
	                        "/**/*.jpg",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js")
	                        .permitAll()
	                    .antMatchers("/api/product/getproduct") //.antMatchers("/api/user/adduser", "/api/user/checkEmailAvailability") 
	                        .permitAll()
	                    .antMatchers("/api/tax/addtaxes", "/api/tax/getalltaxes", "/api/wishlist/addtowishlist", "/api/review/addreview", "/api/review/getreview")
	                        .permitAll()
	                    .antMatchers(HttpMethod.GET, 
	                    		"/api/product/listlatestproducts", 
	                    		"/api/product/listproducts", 
	                    		"/api/product/list3latest",
	                    		"/api/product/list3latestimages", 
	                    		"/api/product/listlatestproducts", 
	                    		"/actuator/health/**")
	                        .permitAll()
	                    .anyRequest()
	                        .authenticated();

	        // Add our custom JWT security filter
	      //  http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    }

}