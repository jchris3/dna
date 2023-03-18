package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AttestationDnaDTO;
import com.pooltpv.mjc.dna.dto.AttestationDto;
import com.pooltpv.mjc.dna.entities.Attestation;
import com.pooltpv.mjc.dna.entities.AttestationDna;
import com.pooltpv.mjc.dna.exceptions.AttestationDtoException;
import com.pooltpv.mjc.dna.repositories.AttestationDnaRepository;
import com.pooltpv.mjc.dna.repositories.AttestationRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
@Data
public class AttestationDnaServiceImpl implements AttestationDnaService {
    private final AttestationDnaRepository attestationDnaRepository;
    private final ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();
    private static final Logger log = getLogger(VehiculeService.class);


    @Override
    public List<AttestationDnaDTO> listAttestationDnaDtos(int codeCompagnie, Date dateDebut, Date dateFin) throws AttestationDtoException {
        List<AttestationDnaDTO> attestationDnaDtoList = attestationDnaRepository.findAttestationsDna(codeCompagnie,dateDebut,dateFin)
                .stream()
                .map(attestationDna->mapToDto(attestationDna)).collect(Collectors.toList());
        if(attestationDnaDtoList.isEmpty()) throw new NoSuchElementException();
        return attestationDnaDtoList;
    }

  @Override
    public void writeAttestationDnaCsv(List<AttestationDnaDTO> attestationDnaDtos, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.TDF.withDelimiter('\t').withHeader("numero_attestation", "numero_police", "date_emission"
                ,"date_effet","date_echeance","couleur","status","zone_circulation","immatriculation",
               "code_client","code_assureur","bon_livraison"))) {
            for (AttestationDnaDTO attestationDnaDTO : attestationDnaDtos) {
                if(attestationDnaDTO.getNumeroAttestation()!=null) {
                    csvPrinter.printRecord(attestationDnaDTO.getNumeroAttestation(), attestationDnaDTO.getNumeroPolice(), dateFormatter.setDateFormatSimple(attestationDnaDTO.getDateEmission())
                            , dateFormatter.setDateFormatSimple(attestationDnaDTO.getDateEffet()), dateFormatter.setDateFormatSimple(attestationDnaDTO.getDateEcheance())
                            , attestationDnaDTO.getCouleur(), attestationDnaDTO.getStatus(), attestationDnaDTO.getZoneCirculation(), attestationDnaDTO.getImmatriculation()
                            , attestationDnaDTO.getCodeClient(), attestationDnaDTO.getCodeAssureur(), attestationDnaDTO.getBonLivraison());
                }
            }
        } catch (IOException | ParseException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    private AttestationDnaDTO mapToDto(AttestationDna attestationDna) {
        AttestationDnaDTO attestationDnaDTO= modelMapper.map(attestationDna, AttestationDnaDTO.class);
        return attestationDnaDTO;
    }
}
