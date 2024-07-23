package com.medicalcare.beneficiary.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "document")
@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_DOCUMENT", initialValue = 2, allocationSize = 1)
public class Document {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "document_type")
	private String documentType;
	
	@Column(name = "document_number")
	private String documentNumber;
	
	@Column(name = "description", length = 50)
	private String description;
	
	@Column(name = "inclusion_date")
    private LocalDateTime inclusionDate;
	
	@Column(name = "update_date")
    private LocalDateTime updateDate;
	
	@ManyToOne
    @JoinColumn(name = "beneficiary_id", nullable = false)
	private Beneficiary beneficiary;
}
