package com.pooltpv.mjc.dna.mappers;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.VenteDnaDTO;
import com.pooltpv.mjc.dna.entities.Assure;
import com.pooltpv.mjc.dna.entities.VenteDna;

public interface VenteDnaMapper {
    VenteDnaDTO fromVenteDna(VenteDna venteDna);
}
