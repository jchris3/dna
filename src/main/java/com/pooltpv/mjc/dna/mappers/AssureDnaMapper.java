package com.pooltpv.mjc.dna.mappers;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.AssureDnaDTO;
import com.pooltpv.mjc.dna.entities.Assure;
import com.pooltpv.mjc.dna.entities.AssureDna;

public interface AssureDnaMapper {
    AssureDnaDTO fromAssureDna(AssureDna assureDna);
}
