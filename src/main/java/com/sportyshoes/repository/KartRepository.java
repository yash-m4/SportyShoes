package com.sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Kart;
@Repository
public interface KartRepository extends JpaRepository<Kart, Integer> {

}
