package com.pooltpv.mjc.dna.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AttestationDnaDTO {
    private String numeroAttestation;
    private String numeroPolice;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEmission;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEffet;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEcheance;
    private String couleur;
    private String status;
    private String zoneCirculation;
    private String immatriculation;
    private String codeClient;
    private int codeAssureur;
    private String bonLivraison;
}
