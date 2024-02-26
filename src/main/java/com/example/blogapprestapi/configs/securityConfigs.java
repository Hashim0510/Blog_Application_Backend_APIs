package com.example.blogapprestapi.configs;


import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//used to add customizations to the dependency classes via a bean declared methods,
//configuration annotation classes  would have @bean anootated dependency classes to do customisations in their functionalities

@Configuration
public class securityConfigs {

    /*
    Basicaly, when adding spring security as a dependency to project, it will be enabling form based authentication for the accessing API's

    we have  several kinds of authentication like basic, form, jwt, oAuth etc.,

    In this below example I have made spring security to use basic authentication for api accessing which means all the api calls will username and password with basic as an authentication type will be included in the
    headers of a requests

    SecurityFilterChain is a servlet or interface which is starting point of authentication request

   IMPLEMENTATION CLASSES MAY HAVE IMPLEMENTS THIS ABOVE INTERFACES

    we are creating one random function with @Bean annotated and that return type will be SecurityFilterChain

    and we are configuring the customisations

     */
    //
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

    }

    /*
    @Bean: This annotation indicates that the method produces a bean to be managed by the Spring container. When Spring initializes, it will recognize this method and register the returned object as a bean.

    SecurityFilterChain: This method returns an instance of SecurityFilterChain, which is a part of Spring Security. SecurityFilterChain is responsible for defining the security configuration for a particular set of HTTP requests.

    filterChain(HttpSecurity httpSecurity): This method is a bean factory method that accepts an HttpSecurity object as a parameter. HttpSecurity is a class provided by Spring Security that allows configuring web-based security for your application.

    throws Exception: This method declares that it throws an Exception. This is required because the HttpSecurity object's build() method may throw an exception.

    httpSecurity.csrf().disable(): This line disables CSRF (Cross-Site Request Forgery) protection. CSRF is a security feature provided by Spring Security to prevent unauthorized requests from being executed on behalf of the authenticated user. In this case, it's being disabled.

    .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()): This line configures authorization rules. It specifies that any HTTP request should be authenticated, meaning only authenticated users are allowed to access any endpoint of the application.

    .httpBasic(Customizer.withDefaults()): This line configures HTTP Basic authentication. It sets up a basic authentication mechanism where the client sends its credentials with every HTTP request. Customizer.withDefaults() is used to apply default settings for HTTP Basic authentication.

    return httpSecurity.build(): Finally, the build() method is called on the HttpSecurity object to construct the SecurityFilterChain with the configured security settings. This SecurityFilterChain is then returned as the bean.

    Overall, this code configures Spring Security to disable CSRF protection, require authentication for all HTTP requests, and use HTTP Basic authentication with default settings. The resulting SecurityFilterChain bean can then be used to secure the web application.
     */


}
