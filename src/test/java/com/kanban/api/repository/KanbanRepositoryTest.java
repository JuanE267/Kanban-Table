package com.kanban.api.repository;

import com.kanban.api.dto.TaskDTO;
import com.kanban.api.mappers.TaskMapper;
import com.kanban.api.model.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class KanbanRepositoryTest {

    @Autowired
    private KanbanRepository kanbanRepository;

    @Test
    public void KanbanRepository_SaveAll_ReturnsSavedTask(){

        // Example tasks
        TaskDTO taskDTO = TaskDTO.builder()
                                    .title("testTask")
                                    .description("test Description")
                                    .build();
        // Mapping to entity
        Task mappedTask = TaskMapper.mapToEntity(taskDTO);

        // Saving
        Task savedTask = kanbanRepository.save(mappedTask);

        // Asserting
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
    }



}
