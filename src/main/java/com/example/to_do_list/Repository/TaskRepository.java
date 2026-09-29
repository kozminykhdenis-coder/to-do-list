package com.example.to_do_list.Repository;

import com.example.to_do_list.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByCompleted(boolean completed);
}
