package com.pooltpv.mjc.dna.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class AttestationDna {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "ATTESTATION")
    private String numeroAttestation;

    @Column(name = "numero_police")
    private String numeroPolice;

    @Column(name = "date_emission")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEmission;

    @Column(name = "date_effet")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEffet;

    @Column(name = "date_echeance")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEcheance;

    private String couleur;

    private String status;

    @Column(name = "zone_circulation")
    private String zoneCirculation;

    private String immatriculation;

    @Column(name = "code_assure")
    private String codeClient;

    @Column(name = "code_assureur")
    private int codeAssureur;

    @Column(name = "bon_livraison")
    private String bonLivraison;
}
