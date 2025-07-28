package com.kanban.api.repository;

import com.kanban.api.model.Task;

import java.util.List;

public interface KanbanRepositoryCustom {
    List<Task> findByTitle(String title);
}
