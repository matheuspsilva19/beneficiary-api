package com.medicalcare.beneficiary.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beneficiary")
@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_BENEFICIARY", initialValue = 2, allocationSize = 1)
public class Beneficiary {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "beneficiary_name", length = 250)
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "birth_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
	
	@Column(name = "inclusion_date")
    private LocalDateTime inclusionDate;
	
	@Column(name = "update_date")
    private LocalDateTime updateDate;
	
	@OneToMany(mappedBy = "beneficiary", cascade = CascadeType.REMOVE)
	private List<Document> documents;

}
