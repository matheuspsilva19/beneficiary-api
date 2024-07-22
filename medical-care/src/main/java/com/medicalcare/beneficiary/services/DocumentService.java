package com.medicalcare.beneficiary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medicalcare.beneficiary.model.Document;
import com.medicalcare.beneficiary.repositories.DocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository repository;
	
	@Transactional(rollbackFor = Exception.class)
	public List<Document> save(List<Document> documents) {
		return repository.saveAll(documents);
	}

}
