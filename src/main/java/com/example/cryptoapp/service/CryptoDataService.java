package com.example.cryptoapp.service;

import com.example.cryptoapp.model.CryptoData;
import com.example.cryptoapp.repository.CryptoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CryptoDataService {
    @Autowired
    private CryptoDataRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CryptoData> fetchAndSaveData() {
        String apiUrl = "https://api.wazirx.com/api/v2/tickers";
        Map<String, Map<String, String>> response = restTemplate.getForObject(apiUrl, Map.class);

        List<CryptoData> top10Data = response.entrySet().stream()
                .limit(10)
                .map(entry -> {
                    CryptoData data = new CryptoData();
                    data.setName(entry.getKey());
                    Map<String, String> details = entry.getValue();
                    data.setLast(details.get("last"));
                    data.setBuy(details.get("buy"));
                    data.setSell(details.get("sell"));
                    data.setVolume(details.get("volume"));
                    data.setBaseUnit(details.get("base_unit"));
                    return data;
                })
                .collect(Collectors.toList());

        repository.deleteAll(); // Clear existing data
        return repository.saveAll(top10Data);
    }

    public List<CryptoData> getAllCryptoData() {
        List<CryptoData> data = repository.findAll();
        if (data.isEmpty()) {
            return fetchAndSaveData();
        }
        return data;
    }

    @Scheduled(fixedRate = 300000) // Run every 5 minutes
    public void scheduledFetchAndSaveData() {
        fetchAndSaveData();
    }
}