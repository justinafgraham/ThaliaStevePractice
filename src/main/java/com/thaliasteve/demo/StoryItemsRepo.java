package com.thaliasteve.demo;

import com.thaliasteve.demo.models.StoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StoryItemsRepo extends CrudRepository<StoryItem, Integer> { }
