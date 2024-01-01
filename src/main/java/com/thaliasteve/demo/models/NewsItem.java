package com.thaliasteve.demo.models;

import com.thaliasteve.demo.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@Entity
//@Table(name = "story_items")
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //    @SerializedName("itemId")
//    String item_id;
    String title;
    String type;
}
