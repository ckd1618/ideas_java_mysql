package com.ckd.ajava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ckd.ajava.models.Idea;



@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {

	List<Idea> findAll();
	
}
