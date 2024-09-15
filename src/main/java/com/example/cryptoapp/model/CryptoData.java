package com.example.cryptoapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crypto_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CryptoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String last;
    private String buy;
    private String sell;
    private String volume;
    private String baseUnit;
}
