package com.cts.smartspend.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class DateDTO {
    private LocalDate date;

    public DateDTO() {}
    public DateDTO(LocalDate date) {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
