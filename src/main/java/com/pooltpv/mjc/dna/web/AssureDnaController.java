package com.pooltpv.mjc.dna.web;

import com.pooltpv.mjc.dna.exceptions.AssureDtoException;
import com.pooltpv.mjc.dna.exceptions.DateDiffException;
import com.pooltpv.mjc.dna.service.AssureDnaService;
import com.pooltpv.mjc.dna.utulities.DateValidatorUsingDateTimeFormatter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

@RestController
@RequestMapping("/csv")
public class AssureDnaController {

    private AssureDnaService assureDnaService;
    private List<Integer> listCodeAssureurs = new ArrayList<>(Arrays.asList(101, 102, 103, 104, 105, 106, 107, 108, 109,
            110, 111, 112, 113, 114, 115, 116, 117));
    private List<Integer> listCodeCompagnies = new ArrayList<>(Arrays.asList(10000, 15000, 20000, 30000, 35000, 40000, 45000, 50000, 55000,
            60000, 65000, 70000, 75000, 80000, 85000, 90000, 95000));
    private Iterator<Integer> itListCodeAssureurs = listCodeAssureurs.listIterator();
    private Iterator<Integer> itlistCodeCompagniess = listCodeCompagnies.listIterator();

    public AssureDnaController(AssureDnaService assureDnaService) {
        this.assureDnaService = assureDnaService;
    }

    @RequestMapping(path = "/assuresDna/{dateDebut}/{dateFin}")
    public void getAllEmployeesInCsv(@PathVariable(name = "dateDebut") @DateTimeFormat(pattern = "dd-MM-yyyy") String dateDebut,
                                     @PathVariable(name = "dateFin") @DateTimeFormat(pattern = "dd-MM-yyyy") String dateFin,
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
                while (itListCodeAssureurs.hasNext() && itlistCodeCompagniess.hasNext()) {

                    try {
                        String filename = "assure-" + itListCodeAssureurs.next() + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".txt";
                        response.addHeader("Content-Disposition", "attachment; filename=" + filename);
                        assureDnaService.writeAssureDnaCsv(assureDnaService.listAssureDnaDTO(itlistCodeCompagniess.next(),  startDate, endDate), response.getWriter());
                    } catch (AssureDtoException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            //assureService.writeAssureCsv(assureService.listAssureDTO(codeCompagnie,codeAssureur,startDate,endDate),response.getWriter());
        } catch (Exception e) {

            throw new DateDiffException(e.getMessage());
        }
    }

    @GetMapping("/assuresDna/{codeCompagnie}/{codeAssureur}/{dateDebut}/{dateFin}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable(name = "codeCompagnie")  int codeCompagnie,
                                                        @PathVariable(name = "codeAssureur")  int codeAssureur ,
                                                        @PathVariable(name = "dateDebut") @DateTimeFormat(pattern = "dd-MM-yyyy") String dateDebut,
                                                       @PathVariable(name = "dateFin") @DateTimeFormat(pattern = "dd-MM-yyyy") String dateFin) throws ParseException {
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
           // for (int i=0;i<listCodeAssureurs.size();i++){
                String filename = "client_" + codeAssureur+ "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".txt";

                InputStreamResource file = new InputStreamResource(assureDnaService.load(assureDnaService.listAssureDnaDTO(codeCompagnie, startDate, endDate)));

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                        .contentType(MediaType.parseMediaType("application/csv"))
                        .body(file);
           // }

        } catch (AssureDtoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
