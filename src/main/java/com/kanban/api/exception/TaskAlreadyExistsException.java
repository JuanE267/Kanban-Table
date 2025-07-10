package com.kanban.api.exception;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException(String title) {
        super("Task Already exists with title: " + title);
    }
}
