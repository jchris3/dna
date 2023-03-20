package com.pooltpv.mjc.dna.web;

import com.pooltpv.mjc.dna.exceptions.DateDiffException;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.service.VehiculeService;
import com.pooltpv.mjc.dna.utulities.DateValidatorUsingDateTimeFormatter;
import lombok.NonNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class VehiculeController {

    private VehiculeService vehiculeService;
    private List<Integer> codeAssureurs = new ArrayList<>(Arrays.asList(101,102,103,104,105,106,107,108,109,
                                     110,112,113,114,115,116,117));

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }
    @RequestMapping(path = "/vehicules/{codeCompagnie}/{codeAssureur}/{dateDebut}/{dateFin}")
    public ResponseEntity<InputStreamResource> getAllVehiculeInCsv(@PathVariable(name = "codeCompagnie") @NonNull int codeCompagnie,
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
                        String filename = "vehicule_"+codeAssureur+"_"+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+".txt";
                        InputStreamResource file = new InputStreamResource(vehiculeService.load(vehiculeService.listVehiculeDTO(codeCompagnie, codeAssureur, startDate, endDate)));

                        return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                                .contentType(MediaType.parseMediaType("application/csv"))
                                .body(file);
                    } catch (VehiculeDtoException e) {
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
        return null;
    }
}
