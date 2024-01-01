package com.thaliasteve.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "stories")
public class StoryItemDto {
    @Id
    int id;
    String by;
    int[] kids;
    String title;
    String type;
}
