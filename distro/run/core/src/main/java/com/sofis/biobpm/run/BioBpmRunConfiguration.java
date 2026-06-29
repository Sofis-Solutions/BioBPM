package com.sofis.biobpm.run;

import com.sofis.biobpm.run.property.BioBpmRunProperties;
import org.camunda.connect.plugin.impl.ConnectProcessEnginePlugin;
import org.camunda.spin.plugin.impl.SpinProcessEnginePlugin;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableConfigurationProperties(BioBpmRunProperties.class)
public class BioBpmRunConfiguration {

  @Bean
  public ConnectProcessEnginePlugin connectPlugin() {
    return new ConnectProcessEnginePlugin();
  }

  @Bean
  public SpinProcessEnginePlugin spinPlugin() {
    return new SpinProcessEnginePlugin();
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter(BioBpmRunProperties props) {
    var corsProps = props.getCors();
    var config = new CorsConfiguration();

    if (corsProps.isEnabled()) {
      config.setAllowedOriginPatterns(corsProps.getAllowedOrigins());
      config.setAllowedMethods(corsProps.getAllowedMethods());
      config.setAllowedHeaders(corsProps.getAllowedHeaders());
      config.setAllowCredentials(corsProps.isAllowCredentials());
      config.setMaxAge(corsProps.getMaxAge());
    } else {
      // CORS disabled: block all cross-origin requests
      config.setAllowedOrigins(java.util.List.of());
    }

    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/engine-rest/**", config);

    var bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }
}
