package com.thaliasteve.demo.repositories;


import com.thaliasteve.demo.dto.CommentDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<CommentDto, Integer> {
}
