package com.kanban.api.exception;

import com.kanban.api.enums.TaskStatus;

public class TaskStatusAlreadySetException extends RuntimeException {
    public TaskStatusAlreadySetException(TaskStatus status) {
        super("Task is already set with status: " + status);
    }
}
