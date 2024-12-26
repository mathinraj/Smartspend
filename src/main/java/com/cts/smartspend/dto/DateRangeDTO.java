package com.cts.smartspend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateRangeDTO {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Future
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    public DateRangeDTO() {
    }

    public DateRangeDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
