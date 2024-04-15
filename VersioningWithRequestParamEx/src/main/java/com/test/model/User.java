package com.test.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private Integer id;
	@Size(min=2,message="name should have at least 2 characters...!")
	private String name;
	@Past(message = "Birth Date should be in the past...!")
	private LocalDate birthDate;
	//private Date birthDate;
}
