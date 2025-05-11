package com.example.crypto.controller;

import com.example.crypto.model.PriceDto;
import com.example.crypto.service.KrakenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")  
public class KrakenController {
    
    private final KrakenService krakenService;

    public KrakenController(KrakenService krakenService) {
        this.krakenService = krakenService;
    }

    @GetMapping("/prices")
public ResponseEntity<List<PriceDto>> getCryptoPrices() {
    String[] pairs = {
        "BTCUSD", "ETHUSD", "XRPUSD", "LTCUSD", "ADAUSD",
    "DOTUSD", "DOGEUSD", "SOLUSD", "AVAXUSD", "TRXUSD",
    "LINKUSD", "MATICUSD", "ATOMUSD", "XLMUSD", "ETCUSD",
    "BCHUSD", "NEARUSD", "FILUSD", "ALGOUSD", "EOSUSD"
    };

    List<PriceDto> prices = krakenService.getCryptoPrices(pairs);
    return ResponseEntity.ok(prices);
}

}

