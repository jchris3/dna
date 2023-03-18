package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.dto.VenteDnaDTO;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface VenteDnaService {
    List<VenteDnaDTO> listVenteDnaDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException;

    ByteArrayInputStream load(List<VenteDnaDTO> venteDnaDTOS) throws IOException, ParseException;
}
