package com.example.crypto.service;

import com.example.crypto.dao.HoldingDao;
import com.example.crypto.model.Holding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingService {
    
    private final HoldingDao holdingDao;

    public HoldingService(HoldingDao holdingDao){
        this.holdingDao = holdingDao;
    }

    public List<Holding> getHoldingsByUserId(int userId) {
        return holdingDao.getHoldingsByUserId(userId); // Връща списък с притежания
    }

    public Holding getHoldingByUserIdAndSymbol(int userId, String symbol) {
        return holdingDao.getHoldingByUserIdAndSymbol(userId, symbol); // Връща едно притежание по userId и символ
    }

    public void addOrUpdateHolding(int userId, String symbol, double quantityChange) {
        Holding existing = getHoldingByUserIdAndSymbol(userId, symbol);
        if (existing == null) {
            if (quantityChange > 0) {
                Holding newHolding = new Holding();
                newHolding.setUserId(userId);
                newHolding.setSymbol(symbol);
                newHolding.setAmount(quantityChange);
                holdingDao.addHolding(newHolding);
            } else {
                throw new IllegalArgumentException("There is no availability for reduction.");
            }
        } else {
            double newAmount = existing.getAmount() + quantityChange;
            if (newAmount < 0) {
                throw new IllegalArgumentException("Insufficient quantity for sale.");
            } else if (newAmount == 0) {
                holdingDao.deleteHolding(userId, symbol);
            } else {
                existing.setAmount(newAmount);
                holdingDao.updateHolding(existing);
            }
        }
    }

    public void addHolding(Holding holding) {
        holdingDao.addHolding(holding); // Добавя ново притежание
    }

    public void updateHolding(Holding holding) {
        holdingDao.updateHolding(holding); // Актуализира съществуващо притежание
    }

    public void deleteHolding(int userId, String symbol) {
        holdingDao.deleteHolding(userId, symbol);
    }
}
