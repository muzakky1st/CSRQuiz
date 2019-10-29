package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CsrService {

	@Autowired
	private CsrRepository repo;
	@Autowired
    private EntityManager em;

	public List<CsrModel> listAll() {
		return repo.findAll();
	}

	public void save(CsrModel csrModel) {
		repo.save(csrModel);
	}

	public CsrModel get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
