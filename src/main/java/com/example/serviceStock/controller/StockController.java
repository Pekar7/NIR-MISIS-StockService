package com.example.serviceStock.controller;


import com.example.serviceStock.model.Stock;
import com.example.serviceStock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StockController {
    private final StockService stockService;

    @Operation(summary = "Get Stock by Ticker", tags = "TICKER")
    @GetMapping("/stocks/{ticker}")
    public Stock getStockByTicker(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker);
    }

}
