package com.pooltpv.mjc.dna.helper;

import com.pooltpv.mjc.dna.dto.*;
import com.pooltpv.mjc.dna.utulities.DateFormatter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    private static DateFormatter dateFormatter = new DateFormatter();

    public static ByteArrayInputStream assuresToCSV(List<AssureDTO> assureDTOList) {
        final CSVFormat format = CSVFormat.TDF.withDelimiter(';').withQuoteMode(QuoteMode.MINIMAL).withHeader("code_assure", "code_assureur", "qualite"

                ,"nom","prenom","profession","date_naissance","num_contribuable","ville",
                "rue","boite_postale","telephone","email","numero_permis","categorie_permis","date_delivrance",
                "permis_delivre_par","nom_prenom_conducteur","date_naissance_conducteur");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (AssureDTO assureDTO : assureDTOList) {

                if(assureDTOList!=null) {
                    if(assureDTO.getCodeTypePiece()==null){
                        assureDTO.setNumContribuable("");
                    } else if(assureDTO.getCodeTypePiece().equals("RC")){
                        assureDTO.setNumContribuable(assureDTO.getNumContribuable());
                    }else if(assureDTO.getCodeTypePiece().equals("IF")){
                        assureDTO.setNumContribuable(assureDTO.getNumContribuable());
                    } else{
                        assureDTO.setNumContribuable("");
                    }
                    csvPrinter.printRecord(assureDTO.getCodeAssure(), assureDTO.getCodeAssureur(), assureDTO.getQualite(), assureDTO.getNom()
                            , assureDTO.getPrenom(), assureDTO.getProfession(), formatter1.format(dateNonNull(assureDTO.getDateNaissance())), assureDTO.getNumContribuable(),
                            assureDTO.getVille(), assureDTO.getRue(), assureDTO.getBoitePostal(), assureDTO.getTelephone(), assureDTO.getEmail(),
                            assureDTO.getNumeroPermis(), assureDTO.getCategoriePermis(), formatter1.format(dateNonNull(assureDTO.getDateDelivrance())), assureDTO.getPermisDelivrePar(),
                            assureDTO.getNomPrenomConducteur(), formatter1.format(dateNonNull(assureDTO.getDateNaissanceConducteur())));

                }
                else{
                    csvPrinter.printRecord(assureDTOList);
                }
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream assuresDnaToCSV(List<AssureDnaDTO> assureDnaDTOList) throws ParseException{
        final CSVFormat format = CSVFormat.TDF.withQuoteMode(QuoteMode.MINIMAL).withDelimiter('\t').withHeader("code_assure","nom","prenom","profession","date_naissance","ville",
                "rue","boite_postal","telephone","email","numero_permis","categorie_permis","date_delivrance","date_expiration",
                "permos_delivrer_par");


        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (AssureDnaDTO assureDnaDTO : assureDnaDTOList) {
                if(assureDnaDTOList!=null) {
                    if(assureDnaDTO.getDateNaissance()==null){
                        assureDnaDTO.setDateNaissance(new Date(0));
                    }
                    if(assureDnaDTO.getDateDelivrance()==null){
                        assureDnaDTO.setDateDelivrance(new Date(0));
                    }
                    if(assureDnaDTO.getDateExpiration()==null){
                        assureDnaDTO.setDateExpiration(new Date(0));
                    }
                    csvPrinter.printRecord(assureDnaDTO.getCodeClient(),  assureDnaDTO.getNom()
                            , assureDnaDTO.getPrenom(), assureDnaDTO.getProfession(),dateFormatter.setDateFormatSimple(assureDnaDTO.getDateNaissance()),
                            assureDnaDTO.getVille(), assureDnaDTO.getRue(), assureDnaDTO.getBoitePostal(), assureDnaDTO.getTelephone(), assureDnaDTO.getEmail(),
                            assureDnaDTO.getNumeroPermis(), assureDnaDTO.getCategoriePermis(), dateFormatter.setDateFormatSimple(assureDnaDTO.getDateDelivrance()),
                            dateFormatter.setDateFormatSimple(assureDnaDTO.getDateExpiration()), assureDnaDTO.getPermisDelivrePar());
                }
                else{
                    csvPrinter.printRecord(assureDnaDTOList);
                }
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream vehiculesToCSV(List<VehiculeDTO> vehiculeDTOS) throws IOException, ParseException {

        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';').withQuoteMode(QuoteMode.MINIMAL).withHeader("code_assure", "code_assureur", "marque"

                ,"modele","date_premiere_mise_circulation","immatriculation","chassis","usage","charge_utile",
                "puissance_fiscale","remorque","nombre_portes","immatriculation_remorque","source_energie","nombre_de_places","cylindree","double_commande",
                "reponsabilite_civile","utilitaire","type_engin","poids_total_autorise_en_charge");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (VehiculeDTO vehiculeDTO : vehiculeDTOS) {

                switch(vehiculeDTO.getUsage()) {
                    case "IVA":
                        vehiculeDTO.setUsage("4a");
                        break;
                    case "IVB":
                        vehiculeDTO.setUsage("4b");
                        break;
                    case "IVC":
                        vehiculeDTO.setUsage("4c");
                        break;
                    default:
                        vehiculeDTO.setUsage("");
                }
                csvPrinter.printRecord(vehiculeDTO.getCodeAssure(),vehiculeDTO.getCodeAssureur(),vehiculeDTO.getMarque()
                        ,vehiculeDTO.getModele(),dateFormatter.setDateFormatSimple(vehiculeDTO.getDatePremiereMiseCirculation()),vehiculeDTO.getImmatriculation(),vehiculeDTO.getChassis(),vehiculeDTO.getUsage(),vehiculeDTO.getChargeUtile(),
                        vehiculeDTO.getPuissanceFiscale(),vehiculeDTO.getRemorque(),vehiculeDTO.getNombrePortes(),vehiculeDTO.getImmatriculationRemorque(),vehiculeDTO.getSourceEnergie(),vehiculeDTO.getNombreDePlaces(),vehiculeDTO.getCylindree(),vehiculeDTO.getDoubleCommande(),
                        vehiculeDTO.getReponsabiliteCivile(),vehiculeDTO.getUtilitaire(),vehiculeDTO.getTypeEngin(),vehiculeDTO.getPoidsTotalAutoriseEnCharge());
            }


            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream vehiculesDnaToCSV(List<VehiculeDnaDTO> vehiculeDnaDTOS) throws IOException, ParseException {
        final CSVFormat format = CSVFormat.TDF.withQuoteMode(QuoteMode.MINIMAL).withDelimiter('\t').withHeader("mark"
                ,"modele","date_premiere_mise_circulation","immatriculation","chassis","categorie","capacite_charge",
                "puissance_fiscale","remorque","nombre_portes","immatriculation_remorque","source_energie","nombre_de_places","cylindre"
                ,"double_commande","reponsabilite_civile","utilitaire","type_engins","poids_total_autorise_en_charge");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (VehiculeDnaDTO vehiculeDnaDTO : vehiculeDnaDTOS) {
                csvPrinter.printRecord(vehiculeDnaDTO.getMark()
                        ,vehiculeDnaDTO.getModele(),dateFormatter.setDateFormatSimple(vehiculeDnaDTO.getDatePremiereMiseCirculation()),vehiculeDnaDTO.getImmatriculation(),vehiculeDnaDTO.getChassis(),
                        vehiculeDnaDTO.getCategorie(),vehiculeDnaDTO.getCapaciteCharge(),vehiculeDnaDTO.getPuissanceFiscale(),
                        vehiculeDnaDTO.getRemorque(),vehiculeDnaDTO.getNombrePortes(),vehiculeDnaDTO.getImmatriculationRemorque(),vehiculeDnaDTO.getSourceEnergie(),vehiculeDnaDTO.getNombreDePlaces(),vehiculeDnaDTO.getCylindre(),vehiculeDnaDTO.getDoubleCommande(),
                        vehiculeDnaDTO.getReponsabiliteCivile(),vehiculeDnaDTO.getUtilitaire(),vehiculeDnaDTO.getTypeEngin(),vehiculeDnaDTO.getPoidsTotalAutoriseEnCharge());
            }


            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
    public static ByteArrayInputStream venteDnaToCSV(List<VenteDnaDTO> venteDnaDTOS) throws IOException, ParseException {
        final CSVFormat format = CSVFormat.TDF.withQuoteMode(QuoteMode.MINIMAL).withDelimiter('\t').withHeader("numero_attestation"
                ,"immatriculation_vehicule","code_client","code_intermediaire",
                "prime_nette_RC","prime_nette_globale","DTA");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (VenteDnaDTO venteDnaDTO : venteDnaDTOS) {
                if(venteDnaDTO.getDta()==null){
                    venteDnaDTO.setDta("0");
                }
                if(venteDnaDTO.getNumeroAttestation()!=null)
                csvPrinter.printRecord(venteDnaDTO.getNumeroAttestation()
                        ,venteDnaDTO.getImmatriculationVehicule()
                        ,venteDnaDTO.getCodeClient()
                        ,venteDnaDTO.getCodeIntermediaire()
                        ,venteDnaDTO.getPrimeNetteRC()
                        ,venteDnaDTO.getPrimeNetteGlobale()
                        ,venteDnaDTO.getDta());
            }


            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream venteToCSV(List<VenteDTO> venteDTOS) throws IOException, ParseException {
        final CSVFormat format = CSVFormat.TDF.withQuoteMode(QuoteMode.MINIMAL).withDelimiter(';').withHeader("numero_attestation"
                ,"immatriculation","code_assure","code_assureur","code_intermediaire_dna",
                "prime_nette_rc","dta");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (VenteDTO venteDTO : venteDTOS) {

                if(venteDTO.getNumeroAttestation()!=null)
                    csvPrinter.printRecord(venteDTO.getNumeroAttestation()
                            ,venteDTO.getImmatriculation()
                            ,venteDTO.getCodeAssure()
                            ,venteDTO.getCodeAssureur()
                            ,venteDTO.getCodeIntermediaireDna()
                            ,venteDTO.getPrimeNetteRC()
                            ,venteDTO.getDta());
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static Date dateNonNull(Date date){
        if(date==null){
            return new Date();
        }
        return date;
    }
}
