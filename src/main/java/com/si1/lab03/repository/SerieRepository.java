package com.si1.lab03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si1.lab03.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{

}
