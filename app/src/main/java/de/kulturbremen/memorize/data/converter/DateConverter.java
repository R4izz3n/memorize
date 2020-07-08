package de.kulturbremen.memorize.data.converter;

import androidx.room.TypeConverter;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

import java.time.ZonedDateTime;

public class DateConverter {
    @TypeConverter
    public ZonedDateTime StringToZonedDateTime(String date) {
        return ZonedDateTime.parse(date);
    }

    @TypeConverter
    public String ZonedDateTimeToString(ZonedDateTime dateTime){
        return dateTime.format(ISO_OFFSET_DATE_TIME);
    }
}
