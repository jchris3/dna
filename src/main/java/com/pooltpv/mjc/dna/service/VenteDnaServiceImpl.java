package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VenteDnaDTO;
import com.pooltpv.mjc.dna.entities.VenteDna;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.VenteDnaRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
@Data
public class VenteDnaServiceImpl implements VenteDnaService {
    private final VenteDnaRepository venteDnaRepository;
    private final ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();


    @Override
    public List<VenteDnaDTO> listVenteDnaDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException {

        List<VenteDnaDTO> venteDnaDTOList = venteDnaRepository.findVentesDna(codeCompagnie,dateDebut,dateFin)
                .stream()
                .map(venteDna->mapToDto(venteDna)).collect(Collectors.toList());
        if(venteDnaDTOList.isEmpty()) throw new NoSuchElementException();
        return venteDnaDTOList;
    }

    @Override
    public ByteArrayInputStream load(List<VenteDnaDTO> venteDnaDTOS) throws IOException, ParseException {

        ByteArrayInputStream in = CSVHelper.venteDnaToCSV(venteDnaDTOS);
        return in;
    }

    private VenteDnaDTO mapToDto(VenteDna venteDna) {
        VenteDnaDTO venteDnaDTO= modelMapper.map(venteDna, VenteDnaDTO.class);
        return venteDnaDTO;
    }
}
