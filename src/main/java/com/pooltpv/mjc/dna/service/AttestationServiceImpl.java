package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AttestationDto;
import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.entities.Attestation;
import com.pooltpv.mjc.dna.entities.Vehicule;
import com.pooltpv.mjc.dna.exceptions.AttestationDtoException;
import com.pooltpv.mjc.dna.repositories.AttestationRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
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
public class AttestationServiceImpl implements AttestationService {
    private AttestationRepository attestationRepository;
    private ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();
    private static final Logger log = getLogger(VehiculeService.class);

    public AttestationServiceImpl(AttestationRepository attestationRepository, ModelMapper modelMapper) {
        this.attestationRepository = attestationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AttestationDto> listAttestationDtos(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws AttestationDtoException {
        List<AttestationDto> attestationDtoList = attestationRepository.findAttestations(codeCompagnie,codeAssureur,dateDebut,dateFin)
                .stream()
                .map(attestation->mapToDto(attestation)).collect(Collectors.toList());
        if(attestationDtoList.isEmpty()) throw new NoSuchElementException();
        return attestationDtoList;
    }

    @Override
    public void writeAttestationCsv(List<AttestationDto> attestationDtos, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(',').withHeader("numero_attestation", "numero_police", "date_emission"
                ,"date_effet","date_echeance","couleur","statut","zone_circulation","immatriculation",
                "remorque","code_assure","code_assureur"))) {
            for (AttestationDto attestationDto : attestationDtos) {
                csvPrinter.printRecord(attestationDto.getNumeroAttestation(),attestationDto.getNumeroPolice(),dateFormatter.setDateFormatSimple(attestationDto.getDateEmission())
                        ,dateFormatter.setDateFormatSimple(attestationDto.getDateEffet()),dateFormatter.setDateFormatSimple(attestationDto.getDateEcheance())
                        ,attestationDto.getCouleur(),attestationDto.getStatut(),attestationDto.getZoneCirculation(),attestationDto.getImmatriculation(),
                        attestationDto.getRemorque(),attestationDto.getCodeAssure(),attestationDto.getCodeAssureur());
            }
        } catch (IOException | ParseException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    private AttestationDto mapToDto(Attestation attestation) {
        AttestationDto attestationDto= modelMapper.map(attestation, AttestationDto.class);
        return attestationDto;
    }
}
