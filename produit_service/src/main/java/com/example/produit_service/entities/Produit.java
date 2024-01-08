package com.example.produit_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;
    private String nom;
    private String marque;
    private String description;
    private Double prix;
    private Integer quantite;
}
