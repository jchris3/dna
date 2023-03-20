package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VenteDTO;
import com.pooltpv.mjc.dna.dto.VenteDnaDTO;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface VenteService {
    List<VenteDTO> listVenteDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException;

    ByteArrayInputStream load(List<VenteDTO> venteDTOS) throws IOException, ParseException;
}
