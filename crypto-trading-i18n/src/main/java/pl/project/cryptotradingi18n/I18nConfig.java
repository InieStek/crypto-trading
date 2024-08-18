package pl.project.cryptotradingi18n;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "crypto.i18n")
public class I18nConfig {

  private String translationServiceUrl;
  private String defaultLanguage = "en";
  private boolean useCaching = true;

  @Bean
  public CustomI18n customI18n(RestTemplate restTemplate) {
    return new CustomI18n(restTemplate, translationServiceUrl, defaultLanguage, useCaching);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  // Gettery i settery dla właściwości

  public String getTranslationServiceUrl() {
    return translationServiceUrl;
  }

  public void setTranslationServiceUrl(String translationServiceUrl) {
    this.translationServiceUrl = translationServiceUrl;
  }

  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public boolean isUseCaching() {
    return useCaching;
  }

  public void setUseCaching(boolean useCaching) {
    this.useCaching = useCaching;
  }
}