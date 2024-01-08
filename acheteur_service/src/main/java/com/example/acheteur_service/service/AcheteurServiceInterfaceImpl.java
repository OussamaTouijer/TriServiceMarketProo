package com.example.acheteur_service.service;

import com.example.acheteur_service.dto.AcheteurRequestDTO;
import com.example.acheteur_service.dto.AcheteurResponseDTO;
import com.example.acheteur_service.entitise.Acheteur;
import com.example.acheteur_service.mapper.AcheteurInterfaceMap;
import com.example.acheteur_service.module.Produit;
import com.example.acheteur_service.produit.ProduitFindController;
import com.example.acheteur_service.repositories.AcheteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

@Transactional
public class AcheteurServiceInterfaceImpl implements AcheteurServiceInterface{

    @Autowired
    ProduitFindController produitFindController;
    @Autowired
    AcheteurRepository acheteurRepository;
    @Autowired
    AcheteurInterfaceMap acheteurInterfaceMap;

    @Override
    public void save(AcheteurRequestDTO acheteurRequestDTO) {
        Acheteur a=new Acheteur();
        a=acheteurInterfaceMap.requestDTO_ToAcheteur(acheteurRequestDTO);
        acheteurRepository.save(a);
    }

    @Override
    public void delete(Integer id) {acheteurRepository.deleteById(id);}


    @Override
    public void update(Integer id, AcheteurRequestDTO acheteurRequestDTO) {
        Acheteur e=acheteurRepository.findById(id).get();
        if(acheteurRequestDTO.getNom() != null) e.setNom(acheteurRequestDTO.getNom());
        if(acheteurRequestDTO.getPrenom() != null) e.setPrenom(acheteurRequestDTO.getPrenom());
        if(acheteurRequestDTO.getEmail() != null) e.setEmail(acheteurRequestDTO.getEmail());
        if(acheteurRequestDTO.getAdresse() != null) e.setAdresse(acheteurRequestDTO.getAdresse());
        if(acheteurRequestDTO.getVille() != null) e.setVille(acheteurRequestDTO.getVille());
        acheteurRepository.save(e);
    }

    

    @Override
    public List<AcheteurResponseDTO> getAll() {
        List<Produit> lp=produitFindController.getAll();
        List<Acheteur> Liste_acheteurs= new ArrayList<Acheteur>();
        Liste_acheteurs=acheteurRepository.findAll();
        List<AcheteurResponseDTO> Liste_acheteursRespenseDTO=new ArrayList<AcheteurResponseDTO>();

        for (Acheteur a:Liste_acheteurs)
        {
            for(Produit p:lp) {
                if (Objects.equals(a.getIdProduit(), p.getIdProduit())) {
                    a.setProduit(p);
                    AcheteurResponseDTO r = acheteurInterfaceMap.acheteur_ToResponseDTO(a);
                    Liste_acheteursRespenseDTO.add(r);
                }
            }

        }

        return Liste_acheteursRespenseDTO;
    }

    @Override
    public AcheteurResponseDTO getAcheteurById(Integer id) {
        Acheteur a=acheteurRepository.findById(id).get();
        Produit p=produitFindController.getById(a.getIdProduit());
        a.setProduit(p);
        return acheteurInterfaceMap.acheteur_ToResponseDTO(a);
    }
}
