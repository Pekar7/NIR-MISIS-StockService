package com.example.serviceStock.controller;


import com.example.serviceStock.model.PriceInfo;
import com.example.serviceStock.model.Stock;
import com.example.serviceStock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StockController {
    private final StockService stockService;

    @Operation(summary = "Get Stock Information by TICKER", tags = "TICKER")
    @GetMapping("/stocks/byTickerALLInfo/{ticker}")
    public Stock getStockByTicker(String ticker){
        return stockService.getStockByTicker(ticker);
    }

    @Operation(summary = "Get Price Information by FIGI", tags = "FIGI")
    @GetMapping("/stocks/byFigi/price/{figi}")
    public PriceInfo getPrice(@PathVariable String figi) {
        return stockService.getPriceByFigi(figi);
    }
}
