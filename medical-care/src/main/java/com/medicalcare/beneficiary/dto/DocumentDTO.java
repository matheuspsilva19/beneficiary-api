package com.medicalcare.beneficiary.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

	private int id;
	private String documentType;
	private String documentNumber;
	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
	private Date inclusionDate;

	@JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
	private Date updateDate;

}
