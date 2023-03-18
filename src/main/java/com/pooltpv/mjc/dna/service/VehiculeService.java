package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.entities.Vehicule;
import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface VehiculeService {
    List<VehiculeDTO> listVehiculeDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws VehiculeDtoException;
   // void writeVehiculeCsv(List<VehiculeDTO> vehiculeDTOS, Writer writer);

    ByteArrayInputStream load(List<VehiculeDTO> vehiculeDTOS) throws IOException, ParseException;
}
