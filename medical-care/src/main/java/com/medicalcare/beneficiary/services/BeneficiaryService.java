package com.medicalcare.beneficiary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medicalcare.beneficiary.dto.BeneficiaryDTO;
import com.medicalcare.beneficiary.model.Beneficiary;
import com.medicalcare.beneficiary.model.Document;
import com.medicalcare.beneficiary.repositories.BeneficiaryRepository;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository repository;

	@Autowired
	private DocumentService documentService;

	@Transactional(readOnly = true)
	public List<Beneficiary> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Beneficiary> findById(int id) {
		return repository.findById(id);
	}
	
	public Optional<List<Document>> findDocumentsById(int id) {
		Optional<Beneficiary> optional = findById(id);

		return optional.map(Beneficiary::getDocuments);
	}

	@Transactional
	public Beneficiary saveOrUpdate(Beneficiary beneficiary) {
		Beneficiary beneficiarySaved = repository.save(beneficiary);

		beneficiary.getDocuments().forEach(document -> document.setBeneficiary(beneficiarySaved));
		beneficiarySaved.setDocuments(documentService.save(beneficiary.getDocuments()));

		return beneficiarySaved;

	}

	public Beneficiary setupUpdate(int id, BeneficiaryDTO beneficiaryDTO) {
		Optional<Beneficiary> optional = findById(id);

		if (optional.isPresent()) {
			Beneficiary beneficiary = optional.get();

			beneficiary.setName(beneficiaryDTO.getName());
			beneficiary.setPhoneNumber(beneficiaryDTO.getPhoneNumber());
			beneficiary.setBirthDate(beneficiaryDTO.getBirthDate());
			beneficiary.setInclusionDate(beneficiaryDTO.getInclusionDate());
			beneficiary.setUpdateDate(beneficiaryDTO.getUpdateDate());

			beneficiaryDTO.getDocuments().forEach(docUpdate -> {
				beneficiary.getDocuments().forEach(document -> {
					document.setDocumentType(docUpdate.getDocumentType());
					document.setDocumentNumber(docUpdate.getDocumentNumber());
					document.setDescription(docUpdate.getDescription());
					document.setInclusionDate(docUpdate.getInclusionDate());
					document.setUpdateDate(docUpdate.getUpdateDate());
				});
			});
			
			return beneficiary;
		}

		return null;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Beneficiary deleteById(int id) {
		Optional<Beneficiary> optional = findById(id);

		if (optional.isPresent()) {
			Beneficiary beneficiary = optional.get();
			repository.deleteById(beneficiary.getId());

			return beneficiary;
		}

		return null;
	}

}
