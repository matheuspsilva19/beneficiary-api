package com.medicalcare.beneficiary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDTO {

	private int id;
	private String name;
	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][XXX]")
	private LocalDateTime inclusionDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][XXX]")
	private LocalDateTime updateDate;

	private List<DocumentDTO> documents;
}
