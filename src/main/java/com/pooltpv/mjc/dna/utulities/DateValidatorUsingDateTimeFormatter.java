package com.pooltpv.mjc.dna.utulities;

import com.pooltpv.mjc.dna.exceptions.DateDiffException;
import net.minidev.asm.ConvertDate;
import org.apache.commons.validator.routines.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateValidatorUsingDateTimeFormatter extends DateValidator {
    private DateTimeFormatter dateFormatter;
    private Date dateStart;
    private Date dateEnd;

    public DateValidatorUsingDateTimeFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public DateValidatorUsingDateTimeFormatter(Date dateStart, Date dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            this.dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isDatediffValid(Date d1, Date d2) throws DateDiffException {
            Long diff = d2.getTime()-d1.getTime();
            System.out.println(ConvertDate.convertToDate(diff));
            boolean diffValid=false;
            if(diff>0)diffValid=true;
            return diffValid;
    }
}
