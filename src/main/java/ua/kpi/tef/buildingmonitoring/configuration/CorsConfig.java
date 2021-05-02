package ua.kpi.tef.buildingmonitoring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  /**
   * Overriding the CORS configuration to exposed required header for ussd to work
   *
   * @param registry CorsRegistry
   */

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
  }
}

