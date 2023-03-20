package com.pooltpv.mjc.dna.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Vente {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "ATTESTATION")

    private String numeroAttestation;
    @Column(name = "immatriculation")
    private String immatriculation;
    @Column(name = "code_assure")
    private String codeAssure;
    @Column(name = "code_assureur")
    private String codeAssureur;
    @Column(name = "code_intermediaire_dna")
    private String codeIntermediaireDna;
    @Column(name = "prime_nette_RC")
    private int primeNetteRC;
    private int dta;

}
