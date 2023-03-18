package com.pooltpv.mjc.dna.repositories;

import com.pooltpv.mjc.dna.entities.Vehicule;
import com.pooltpv.mjc.dna.entities.VehiculeDna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface VehiculeDnaRepository extends JpaRepository<VehiculeDna,String> {
    @Query(value = "select \n" +
            "    U_CLE_P, \n"+
            "    marque AS mark,\n" +
            "    TYPEVEHI AS modele,\n" +
            "    DATE_PREM_MEC AS date_premiere_mise_circulation,\n" +
            "    immatriculation,\n" +
            "    chassis,\n" +
            "    CASE \n" +
            "        WHEN CODECATE='40' THEN 'IVA'\n" +
            "        WHEN CODECATE='42' THEN 'IVB'\n" +
            "        WHEN CODECATE='44' THEN 'IVC'\n" +
            "     END  AS categorie,\n" +
            "    '' AS capacite_charge, \t\n" +
            "    ASPUISSANCE AS puissance_fiscale,\n" +
            "    0 AS remorque,\n" +
            "    '' AS nombre_portes,\n" +
            "    '' AS immatriculation_remorque,\n" +
            "    CASE \n" +
            "        WHEN SE='E' THEN 'ESSENCE'\n" +
            "        WHEN SE='D' THEN 'DIESEL'\n" +
            "     END  AS  source_energie,\n" +
            "    PLACE as nombre_de_places,\n" +
            "   '' AS cylindre,\n" +
            "    0 AS double_commande,\n" +
            "    0 AS reponsabilite_civile,\n" +
            "    0 AS utilitaire,\n" +
            "    0 AS type_engins,\n" +
            "    0 AS poids" +
            " from ORASSADM.CIE_POOL_POL_RISQUE \n" +
            " WHERE \n" +
            "    DATESAIS BETWEEN :dateDebut AND :dateFin \n" +
            "    AND CODE_CIE=:codeCompagnie"
            ,nativeQuery = true)
    List<VehiculeDna> findVehiculesDna(@Param("codeCompagnie") int codeCompagnie, @Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}

