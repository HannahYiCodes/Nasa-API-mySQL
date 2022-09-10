package com.careerdevs.nasa.repository;

import com.careerdevs.nasa.model.NasaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NasaRepository extends JpaRepository<NasaModel, Integer> {

}
