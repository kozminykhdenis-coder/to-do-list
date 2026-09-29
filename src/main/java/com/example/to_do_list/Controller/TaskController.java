package com.example.to_do_list.Controller;

import com.example.to_do_list.Entity.Task;
import com.example.to_do_list.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) Boolean completed) {
        if(completed == null) {
            return taskService.getAll();
        }
        return taskService.getByCompleted(completed);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable UUID id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("{id}")
    public Task update(@PathVariable UUID id, @RequestBody Task task) {
       return taskService.update(id, task);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
