package com.application.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers implements Serializable {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phoneNo;
}
