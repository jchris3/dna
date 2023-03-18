package com.pooltpv.mjc.dna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDnaDTO {
    private String numeroAttestation;
    private String immatriculationVehicule;
    private String codeClient;
    private String codeIntermediaire;
    private String primeNetteRC;
    private String primeNetteGlobale;
    private String dta;
}
