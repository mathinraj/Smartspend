package com.cts.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateRangeDTO {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRangeDTO() {
    }

    public DateRangeDTO(LocalDate endDate, LocalDate startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
