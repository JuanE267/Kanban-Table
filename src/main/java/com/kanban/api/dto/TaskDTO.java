package com.kanban.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {

    @NotBlank(message = "Title cannot be blank!")
    private String title;
    private String description;

    public TaskDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

}
