package com.example.cryptoapp.repository;

import com.example.cryptoapp.model.CryptoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoDataRepository extends JpaRepository<CryptoData, Long> {
}