package pl.project.cryptotradinguser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CryptoTradingUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(CryptoTradingUserApplication.class, args);
  }

}
