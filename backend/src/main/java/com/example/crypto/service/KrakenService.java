package com.example.crypto.service;

import java.util.ArrayList;

import com.example.crypto.model.PriceDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;



import java.util.List;

@Service
public class KrakenService {
    
    private final RestTemplate restTemplate;

    public KrakenService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

   public List<PriceDto> getCryptoPrices(String[] pairs) {
    List<PriceDto> result = new ArrayList<>();
    RestTemplate restTemplate = new RestTemplate();

    String joinedPairs = String.join(",", pairs);
    String url = "https://api.kraken.com/0/public/Ticker?pair=" + joinedPairs;

    String response = restTemplate.getForObject(url, String.class);
    System.out.println("Response from Kraken: " + response);

    JSONObject json = new JSONObject(response);
    JSONObject data = json.getJSONObject("result");

    for (String pair : data.keySet()) {
        JSONObject info = data.getJSONObject(pair);
        double price = info.getJSONArray("c").getDouble(0);
        System.out.println("Pair: " + pair + " Price: " + price);
        result.add(new PriceDto(pair, price));
    }

    return result;

    }
}
