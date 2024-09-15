package com.example.cryptoapp.controller;

import com.example.cryptoapp.model.CryptoData;
import com.example.cryptoapp.service.CryptoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CryptoController {
    @Autowired
    private CryptoDataService service;

    @GetMapping("/")
    public String getCryptoData(Model model) {
        List<CryptoData> cryptoDataList = service.getAllCryptoData();
        model.addAttribute("cryptoDataList", cryptoDataList);
        return "index";
    }
}