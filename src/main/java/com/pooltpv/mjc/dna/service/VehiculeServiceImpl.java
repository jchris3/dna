package com.pooltpv.mjc.dna.service;

import com.jayway.jsonpath.JsonPath;
import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.entities.Vehicule;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.VehiculeRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
public class VehiculeServiceImpl implements VehiculeService {
    private VehiculeRepository vehiculeRepository;
    private ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();
    private static final Logger log = getLogger(VehiculeService.class);

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, ModelMapper modelMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VehiculeDTO> listVehiculeDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException {

        List<VehiculeDTO> vehiculeDTOList = vehiculeRepository.findVehicules(codeCompagnie,codeAssureur,dateDebut,dateFin)
                .stream()
                .map(vehicule->mapToDto(vehicule)).collect(Collectors.toList());
        if(vehiculeDTOList.isEmpty()) throw new NoSuchElementException();
        return vehiculeDTOList;
    }

    @Override
    public ByteArrayInputStream load(List<VehiculeDTO> vehiculeDTOS) throws IOException, ParseException {

        ByteArrayInputStream in = CSVHelper.vehiculesToCSV(vehiculeDTOS);
        return in;
    }

    private VehiculeDTO mapToDto(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO= modelMapper.map(vehicule, VehiculeDTO.class);
        return vehiculeDTO;
    }
}
