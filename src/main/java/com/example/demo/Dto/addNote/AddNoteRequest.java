package com.example.demo.Dto.addNote;


import lombok.Data;

import java.io.Serializable;


@Data
public class AddNoteRequest implements Serializable {
    String title;
    String description;
    String category;
}
