package com.pooltpv.mjc.dna.mappers;

import com.pooltpv.mjc.dna.dto.AttestationDnaDTO;
import com.pooltpv.mjc.dna.entities.AttestationDna;

public interface AttestationsDnaMapper {
    AttestationDnaDTO fromAttestationDna(AttestationDna attestationDna);
}
