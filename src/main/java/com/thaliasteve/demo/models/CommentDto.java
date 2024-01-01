package com.thaliasteve.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class CommentDto {
    @Id
    int id;
    String by;
    String text;
    int[] kids;
    String parent;
}
