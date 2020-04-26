package in.co.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@ComponentScan("in.co.rays")
//@EnableWebMvc
@SpringBootApplication
//@CrossOrigin
public class CollegeDemoApplication {
	
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	System.out.println(new BCryptPasswordEncoder().encode("Rohit"));
    	return new BCryptPasswordEncoder();
    }
	public static void main(String[] args) {
		SpringApplication.run(CollegeDemoApplication.class, args);
	}
	
	/**
	 * Enables CORS to all urls
	 * 
	 * @return
	 */
/*	@Bean
	public WebMvcConfigurer corsConfigurer() {

		WebMvcConfigurer w = new WebMvcConfigurer() {

			*//**
			 * Add CORS
			 *//*
			public void addCorsMappings(CorsRegistry registry) {
				CorsRegistration cors = registry.addMapping("/**");
				cors.allowedOrigins("http://localhost:4200");
				cors.allowedHeaders("*");
				cors.allowCredentials(true);
			}

			*//**
			 * Add Interceptors
			 *//*
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns("/Auth/**");
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
			}

		};

		return w;
	}*/


}
