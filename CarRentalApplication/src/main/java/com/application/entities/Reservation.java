package com.application.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
	private Integer id;
	private Integer carId;
	private Integer userId;
	private LocalDate startDate;
	private LocalDate endDate;
	private BigDecimal rentalPrice;
}
