package com.pooltpv.mjc.dna.service;

import com.pooltpv.mjc.dna.dto.VehiculeDTO;
import com.pooltpv.mjc.dna.dto.VehiculeDnaDTO;
import com.pooltpv.mjc.dna.entities.Vehicule;
import com.pooltpv.mjc.dna.entities.VehiculeDna;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.VehiculeDnaRepository;
import com.pooltpv.mjc.dna.repositories.VehiculeRepository;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
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
public class VehiculeDnaServiceImpl implements VehiculeDnaService {
    private final VehiculeDnaRepository vehiculeDnaRepository;
    private final ModelMapper modelMapper;
    private DateFormatter dateFormatter = new DateFormatter();
    private static final Logger log = getLogger(VehiculeService.class);


    @Override
    public List<VehiculeDnaDTO> listVehiculeDnaDTO(int codeCompagnie, Date dateDebut, Date dateFin) throws VehiculeDtoException {

        List<VehiculeDnaDTO> vehiculeDnaDTOList = vehiculeDnaRepository.findVehiculesDna(codeCompagnie,dateDebut,dateFin)
                .stream()
                .map(vehiculeDna->mapToDto(vehiculeDna)).collect(Collectors.toList());
        if(vehiculeDnaDTOList.isEmpty()) throw new NoSuchElementException();
        return vehiculeDnaDTOList;
    }

    @Override
    public void writeVehiculeDnaCsv(List<VehiculeDnaDTO> vehiculeDnaDTOS, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';').withHeader("code_assure", "code_assureur", "marque"
                ,"modele","date_premiere_mise_circulation","immatriculation","chassis","usage","charge_utile",
                "puissance_fiscale","remorque","nombre_portes","immatriculation_remorque","source_energie","nombre_de_places","cylindree","double_commande",
                "reponsabilite_civile","utilitaire","type_engin","poids_total_autorise_en_charge"))) {
            for (VehiculeDnaDTO vehiculeDnaDTO : vehiculeDnaDTOS) {
                csvPrinter.printRecord(vehiculeDnaDTO.getMark()
                        ,vehiculeDnaDTO.getModele(),dateFormatter.setDateFormatSimple(vehiculeDnaDTO.getDatePremiereMiseCirculation()),vehiculeDnaDTO.getImmatriculation(),vehiculeDnaDTO.getChassis(),vehiculeDnaDTO.getCategorie(),vehiculeDnaDTO.getCapaciteCharge(),
                        vehiculeDnaDTO.getPuissanceFiscale(),vehiculeDnaDTO.getRemorque(),vehiculeDnaDTO.getNombrePortes(),vehiculeDnaDTO.getImmatriculationRemorque(),vehiculeDnaDTO.getSourceEnergie(),vehiculeDnaDTO.getNombreDePlaces(),vehiculeDnaDTO.getCylindre(),vehiculeDnaDTO.getDoubleCommande(),
                        vehiculeDnaDTO.getReponsabiliteCivile(),vehiculeDnaDTO.getUtilitaire(),vehiculeDnaDTO.getTypeEngin(),vehiculeDnaDTO.getPoidsTotalAutoriseEnCharge());
            }
        } catch (IOException | ParseException e) {
            log.error("Error While writing CSV ", e);
        }

    }

    @Override
    public ByteArrayInputStream load(List<VehiculeDnaDTO> vehiculeDnaDTOS) throws IOException, ParseException {

        ByteArrayInputStream in = CSVHelper.vehiculesDnaToCSV(vehiculeDnaDTOS);
        return in;
    }

    private VehiculeDnaDTO mapToDto(VehiculeDna vehiculeDna) {
        VehiculeDnaDTO vehiculeDnaDTO= modelMapper.map(vehiculeDna, VehiculeDnaDTO.class);
        return vehiculeDnaDTO;
    }
}
