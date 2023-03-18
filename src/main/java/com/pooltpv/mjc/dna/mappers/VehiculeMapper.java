package com.pooltpv.mjc.dna.mappers;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.entities.Assure;

public interface VehiculeMapper {
    AssureDTO fromAssure(Assure assure);
}
