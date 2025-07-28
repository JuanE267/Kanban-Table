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

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class KanbanRepositoryTest {

    @Autowired
    private KanbanRepository kanbanRepository;

    // Unit tests

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

    @Test
    public void KanbanRepository_GetAll_ReturnsMoreThanOneTask(){

        // saving some data into the database to work on
        TaskDTO taskDTO = TaskDTO.builder()
                .title("testTask")
                .description("test Description")
                .build();
        TaskDTO taskDTO1 = TaskDTO.builder()
                .title("testTask1")
                .description("test Description")
                .build();

        Task mappedTask = TaskMapper.mapToEntity(taskDTO);
        Task mappedTask1 = TaskMapper.mapToEntity(taskDTO1);

        kanbanRepository.save(mappedTask);
        kanbanRepository.save(mappedTask1);

        List<Task> taskList = kanbanRepository.findAll();

        Assertions.assertThat(taskList).isNotNull();
        Assertions.assertThat(taskList.size()).isEqualTo(2);
        Assertions.assertThat(taskList).isNotEmpty();

    }

    @Test
    public void KanbanRepository_FindById_ReturnsATask(){

        TaskDTO taskDTO = TaskDTO.builder()
                .title("testTask")
                .description("test Description")
                .build();

        Task mappedTask = TaskMapper.mapToEntity(taskDTO);

        kanbanRepository.save(mappedTask);

        Task taskFound = kanbanRepository.findById(mappedTask.getId()).get();

        Assertions.assertThat(taskFound).isNotNull();

    }

    @Test
    public void KanbanRepository_FindByTitle_ReturnsTasks(){

        // saving some data into the database to work on
        TaskDTO taskDTO = TaskDTO.builder()
                .title("genericTitle")
                .description("test Description")
                .build();
        TaskDTO taskDTO1 = TaskDTO.builder()
                .title("genericTitle")
                .description("test Description")
                .build();

        Task mappedTask = TaskMapper.mapToEntity(taskDTO);
        Task mappedTask1 = TaskMapper.mapToEntity(taskDTO1);

        kanbanRepository.save(mappedTask);
        kanbanRepository.save(mappedTask1);

        List<Task> tasksWithSameTitle = kanbanRepository.findByTitle("genericTitle");


        Assertions.assertThat(tasksWithSameTitle).isNotEmpty();
        Assertions.assertThat(tasksWithSameTitle).isNotNull();
        Assertions.assertThat(tasksWithSameTitle.size()).isEqualTo(2);
    }

    // todo
    // update delete testing

}
