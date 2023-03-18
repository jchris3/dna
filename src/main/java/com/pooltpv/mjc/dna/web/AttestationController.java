package com.pooltpv.mjc.dna.web;

import com.pooltpv.mjc.dna.exceptions.AttestationDtoException;
import com.pooltpv.mjc.dna.exceptions.DateDiffException;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.service.AttestationService;
import com.pooltpv.mjc.dna.service.VehiculeService;
import com.pooltpv.mjc.dna.utulities.DateValidatorUsingDateTimeFormatter;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

@RestController
@RequestMapping("/csv")
public class AttestationController {

    private AttestationService attestationService;
    private List<Integer> codeAssureurs = new ArrayList<>(Arrays.asList(101,102,103,104,105,106,107,108,109,
                                     110,112,113,114,115,116,117));

    public AttestationController(AttestationService attestationService) {
        this.attestationService = attestationService;
    }
    @RequestMapping(path = "/attestations/{codeCompagnie}/{codeAssureur}/{dateDebut}/{dateFin}")
    public void getAllVehiculeInCsv(@PathVariable(name = "codeCompagnie") @NonNull int codeCompagnie,
                                    @PathVariable(name = "codeAssureur") @NonNull int codeAssureur,
                                     @PathVariable(name = "dateDebut") @DateTimeFormat(pattern = "dd-MM-yyyy") String  dateDebut,
                                     @PathVariable(name = "dateFin")  @DateTimeFormat(pattern = "dd-MM-yyyy") String  dateFin,
                                     HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            dateFormatter.parse(dateDebut);
            dateFormatter.parse(dateFin);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.FRENCH);
            formatter.setTimeZone(TimeZone.getTimeZone("Africa/Douala"));
            Date startDate = formatter.parse(dateDebut + " 00:00:00");
            Date endDate = formatter.parse(dateFin + " 23:59:59");
            DateValidatorUsingDateTimeFormatter dateValidatorUsingDateTimeFormatter = new DateValidatorUsingDateTimeFormatter(startDate, endDate);
            if (dateValidatorUsingDateTimeFormatter.isDatediffValid(startDate, endDate) == true)
                //codeAssureurs.forEach(codeAssureur-> {
                    try {
                        String filename = "attestation-"+codeAssureur+"_"+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+".txt";
                        response.addHeader("Content-Disposition", "attachment; filename="+filename);
                        attestationService.writeAttestationCsv(attestationService.listAttestationDtos(codeCompagnie, codeAssureur,startDate,endDate),response.getWriter());
                    } catch (AttestationDtoException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
               // });
                //assureService.writeAssureCsv(assureService.listAssureDTO(codeCompagnie,codeAssureur,startDate,endDate),response.getWriter());
        }
        catch (Exception e){

            throw new DateDiffException(e.getMessage());
        }
    }
}
