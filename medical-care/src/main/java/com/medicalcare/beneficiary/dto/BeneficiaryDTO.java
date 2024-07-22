package com.medicalcare.beneficiary.dto;

import java.util.Date;
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
	private Date birthDate;

	@JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
	private Date inclusionDate;

	@JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
	private Date updateDate;

	private List<DocumentDTO> documents;
}
