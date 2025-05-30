package com.open.spring.system;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // set up your own index
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* map path and location for "uploads" outside of application resources
       ... creates a directory outside "static" folder, "file:volumes/uploads"
       ... CRITICAL, without this uploaded file will not be loaded/displayed by frontend
     */
    @Override
    public void addResourceHandlers(final @NonNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/volumes/uploads/**").addResourceLocations("file:volumes/uploads/");
    }
    
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://spring.opencodingsociety.com", "https://pages.opencodingsociety.com", "http://127.0.0.1:4500", "http://localhost:4500").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");


        // registry.addMapping("/socket.io").allowedOrigins("https://spring.opencodingsociety.com", "https://pages.opencodingsociety.com", "http://127.0.0.1:4500", "http://localhost:4500").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

    }
    
}