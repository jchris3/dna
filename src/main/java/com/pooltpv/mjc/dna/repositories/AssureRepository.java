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
<<<<<<< HEAD
    @Query(value = " SELECT DISTINCT to_char(ORASSADM.CIE_POOL_POL_RISQUE.CODEASSU) AS code_assure,\n" +
=======
    @Query(value = " SELECT DISTINCT to_char(CODEASSU) AS code_assure,\n" +
>>>>>>> a9a3409530c57731f62d83d3161ef4bf0db0294f
            "U_CLE_P, \n"  +
            "   :codeAssureur AS code_assureur,\n" +
            "   CASE \n" +
            "        WHEN qualite='PP' THEN 'PERSONE PHYSIQUE'\n" +
            "        WHEN qualite='PM' THEN 'PERSONNE MORALE'\n" +
            "     END  AS qualite,\n" +
            "    ASSURE_NOM AS nom,\n" +
<<<<<<< HEAD
            "    ORASSADM.CIE_POOL_POL_RISQUE.PRENASSU AS prenom,\n" +
            "    PROFESSION AS profession,\n" +
            "    DATENAIS AS DATE_NAISSANCE,\n" +
            "    ASSURE.CODTYPPI as code_type_piece,\n" +
            "    ASSURE.NUMPIEID as num_contribuable,\n" +
=======
            "    PRENASSU AS prenom,\n" +
            "    PROFESSION AS profession,\n" +
            "    DATENAIS AS DATE_NAISSANCE,\n" +
            "    'N/A                                                                                                                                                                                                                                                                                                                                                                                                                                               ' AS num_contribuable, \n" +
>>>>>>> a9a3409530c57731f62d83d3161ef4bf0db0294f
            "    LIBEVILL AS ville,\n" +
            "    MOBILE AS telephone,\n" +
            "    EMAIL AS email,\n" +
            "    NUMEPERM AS NUMERO_PERMIS,\n" +
            "    CODTYPPE AS CATEGORIE_PERMIS,\n" +
            "    DATDELPE AS DATE_DELIVRANCE,\n" +
            "    conducteur AS nom_prenom_conducteur,\n" +
            "    datenais as date_naissance_conducteur\n" +
<<<<<<< HEAD
            " FROM ORASSADM.CIE_POOL_POL_RISQUE,ASSURE\n" +
            " WHERE \n" +
            "  ASSURE.CODEASSU = ORASSADM.CIE_POOL_POL_RISQUE.CODEASSU\n" +
            "   AND DATESAIS BETWEEN :dateDebut AND :dateFin \n" +
            "   AND CODE_CIE=:codeCompagnie"
=======
            " FROM ORASSADM.CIE_POOL_POL_RISQUE \n" +
            " WHERE \n" +
            "    DATESAIS BETWEEN :dateDebut AND :dateFin \n" +
            "    AND CODE_CIE=:codeCompagnie"
>>>>>>> a9a3409530c57731f62d83d3161ef4bf0db0294f
            ,nativeQuery = true)
    List<Assure> findAssures(@Param("codeCompagnie") int codeCompagnie,@Param("codeAssureur") int codeAssureur,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
}

