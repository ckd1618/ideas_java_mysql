package com.ckd.ajava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ckd.ajava.models.Idea;
import com.ckd.ajava.repositories.IdeaRepository;





@Service
public class IdeaService {
	private final IdeaRepository ideaRepository;
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}

	public List<Idea> allIdea() {
		return (List<Idea>) ideaRepository.findAll();
	}

	public Idea createIdea(Idea b) {
		return ideaRepository.save(b);
	}

	public void updateIdea(Idea idea) {
		ideaRepository.save(idea);
	}

	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}

	public Idea getIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		if(optionalIdea.isPresent()) {
		    return optionalIdea.get();
		} else {
		    return null;
		}
	}
}