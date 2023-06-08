package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Kart;
import com.sportyshoes.repository.KartRepository;

@Service
public class KartService {

	@Autowired
	KartRepository kartRepository;
	
	public void addKart(Kart kart) {
		kartRepository.save(kart);
	}
	
	public List<Kart> viewAll(){
		return kartRepository.findAll();
		
	}
}
