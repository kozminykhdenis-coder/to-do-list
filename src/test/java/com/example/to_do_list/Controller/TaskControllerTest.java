package com.example.to_do_list.Controller;

import com.example.to_do_list.Entity.Task;
import com.example.to_do_list.Service.TaskService;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @Test
    void getAllTask_shouldReturnJsonList() throws Exception {
        Task task = Task.builder()
                .id((UUID.randomUUID()))
                .title("Task 1")
                .completed(false)
                .build();

        when(taskService.getAll()).thenReturn(List.of(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Task 1"))
                .andExpect(jsonPath("$[0].completed")
                        .value(false));
    }
    @Test
    void createTask_shouldReturnCreated() throws Exception {
        Task input = Task.builder()
                .title("New Task")
                .completed(false)
                .build();

        when(taskService.create(any(Task.class))).thenReturn(input);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"));
    }

}
