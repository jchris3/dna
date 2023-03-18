package com.pooltpv.mjc.dna.service;


import com.pooltpv.mjc.dna.dto.AssureDTO;
import com.pooltpv.mjc.dna.dto.AssureDnaDTO;
import com.pooltpv.mjc.dna.entities.Assure;
import com.pooltpv.mjc.dna.entities.AssureDna;
import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import com.pooltpv.mjc.dna.helper.CSVHelper;
import com.pooltpv.mjc.dna.repositories.AssureDnaRepository;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
@Data
public class AssureDnaServiceImpl implements AssureDnaService {
    private AssureDna assureDna;
    private static final Logger log = getLogger(AssureDnaService.class);
    private final AssureDnaRepository assureDnaRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<AssureDnaDTO> listAssureDnaDTO(int codeCompagnie, Date dateDebut, Date dateFin) throws AssureDtoException {

        List<AssureDnaDTO> assureDnaDTOList = assureDnaRepository.findAssuresDna(codeCompagnie,dateDebut,dateFin)
                .stream()
                .map(assureDna->mapToDto(assureDna)).collect(Collectors.toList());
        if(assureDnaDTOList.isEmpty()) assureDnaDTOList.add(new AssureDnaDTO());
        return assureDnaDTOList;
    }

    public void writeAssureDnaCsv(List<AssureDnaDTO> assureDnaDTOS, Writer writer) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('\t').withHeader("code_assure", "code_assureur", "qualite"
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
    public ByteArrayInputStream load(List<AssureDnaDTO> assureDnaDTOS) throws ParseException {

        ByteArrayInputStream in = CSVHelper.assuresDnaToCSV(assureDnaDTOS);
        return in;
    }

    private AssureDnaDTO mapToDto(AssureDna assureDna){
        AssureDnaDTO assureDnaDTO= modelMapper.map(assureDna,AssureDnaDTO.class);
        return assureDnaDTO;
    }
}
