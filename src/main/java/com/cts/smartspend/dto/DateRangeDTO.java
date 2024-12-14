package com.cts.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateRangeDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
