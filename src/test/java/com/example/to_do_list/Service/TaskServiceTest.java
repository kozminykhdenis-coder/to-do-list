package com.example.to_do_list.Service;

import com.example.to_do_list.Entity.Task;
import com.example.to_do_list.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getAll_shouldReturnAllTasks() {
        Task task1 = Task.builder().id(UUID.randomUUID()).title("Task 1")
                .completed(false).build();
        Task task2 = Task.builder().id(UUID.randomUUID()).title("Task 2")
                .completed(false).build();
        when(taskRepository.findAll()).thenReturn(List.of(task1,task2));

        List<Task> result = taskService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Task::getTitle)
                .containsExactly("Task 1", "Task 2");
        verify(taskRepository, times(1)).findAll();
    }


    @Test
    void create_shouldSetCompletedFalse_whenCompletedIsNull() {
        Task input = Task.builder()
                .title("Task")
                .description("desc")
                .build();

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Task result = taskService.create(input);

        assertThat(result.getCompleted()).isFalse();
        verify(taskRepository, times(1))
                .save(any(Task.class));
    }

    @Test
    void create_shouldKeepCompletedTrue_whenProvided() {
        Task input = Task.builder()
                .title("Task")
                .description("desc")
                .completed(true)
                .build();

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Task result = taskService.create(input);

        assertThat(result.getCompleted()).isTrue();
        verify(taskRepository, times(1))
                .save(any(Task.class));
    }
}
