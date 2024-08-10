package pl.project.cryptotradingapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CryptoTradingApiGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(CryptoTradingApiGatewayApplication.class, args);
  }

}
