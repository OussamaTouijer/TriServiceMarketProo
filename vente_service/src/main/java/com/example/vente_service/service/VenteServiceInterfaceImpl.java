package com.example.vente_service.service;

import com.example.vente_service.acheteurs.AcheteurOpenFeing;
import com.example.vente_service.dto.VenteRequestDTO;
import com.example.vente_service.dto.VenteResponseDTO;
import com.example.vente_service.entities.Vente;
import com.example.vente_service.mapper.VenteInterfaceMap;
import com.example.vente_service.module.Acheteur;
import com.example.vente_service.module.Produit;
import com.example.vente_service.produits.ProduitOpenFeing;
import com.example.vente_service.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

@Transactional
public class VenteServiceInterfaceImpl implements VenteServiceInterface {

    @Autowired
    VenteRepository venteRepository;

    @Autowired
    VenteInterfaceMap venteInterfaceMap;

    @Autowired
    ProduitOpenFeing produitOpenFeing;

    @Autowired
    AcheteurOpenFeing acheteurOpenFeing;


    @Override
    public void save(VenteRequestDTO venteRequestDTO) {
        Vente v =new Vente();
        v=venteInterfaceMap.requestDTO_ToVente(venteRequestDTO);
        venteRepository.save(v);
    }

    @Override
    public void delete(Integer id) { venteRepository.deleteById(id);}

    @Override
    public void update(Integer id, VenteRequestDTO venteRequestDTO) {

        Vente v=venteRepository.findById(id).get();
        if(venteRequestDTO.getIdV() != null) v.setIdV(venteRequestDTO.getIdV());
        if(venteRequestDTO.getIdA() != null) v.setIdA(venteRequestDTO.getIdA());
        if(venteRequestDTO.getIdP() != null) v.setIdP(venteRequestDTO.getIdP());

        venteRepository.save(v);
    }

    @Override
    public List<VenteResponseDTO> getAll() {
        List<Produit> lp=produitOpenFeing.getAll();
        List<Acheteur> la=acheteurOpenFeing.getAll();

        List<Vente> Liste_ventes= new ArrayList<Vente>();
        Liste_ventes=venteRepository.findAll();
        List<VenteResponseDTO> Liste_ventesRespenseDTO=new ArrayList<VenteResponseDTO>();

        for (Vente vente:Liste_ventes)
        {
            for(Acheteur acheteur:la){
                if(vente.getIdA()==acheteur.getIdAcheteur()){ vente.setAcheteur(acheteur); break;}
            }
            for(Produit produit:lp){
                if(vente.getIdP()==produit.getIdProduit()){ vente.setProduit(produit); break;}
            }
            VenteResponseDTO r=venteInterfaceMap.vente_ToResponseDTO(vente);
            Liste_ventesRespenseDTO.add(r);

        }

        return Liste_ventesRespenseDTO;
    }

    @Override
    public VenteResponseDTO getVenteById(Integer id) {
        Vente vente=venteRepository.findById(id).get();
        Acheteur acheteur=acheteurOpenFeing.getById(vente.getIdA());
        Produit produit=produitOpenFeing.getById(vente.getIdP());
        vente.setAcheteur(acheteur);
        vente.setProduit(produit);
        return venteInterfaceMap.vente_ToResponseDTO(vente);
    }
}
