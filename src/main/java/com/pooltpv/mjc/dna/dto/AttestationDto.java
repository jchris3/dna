package com.pooltpv.mjc.dna.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AttestationDto {
    private String numeroAttestation;
    private String numeroPolice;
    private Date dateEmission;
    private Date dateEffet;
    private Date dateEcheance;
    private String couleur;
    private String statut;
    private String zoneCirculation;
    private String immatriculation;
    private int remorque;
    private String codeAssure;
    private int codeAssureur;
}
