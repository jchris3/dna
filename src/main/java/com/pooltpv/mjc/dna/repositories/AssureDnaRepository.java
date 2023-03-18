package com.pooltpv.mjc.dna.repositories;

import com.pooltpv.mjc.dna.entities.AssureDna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface AssureDnaRepository extends JpaRepository<AssureDna,String> {
    @Query(value = " SELECT DISTINCT to_char(CODEASSU) AS code_assure,\n" +
            "U_CLE_P, \n"  +
            "    ASSURE_NOM AS nom,\n" +
            "    PRENASSU AS prenom,\n" +
            "    PROFESSION AS profession,\n" +
            "    DATENAIS AS DATE_NAISSANCE,\n" +
            "    LIBEVILL AS ville,\n" +
            "    '' AS rue,\n" +
            "    '' AS code_postal,\n" +
            "    MOBILE AS telephone,\n" +
            "    EMAIL AS email,\n" +
            "    NUMEPERM AS NUMERO_PERMIS,\n" +
            "    CODTYPPE AS CATEGORIE_PERMIS,\n" +
            "    DATDELPE AS DATE_DELIVRANCE,\n" +
            "    add_months(to_date(DATDELPE),120) AS DATE_EXPIRATION,\n" +
            "    '' AS permis_delivre_par \n" +
            " FROM ORASSADM.CIE_POOL_POL_RISQUE \n" +
            " WHERE \n" +
            "    DATESAIS BETWEEN :dateDebut AND :dateFin \n" +
            "    AND CODE_CIE=:codeCompagnie"
            ,nativeQuery = true)
    List<AssureDna> findAssuresDna(@Param("codeCompagnie") int codeCompagnie,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
}

