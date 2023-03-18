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
public class VenteDna {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "ATTESTATION")
    private String numeroAttestation;
    @Column(name = "immatriculation")
    private String immatriculationVehicule;
    @Column(name = "code_assure")
    private String codeClient;
    @Column(name = "code_intermediaire")
    private String codeIntermediaire;
    @Column(name = "prime_nette_RC")
    private String primeNetteRC;
    @Column(name = "prime_nette_globale")
    private String primeNetteGlobale;

    private String dta;

}
