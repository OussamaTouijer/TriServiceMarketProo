package com.example.acheteur_service.service;

import com.example.acheteur_service.dto.AcheteurRequestDTO;
import com.example.acheteur_service.dto.AcheteurResponseDTO;

import java.util.List;

public interface AcheteurServiceInterface {
    public void save(AcheteurRequestDTO acheteurRequestDTO) ;
    public void delete(Integer id) ;
    public void update(Integer id ,AcheteurRequestDTO acheteurRequestDTO) ;
    public List<AcheteurResponseDTO> getAll();
    public AcheteurResponseDTO getAcheteurById(Integer id);

}
