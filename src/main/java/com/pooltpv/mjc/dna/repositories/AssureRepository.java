package com.pooltpv.mjc.dna.repositories;

import com.pooltpv.mjc.dna.entities.Assure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface AssureRepository extends JpaRepository<Assure,String> {
    @Query(value = " SELECT DISTINCT to_char(CODEASSU) AS code_assure,\n" +
            "U_CLE_P, \n"  +
            "   :codeAssureur AS code_assureur,\n" +
            "   CASE \n" +
            "        WHEN qualite='PP' THEN 'PERSONE PHYSIQUE'\n" +
            "        WHEN qualite='PM' THEN 'PERSONNE MORALE'\n" +
            "     END  AS qualite,\n" +
            "    ASSURE_NOM AS nom,\n" +
            "    PRENASSU AS prenom,\n" +
            "    PROFESSION AS profession,\n" +
            "    DATENAIS AS DATE_NAISSANCE,\n" +
            "    'N/A                                                                                                                                                                                                                                                                                                                                                                                                                                               ' AS num_contribuable, \n" +
            "    LIBEVILL AS ville,\n" +
            "    MOBILE AS telephone,\n" +
            "    EMAIL AS email,\n" +
            "    NUMEPERM AS NUMERO_PERMIS,\n" +
            "    CODTYPPE AS CATEGORIE_PERMIS,\n" +
            "    DATDELPE AS DATE_DELIVRANCE,\n" +
            "    conducteur AS nom_prenom_conducteur,\n" +
            "    datenais as date_naissance_conducteur\n" +
            " FROM ORASSADM.CIE_POOL_POL_RISQUE \n" +
            " WHERE \n" +
            "    DATESAIS BETWEEN :dateDebut AND :dateFin \n" +
            "    AND CODE_CIE=:codeCompagnie"
            ,nativeQuery = true)
    List<Assure> findAssures(@Param("codeCompagnie") int codeCompagnie,@Param("codeAssureur") int codeAssureur,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
}

