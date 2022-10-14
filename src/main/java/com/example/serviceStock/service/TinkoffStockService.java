package com.example.serviceStock.service;


import com.example.serviceStock.exception.StockNotFoundException;
import com.example.serviceStock.model.Currency;
import com.example.serviceStock.model.PriceInfo;
import com.example.serviceStock.model.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;

@Slf4j
@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService {
    private final OpenApi api;


    @Override
    public Stock getStockByTicker(String ticker) {
        var contextApi = api.getMarketContext().searchMarketInstrumentsByTicker(ticker);
        var listContext = contextApi.join().getInstruments();
        var item = listContext.get(0);
        String figi = listContext.get(0).getFigi();

        if (listContext.isEmpty()) {
            throw new StockNotFoundException(String.format("Stock %S not found.", ticker));
        }

        var apCon = api.getMarketContext().getMarketOrderbook(figi, 0);

        return new Stock(
                item.getTicker(),
                item.getFigi(),
                item.getLot(),
                item.getName(),
                item.getType().getValue(),
                Currency.valueOf(item.getCurrency().getValue()),
                apCon.join().get().getLastPrice(),
                "TINKOFF");
    }


    @Override
    public PriceInfo getPriceByFigi(String figi) {
        var context = api.getMarketContext().getMarketOrderbook(figi, 0);
        var list = context.join().get();
        return new PriceInfo(
                list.getLastPrice(),
                list.getDepth(),
                list.getLimitUp(),
                list.getLimitDown(),
                list.getTradeStatus());
    }

}