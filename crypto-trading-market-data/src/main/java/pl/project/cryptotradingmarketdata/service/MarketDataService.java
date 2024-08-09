package pl.project.cryptotradingmarketdata.service;

import java.util.List;
import pl.project.cryptotradingmarketdata.dto.CryptocurrencyDto;

public interface MarketDataService {
  List<CryptocurrencyDto> getCurrentPrices(int limit);
  CryptocurrencyDto getPriceBySymbol(String symbol);

}
