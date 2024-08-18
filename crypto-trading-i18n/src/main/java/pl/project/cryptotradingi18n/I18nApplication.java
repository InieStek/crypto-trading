package pl.project.cryptotradingi18n;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class I18nApplication {
  public static void main(String[] args) {
    SpringApplication.run(I18nApplication.class, args);
  }
}
