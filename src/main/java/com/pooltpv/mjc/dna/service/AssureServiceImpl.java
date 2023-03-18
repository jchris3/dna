package com.pooltpv.mjc.dna.service;


import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.AssureRepository;
import com.pooltpv.mjc.dna.entities.Assure;
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
public class AssureServiceImpl implements AssureService {
    private Assure assure;
    private static final Logger log = getLogger(AssureService.class);
    AssureRepository assureRepository;
    ModelMapper modelMapper;

    public AssureServiceImpl(AssureRepository assureRepository,ModelMapper modelMapper) {
        this.assureRepository = assureRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<AssureDTO> listAssureDTO(int codeCompagnie, int codeAssureur, Date dateDebut, Date dateFin) throws AssureDtoException {

        List<AssureDTO> assureDTOList = assureRepository.findAssures(codeCompagnie,codeAssureur,dateDebut,dateFin)
                .stream()
                .map(assure->mapToDto(assure)).collect(Collectors.toList());
        if(assureDTOList.isEmpty()) assureDTOList.add(new AssureDTO());
        return assureDTOList;
    }

    public void writeAssureCsv(List<AssureDTO> assureDTOS, Writer writer) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';').withHeader("code_assure", "code_assureur", "qualite"
                ,"nom","prenom","profession","date_naissance","num_contribuable","ville",
                "rue","boite_postal","telephone","email","numero_permis","categorie_permis","date_delivrance",
                "permis_delivre_par","nom_prenom_conducteur","date_naissance_conducteur"))) {
            /*for (AssureDTO assureDTO : assureDTOS) {
                csvPrinter.printRecord(assureDTO.getCode_assure(),assureDTO.getCode_assureur(),assureDTO.getQualite(),assureDTO.getNom()
                ,assureDTO.getPrenom(),assureDTO.getProfession(),formatter1.format(formatter.parse(assureDTO.getDATE_NAISSANCE())),assureDTO.getNum_contribuable(),
                        assureDTO.getVille(),assureDTO.getRue(),assureDTO.getBoite_postal(),assureDTO.getTelephone(),assureDTO.getEmail(),
                        assureDTO.getNUMERO_PERMIS(),assureDTO.getCATEGORIE_PERMIS(),formatter1.format(formatter.parse(assureDTO.getDATE_DELIVRANCE())),assureDTO.getPERMIS_DELIVRE_PAR(),
                        assureDTO.getNom_prenom_conducteur(),formatter1.format(formatter.parse(assureDTO.getDate_naissance_conducteur())));
            }*/
        } catch (IOException /*| ParseException*/ e) {
            log.error("Error While writing CSV ", e);
        }
    }


    @Override
    public ByteArrayInputStream load(List<AssureDTO> assureDTOS) {

        ByteArrayInputStream in = CSVHelper.assuresToCSV(assureDTOS);
        return in;
    }

    private AssureDTO mapToDto(Assure assure){
        AssureDTO assureDTO= modelMapper.map(assure,AssureDTO.class);
        return assureDTO;
    }
}
