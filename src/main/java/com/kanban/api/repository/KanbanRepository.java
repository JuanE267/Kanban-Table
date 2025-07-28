package com.kanban.api.repository;

import com.kanban.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanbanRepository extends KanbanRepositoryCustom,JpaRepository<Task, Long> {
}
