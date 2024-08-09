package pl.project.cryptotradingmarketdata.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.project.cryptotradingmarketdata.dto.CryptocurrencyDto;
import pl.project.cryptotradingmarketdata.service.MarketDataService;

@RestController
@RequestMapping("/api/market-data")
public class MarketDataController {
  private final MarketDataService marketDataService;

  @Autowired
  public MarketDataController(MarketDataService marketDataService) {
    this.marketDataService = marketDataService;
  }

  @GetMapping("/prices")
  public List<CryptocurrencyDto> getPrices(@RequestParam(defaultValue = "10") int limit) {
    return marketDataService.getCurrentPrices(limit);
  }

  @GetMapping("/price/{symbol}")
  public CryptocurrencyDto getPriceBySymbol(@PathVariable String symbol) {
    return marketDataService.getPriceBySymbol(symbol);
  }
}
