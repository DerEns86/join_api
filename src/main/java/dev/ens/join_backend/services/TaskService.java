package dev.ens.join_backend.services;

import dev.ens.join_backend.dtos.TaskRequestDTO;
import dev.ens.join_backend.dtos.TaskResponseDTO;
import dev.ens.join_backend.model.Task;

import java.util.List;


public interface TaskService {


    List<TaskResponseDTO> getAllTasks();

    Task createTask(TaskRequestDTO task, String username);

    Task updateTask(Long taskId, Task task);

    void deleteTask(Long taskId);

    Task getTaskById(Long taskId);

    List<TaskResponseDTO> getUrgentTasks();

    Task updateTaskStatus(Long taskId, Task task);

    Task assignContactToTask(Long taskId, Long contactId);
}
