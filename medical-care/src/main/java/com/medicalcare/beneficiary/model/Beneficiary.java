package com.medicalcare.beneficiary.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beneficiary")
public class Beneficiary {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
	
	@Column(name = "beneficiary_name", length = 250)
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "birth_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
	
	@Column(name = "inclusion_date")
	@DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private Date inclusionDate;
	
	@Column(name = "update_date")
	@DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private Date updateDate;
	
	@OneToMany(mappedBy = "beneficiary", cascade = CascadeType.REMOVE)
	private List<Document> documents;

}
