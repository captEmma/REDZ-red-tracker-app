package com.redz.financeportfolio.model;

import java.util.Map;
import java.util.stream.Collectors;

public class StockData {
    private Map<Long, Double> open;
    private Map<Long, Double> high;
    private Map<Long, Double> low;
    private Map<Long, Double> close;
    private Map<Long, Double> volume;
    private Map<Long, Double> dividends;
    private Map<Long, Double> stockSplits;

    public Map<Long, Double> getOpen() {
        return open;
    }

    public void setOpen(Map<Long, Double> open) {
        this.open = open;
    }

    public Map<Long, Double> getHigh() {
        return high;
    }

    public void setHigh(Map<Long, Double> high) {
        this.high = high;
    }

    public Map<Long, Double> getLow() {
        return low;
    }

    public void setLow(Map<Long, Double> low) {
        this.low = low;
    }

    public Map<Long, Double> getClose() {
        return close;
    }

    public void setClose(Map<Long, Double> close) {
        this.close = close;
    }

    public Map<Long, Double> getVolume() {
        return volume;
    }

    public void setVolume(Map<Long, Double> volume) {
        this.volume = volume;
    }

    public Map<Long, Double> getDividends() {
        return dividends;
    }

    public void setDividends(Map<Long, Double> dividends) {
        this.dividends = dividends.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public Map<Long, Double> getStockSplits() {
        return stockSplits;
    }

    public void setStockSplits(Map<Long, Double> stockSplits) {
        this.stockSplits = stockSplits;
    }
}
