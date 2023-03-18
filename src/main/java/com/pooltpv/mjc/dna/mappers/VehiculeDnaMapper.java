package com.pooltpv.mjc.dna.mappers;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.VehiculeDnaDTO;
import com.pooltpv.mjc.dna.entities.VehiculeDna;

public interface VehiculeDnaMapper {
    VehiculeDnaDTO fromVehiculeDna(VehiculeDna vehiculeDna);
}
