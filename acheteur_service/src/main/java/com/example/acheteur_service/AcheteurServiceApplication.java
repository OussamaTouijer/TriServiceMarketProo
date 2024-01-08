package com.example.acheteur_service;

import com.example.acheteur_service.entitise.Acheteur;
import com.example.acheteur_service.repositories.AcheteurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
@EnableFeignClients
@SpringBootApplication
public class AcheteurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcheteurServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AcheteurRepository acheteurRepository){
        return args -> {
            acheteurRepository.save(Acheteur.builder()
                    .nom("Ali Mohammed")
                    .ville("Rabat").idProduit(2)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Khaoula Jadimoussa")
                    .ville("Casablanca").idProduit(3)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Driss Zahrawi ")
                    .ville("kenitra").idProduit(5)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Oussama Touijer ")
                    .ville("Sale").idProduit(6)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Nora Samawi ")
                    .ville("Marrakech").idProduit(3)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Hayat Nanah ")
                    .ville("Tanger").idProduit(2)
                    .build());
        };
    }
}
