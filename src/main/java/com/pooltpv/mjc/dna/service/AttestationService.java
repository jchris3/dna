package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AttestationDto;
import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.exceptions.AttestationDtoException;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.Date;
import java.util.List;

@Service
public interface AttestationService {
    List<AttestationDto> listAttestationDtos(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws AttestationDtoException;
    void writeAttestationCsv(List<AttestationDto> attestationDtos, Writer writer);
}
