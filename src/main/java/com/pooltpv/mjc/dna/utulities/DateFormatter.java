package com.pooltpv.mjc.dna.utulities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class DateFormatter {

   private DateFormat dateHhMmSsFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   private DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
<<<<<<< HEAD
   private DateFormat dateFormatter1 = new SimpleDateFormat("dd/MM/yyyy");
=======
>>>>>>> a9a3409530c57731f62d83d3161ef4bf0db0294f

   public String setDateFormat(String dateHhMmSs) throws ParseException {
      if(dateHhMmSs!=null)
      return this.dateFormatter.format(this.dateFormatter.parse(dateHhMmSs));
      else
         return "";

   }
   public String setDateFormatSimple(Date date) throws ParseException {
      if(date!=null)
         return this.dateFormatter.format(date);
      else
         return "";

   }
<<<<<<< HEAD

   public String setDateFormatSimple1(Date date) throws ParseException {
      if(date!=null)
         return this.dateFormatter1.format(date);
      else
         return "";

   }
=======
>>>>>>> a9a3409530c57731f62d83d3161ef4bf0db0294f
}
