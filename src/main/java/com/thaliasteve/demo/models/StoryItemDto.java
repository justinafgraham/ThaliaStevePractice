package com.thaliasteve.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Entity
@Table(name = "stories")
public class StoryItemDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int record_id;
//    @Column(name = "story_id")
    @JsonAlias("id")
    int story_id;
    String by;
    int[] kids;
    String title;
    String type;

//    public void setScore(final int id) {
//        System.out.println((char) 27 + "[97;43m" + id + (char) 27 + "[0m");
//        this.score = id + 111;
//    }
}
