package com.thaliasteve.demo.repositories;

import com.thaliasteve.demo.dto.StoryItemDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryItemRepo extends CrudRepository<StoryItemDto, Integer> {}
