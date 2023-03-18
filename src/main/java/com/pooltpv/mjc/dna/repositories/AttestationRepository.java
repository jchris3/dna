package com.pooltpv.mjc.dna.repositories;

import com.pooltpv.mjc.dna.entities.Attestation;
import com.pooltpv.mjc.dna.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface AttestationRepository extends JpaRepository<Attestation,String> {
    @Query(value = "select \n" +
            "    U_CLE_P, \n"+
            "    CASE         \n" +
            "       WHEN attestation like '01%' THEN CONCAT('101*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '101*T*%' THEN CONCAT('101*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '02%' THEN CONCAT('102*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '102*%' THEN CONCAT('102*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '03%' THEN CONCAT('103*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '103*%' THEN CONCAT('103*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '04%' THEN CONCAT('104*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '104*%' THEN CONCAT('104*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '05%' THEN CONCAT('105*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '105*%' THEN CONCAT('105*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '06%' THEN CONCAT('106*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '106*%' THEN CONCAT('106*T*',SUBSTR(attestation,-7,7)) \n" +
            "       WHEN attestation like '07%' THEN CONCAT('107*T*',SUBSTR(attestation,-7,7))  \n" +
            "       WHEN attestation like '107*%' THEN CONCAT('107*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '08%' THEN CONCAT('108*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '108*%' THEN CONCAT('108*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '09%' THEN CONCAT('109*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '109*%' THEN CONCAT('109*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '10%' THEN CONCAT('110*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '110*%' THEN CONCAT('110*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '11%' THEN CONCAT('111*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '111*%' THEN CONCAT('111*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '12%' THEN CONCAT('112*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '112*%' THEN CONCAT('112*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '13%' THEN CONCAT('113*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '113*%' THEN CONCAT('113*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '14%' THEN CONCAT('114*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '114*%' THEN CONCAT('114*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '15%' THEN CONCAT('115*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '115*%' THEN CONCAT('115*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '16%' THEN CONCAT('116*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '116*%' THEN CONCAT('116*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '17%' THEN CONCAT('117*T*',SUBSTR(attestation,-7,7))           \n" +
            "       WHEN attestation like '117*%' THEN CONCAT('117*T*',SUBSTR(attestation,-7,7))           \n" +
            "      ELSE attestation \n"+
            "     END AS ATTESTATION, \n"+
            "   concat(concat(numepoli,'-'),codeintesous) as numero_police, \n"+
            "   datesous as date_emission, \n"+
            "   dateffet as date_effet,\n"+
            "   dateeche as date_echeance, \n"+
            "   'VERTE' as couleur, \n"+
            "   CASE \n"+
            "       WHEN BLG='Annulation' THEN 'ANNULEE' \n"+
            "       WHEN BLG='Ristourne' THEN 'RESILIEE' \n"+
            "   ELSE 'VENDUE' \n"+
            "   END  AS  statut, \n"+
            "   v_zone as zone_circulation, \n"+
            "   immatriculation, \n"+
            "   0 as remorque, \n"+
            "   codeassu as code_assure, \n"+
            "   :codeAssureur as code_assureur \n"+
            " from \n"+
            "    ORASSADM.CIE_POOL_POL_RISQUE \n"+
            " WHERE \n" +
            "    DATESOUS BETWEEN :dateDebut AND :dateFin \n" +

            "    AND CODE_CIE=:codeCompagnie"
            ,nativeQuery = true)
    List<Attestation> findAttestations(@Param("codeCompagnie") int codeCompagnie,@Param("codeAssureur") int codeAssureur,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
}

