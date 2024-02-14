package com.application.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
	private Integer id;
	private String brand;
	private Integer yearOfManufacturing;
	private BigDecimal price;
	private String model;
	private Boolean isAvailable;
}
