package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VehiculeDnaDTO;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface VehiculeDnaService {
    List<VehiculeDnaDTO> listVehiculeDnaDTO(int codeCompagnie,Date dateDebut, Date dateFin) throws VehiculeDtoException;
    void writeVehiculeDnaCsv(List<VehiculeDnaDTO> vehiculeDnaDTOS, Writer writer);

    ByteArrayInputStream load(List<VehiculeDnaDTO> vehiculeDnaDTOS) throws IOException, ParseException;
}
