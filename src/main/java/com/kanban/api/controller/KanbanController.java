package com.kanban.api.controller;

import com.kanban.api.dto.TaskDTO;
import com.kanban.api.model.Task;
import com.kanban.api.service.KanbanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kanban.api.mappers.TaskMapper.mapToDto;
import static com.kanban.api.mappers.TaskMapper.mapToEntity;

@RestController
@RequestMapping("/kanban")
public class KanbanController {

    private final KanbanService service;

    public KanbanController(KanbanService service){
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        List<Task> listOfTasks = service.getAllTasks();
        return listOfTasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return service.getTask(id);
    }

    public List<Task> getTasksByTitle(@PathVariable String title){
        return service.getTasksByTitle(title);
    }

    @PostMapping
    public Task createTask(@RequestBody @Valid TaskDTO task){
        return service.addTask(mapToEntity(task));
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable Long id){
        return service.deleteTask(id);
    }

    @PatchMapping("/{id}/complete")
    public TaskDTO taskDone(@PathVariable Long id){
        return mapToDto(service.taskDone(id));
    }

    @PatchMapping("/{id}/cancel")
    public TaskDTO taskCanceled(@PathVariable Long id){
        return mapToDto(service.taskCanceled(id));
    }

    @PatchMapping("/{id}/test")
    public TaskDTO taskInTest(@PathVariable Long id){
        return mapToDto(service.taskInTest(id));
    }

    @PatchMapping("/{id}/todo")
    public TaskDTO taskToDo(@PathVariable Long id){
        return mapToDto(service.taskToDo(id));
    }

    @PatchMapping("/{id}/inProgress")
    public TaskDTO taskInProgress(@PathVariable Long id){
        return mapToDto(service.taskInProgress(id));
    }

    @PutMapping("/{id}/edit")
    public TaskDTO editTaskDescription(@PathVariable Long id, @RequestBody @Valid TaskDTO task){
        return mapToDto(service.editTask(id,  task));
    }

}
