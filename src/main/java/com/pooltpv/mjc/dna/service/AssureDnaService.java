package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.AssureDnaDTO;
import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface AssureDnaService {
    List<AssureDnaDTO> listAssureDnaDTO(int codeCompagnie, Date dateDebut, Date dateFin) throws AssureDtoException;
    void writeAssureDnaCsv(List<AssureDnaDTO> assureDnaDTOS, Writer writer);
    ByteArrayInputStream load(List<AssureDnaDTO> assureDnaDTOS) throws ParseException;

}
