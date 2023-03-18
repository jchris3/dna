package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AttestationDnaDTO;
import com.pooltpv.mjc.dna.exceptions.AttestationDtoException;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.Date;
import java.util.List;

@Service
public interface AttestationDnaService {
    List<AttestationDnaDTO> listAttestationDnaDtos(int codeCompagnie, Date dateDebut, Date dateFin) throws AttestationDtoException;
    void writeAttestationDnaCsv(List<AttestationDnaDTO> attestationDnaDtos, Writer writer);
}
