package com.example.produit_service.service;

import com.example.produit_service.dto.ProduitRequestDTO;
import com.example.produit_service.dto.ProduitResponseDTO;

import java.util.List;

public interface ProduitServiceInterface {
    public void save(ProduitRequestDTO produitRequestDTO) ;
    public void delete(Integer id) ;
    public void update(Integer id , ProduitRequestDTO produitRequestDTO) ;
    public List<ProduitResponseDTO> getAll();
    public ProduitResponseDTO getProduitById(Integer id);
}
