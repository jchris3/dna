package com.pooltpv.mjc.dna.repositories;

import com.pooltpv.mjc.dna.entities.AttestationDna;
import com.pooltpv.mjc.dna.entities.VenteDna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface VenteDnaRepository extends JpaRepository<VenteDna,String> {
    @Query(value = "  select\n" +
            "        U_CLE_P,\n" +
            "        CASE                 \n" +
            "            WHEN attestation like '01%' THEN CONCAT('101*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '101*T*%' THEN CONCAT('101*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '02%' THEN CONCAT('102*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '102*%' THEN CONCAT('102*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '03%' THEN CONCAT('103*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '103*%' THEN CONCAT('103*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '04%' THEN CONCAT('104*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '104*%' THEN CONCAT('104*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '05%' THEN CONCAT('105*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '105*%' THEN CONCAT('105*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '06%' THEN CONCAT('106*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '106*%' THEN CONCAT('106*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))         \n" +
            "            WHEN attestation like '07%' THEN CONCAT('107*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))          \n" +
            "            WHEN attestation like '107*%' THEN CONCAT('107*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '08%' THEN CONCAT('108*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '108*%' THEN CONCAT('108*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '09%' THEN CONCAT('109*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '109*%' THEN CONCAT('109*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '10%' THEN CONCAT('110*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '110*%' THEN CONCAT('110*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '11%' THEN CONCAT('111*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '111*%' THEN CONCAT('111*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '12%' THEN CONCAT('112*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '112*%' THEN CONCAT('112*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '13%' THEN CONCAT('113*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '113*%' THEN CONCAT('113*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '14%' THEN CONCAT('114*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '114*%' THEN CONCAT('114*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '15%' THEN CONCAT('115*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '115*%' THEN CONCAT('115*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '16%' THEN CONCAT('116*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '116*%' THEN CONCAT('116*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '17%' THEN CONCAT('117*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))                   \n" +
            "            WHEN attestation like '117*%' THEN CONCAT('117*T*',\n" +
            "            SUBSTR(attestation,\n" +
            "            -7,\n" +
            "            7))     \n" +
            "        END AS ATTESTATION,\n" +
            "        immatriculation,\n" +
            "        \n" +
            "        codeassu as code_assure,\n" +
            "        codeintesous as code_intermediaire,\n" +
            "        rc as prime_nette_RC,\n" +
            "        prime_nette as prime_nette_globale,\n" +
            "        droit_timbre as dta \n"+
            " from \n"+
            "    ORASSADM.CIE_POOL_POL_RISQUE \n"+
            " WHERE \n" +
            "    DATESOUS BETWEEN :dateDebut AND :dateFin \n" +

            "    AND CODE_CIE=:codeCompagnie"
            ,nativeQuery = true)
    List<VenteDna> findVentesDna(@Param("codeCompagnie") int codeCompagnie, @Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}

