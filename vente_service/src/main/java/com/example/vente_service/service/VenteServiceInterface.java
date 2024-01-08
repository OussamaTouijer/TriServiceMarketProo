package com.example.vente_service.service;

import com.example.vente_service.dto.VenteRequestDTO;
import com.example.vente_service.dto.VenteResponseDTO;

import java.util.List;

public interface VenteServiceInterface {
    public void save(VenteRequestDTO venteRequestDTO) ;
    public void delete(Integer id) ;
    public void update(Integer id ,VenteRequestDTO venteRequestDTO) ;
    public List<VenteResponseDTO> getAll();
    public VenteResponseDTO getVenteById(Integer id);
}
