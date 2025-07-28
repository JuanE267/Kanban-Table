package com.kanban.api.repository;

import com.kanban.api.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

// i've created this new repository to implement the custom methods.
// it implements a custom interface, the jpaRepository implements this custom repository and the jpa one.
public class KanbanRepositoryImpl implements KanbanRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    public KanbanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Task> findByTitle(String title) {
        // JPQL works with entities and their attributes, that's why the query is somewhat different to a sql one
        return entityManager.createQuery("SELECT t FROM Task t Where t.title = :title", Task.class).setParameter("title", title).getResultList();
    }
}
