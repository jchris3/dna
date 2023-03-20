package com.pooltpv.mjc.dna.web;

import com.pooltpv.mjc.dna.exceptions.DateDiffException;
import com.pooltpv.mjc.dna.exceptions.VehiculeDtoException;
import com.pooltpv.mjc.dna.service.VenteDnaService;
import com.pooltpv.mjc.dna.service.VenteService;
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
public class VenteController {

    private VenteService venteService;
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }
    @RequestMapping(path = "/ventes/{codeCompagnie}/{codeAssureur}/{dateDebut}/{dateFin}")
    public ResponseEntity<InputStreamResource> getAllVenteCsv(@PathVariable(name = "codeCompagnie") @NonNull int codeCompagnie,
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
                        String filename = "vente_"+codeAssureur+"_"+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+".txt";
                        InputStreamResource file = new InputStreamResource(venteService.load(venteService.listVenteDTO(codeCompagnie,codeAssureur,startDate, endDate)));

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
