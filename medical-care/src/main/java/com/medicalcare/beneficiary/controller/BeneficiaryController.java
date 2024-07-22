package com.medicalcare.beneficiary.controller;

import static org.springframework.http.ResponseEntity.notFound;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalcare.beneficiary.dto.BeneficiaryDTO;
import com.medicalcare.beneficiary.dto.DocumentDTO;
import com.medicalcare.beneficiary.model.Beneficiary;
import com.medicalcare.beneficiary.model.Document;
import com.medicalcare.beneficiary.services.BeneficiaryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/beneficiary")
@SecurityRequirement(name = "bearerAuth")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/findAll")
	public List<BeneficiaryDTO> findAll() {
		List<Beneficiary> beneficiaries = service.findAll();

		return beneficiaries.stream().map(beneficiary -> mapper.map(beneficiary, BeneficiaryDTO.class)).toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<BeneficiaryDTO> findById(@PathVariable int id) {
		Optional<Beneficiary> optional = service.findById(id);

		return optional.map(beneficiary -> mapper.map(beneficiary, BeneficiaryDTO.class)).map(ResponseEntity::ok)
				.orElseGet(() -> notFound().build());
	}

	@PostMapping
	public ResponseEntity<BeneficiaryDTO> save(@RequestBody BeneficiaryDTO beneficiarioDTO) {

		Beneficiary newBeneficiary = service.saveOrUpdate(mapper.map(beneficiarioDTO, Beneficiary.class));

		return new ResponseEntity<BeneficiaryDTO>(mapper.map(newBeneficiary, BeneficiaryDTO.class), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<BeneficiaryDTO> update(@PathVariable int id, @RequestBody BeneficiaryDTO beneficiarioDTO) {
		Beneficiary beneficiaryUpdate = service.setupUpdate(id, beneficiarioDTO);

		Optional<Beneficiary> optional = service.findById(id);

		if (optional.isPresent() && beneficiaryUpdate != null) {
			Beneficiary updatedBeneficiary = service.saveOrUpdate(beneficiaryUpdate);

			return new ResponseEntity<BeneficiaryDTO>(mapper.map(updatedBeneficiary, BeneficiaryDTO.class),
					HttpStatus.NO_CONTENT);
		}

		return notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BeneficiaryDTO> deleteById(@PathVariable int id) {
		Beneficiary beneficiaryDeleted = service.deleteById(id);

		if (beneficiaryDeleted != null) {
			return new ResponseEntity<BeneficiaryDTO>(mapper.map(beneficiaryDeleted, BeneficiaryDTO.class),
					HttpStatus.NO_CONTENT);
		}

		return notFound().build();
	}

	@GetMapping("/{id}/documents")
	public ResponseEntity<List<DocumentDTO>> findDocumentsById(@PathVariable int id) {

		Optional<List<Document>> documentsOptional = service.findDocumentsById(id);

		if (documentsOptional.isPresent()) {
			List<DocumentDTO> documents = documentsOptional.get().stream()
					.map(document -> mapper.map(document, DocumentDTO.class)).toList();

			return ResponseEntity.ok(documents);
		}

		return notFound().build();
	}

}
