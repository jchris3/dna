package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VenteDTO;
import com.pooltpv.mjc.dna.dto.VenteDnaDTO;
import com.pooltpv.mjc.dna.entities.Vente;
import com.pooltpv.mjc.dna.entities.VenteDna;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.VenteDnaRepository;
import com.pooltpv.mjc.dna.repositories.VenteRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;
    private final ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();


    @Override
    public List<VenteDTO> listVenteDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException {

        List<VenteDTO> venteDTOList = venteRepository.findVentes(codeCompagnie,codeAssureur,dateDebut,dateFin)
                .stream()
                .map(vente->mapToDto(vente)).collect(Collectors.toList());
        if(venteDTOList.isEmpty()) throw new NoSuchElementException();
        return venteDTOList;
    }

    @Override
    public ByteArrayInputStream load(List<VenteDTO> venteDTOS) throws IOException, ParseException {

        ByteArrayInputStream in = CSVHelper.venteToCSV(venteDTOS);
        return in;
    }

    private VenteDTO mapToDto(Vente vente) {
        VenteDTO venteDTO= modelMapper.map(vente, VenteDTO.class);
        return venteDTO;
    }
}
