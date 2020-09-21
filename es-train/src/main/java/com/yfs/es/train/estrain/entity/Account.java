package com.yfs.es.train.estrain.entity;

import java.math.BigDecimal;

public class Account {

    public Account() {
    }

    public Account(BigDecimal currentMoney, int currentStock) {
        this.currentMoney = currentMoney;
        this.currentStock = currentStock;
    }

    private BigDecimal currentMoney;

    private int currentStock;

    private BigDecimal currentRadio;

    public BigDecimal getSumValue(BigDecimal price) {
        BigDecimal sumValue = currentMoney.add(BigDecimal.valueOf(currentStock * 100).multiply(price));
        return sumValue;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public BigDecimal getCurrentRadio() {
        return currentRadio;
    }

    public void setCurrentRadio(BigDecimal currentRadio) {
        this.currentRadio = currentRadio;
    }





}
