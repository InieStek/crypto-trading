package pl.project.cryptotradingmarketdata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.project.cryptotradingmarketdata.exception.MarketDataException;
import pl.project.cryptotradingmarketdata.dto.CryptocurrencyDto;

@Service
public class CoinrankingMarketDataService implements MarketDataService {

  private static final String API_BASE_URL = "https://api.coinranking.com/v2";
  private static final String COINS_ENDPOINT = "/coins";

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Value("${coinranking.api.key}")
  private String apiKey;

  @Autowired
  public CoinrankingMarketDataService(RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  public List<CryptocurrencyDto> getCurrentPrices(int limit) {
    String url = API_BASE_URL + COINS_ENDPOINT + "?limit=" + limit;

    HttpHeaders headers = new HttpHeaders();
    headers.set("x-access-token", apiKey);
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    try {
      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
      JsonNode root = objectMapper.readTree(response.getBody());
      JsonNode data = root.get("data");
      JsonNode coins = data.get("coins");

      List<CryptocurrencyDto> cryptocurrencies = new ArrayList<>();
      for (JsonNode coin : coins) {
        String symbol = coin.get("symbol").asText();
        String name = coin.get("name").asText();
        double price = coin.get("price").asDouble();
        cryptocurrencies.add(new CryptocurrencyDto(symbol, name, price));
      }
      return cryptocurrencies;
    } catch (Exception e) {
      throw new MarketDataException("Error fetching cryptocurrency prices", e);
    }
  }

  @Override
  public CryptocurrencyDto getPriceBySymbol(String symbol) {
    String url = API_BASE_URL + COINS_ENDPOINT + "?symbol=" + symbol;

    HttpHeaders headers = new HttpHeaders();
    headers.set("x-access-token", apiKey);
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    try {
      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
      JsonNode root = objectMapper.readTree(response.getBody());
      JsonNode data = root.get("data");
      JsonNode coins = data.get("coins");

      if (coins.size() > 0) {
        JsonNode coin = coins.get(0);
        String name = coin.get("name").asText();
        double price = coin.get("price").asDouble();
        return new CryptocurrencyDto(symbol, name, price);
      } else {
        throw new MarketDataException("Price not found for cryptocurrency: " + symbol);
      }
    } catch (Exception e) {
      throw new MarketDataException("Error fetching cryptocurrency price", e);
    }
  }
}
