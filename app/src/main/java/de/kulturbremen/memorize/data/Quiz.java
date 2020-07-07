package de.kulturbremen.memorize.data;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;


@Entity(indices = {@Index("dateLastAltered")})
public class Quiz {
    @PrimaryKey
    public String name;

    public ZonedDateTime creationDate;
    public ZonedDateTime dateLastAltered;
}
