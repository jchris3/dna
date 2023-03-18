package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.util.Date;
import java.util.List;

@Service
public interface AssureService {
    List<AssureDTO> listAssureDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws AssureDtoException;
    void writeAssureCsv(List<AssureDTO> assureDTOS, Writer writer);
    ByteArrayInputStream load(List<AssureDTO> assureDTOS);

}
