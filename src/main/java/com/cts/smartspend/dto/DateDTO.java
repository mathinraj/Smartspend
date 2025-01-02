package com.cts.smartspend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DateDTO {

    @JsonFormat(pattern = "yyyy-MM-dd") //ISO 8601 format
    private LocalDate date;

    public DateDTO() {
    }
    public DateDTO(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

