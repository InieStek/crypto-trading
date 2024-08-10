package pl.project.cryptotradingapigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    exchange.getRequest().mutate().header("X-Gateway-Header", "API Gateway");
    return chain.filter(exchange);
  }
}
