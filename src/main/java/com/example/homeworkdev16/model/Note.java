package com.example.homeworkdev16.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {
    @Id
    private Long id;
    private String title;
    private String content;
}
