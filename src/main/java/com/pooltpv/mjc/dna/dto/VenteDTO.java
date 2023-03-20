package com.pooltpv.mjc.dna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    private String numeroAttestation;
    private String immatriculation;
    private String codeAssure;
    private String codeAssureur;
    private String codeIntermediaireDna;
    private int primeNetteRC;
    private int dta;
}
