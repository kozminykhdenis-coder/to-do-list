package com.example.to_do_list.Service;

import com.example.to_do_list.Entity.Task;
import com.example.to_do_list.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAll(){
       return taskRepository.findAll();
    }

    public Task getById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not foud: " + id));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(UUID id, Task update) {
        Task task = getById(id);
        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setCompleted(update.isCompleted());
        return taskRepository.save(task);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
}
