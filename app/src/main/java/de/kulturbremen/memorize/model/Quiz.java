package de.kulturbremen.memorize.model;

import java.time.ZonedDateTime;

public interface Quiz {
    String getName();
    ZonedDateTime getCreationDate();
    ZonedDateTime getDateLastAltered();
}
