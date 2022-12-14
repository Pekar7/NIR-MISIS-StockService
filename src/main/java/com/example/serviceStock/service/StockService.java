package com.example.serviceStock.service;


import com.example.serviceStock.model.PriceInfo;
import com.example.serviceStock.model.Stock;

public interface StockService {
    Stock getStockByTicker(String ticker);
    PriceInfo getPriceByFigi(String figi);
}
