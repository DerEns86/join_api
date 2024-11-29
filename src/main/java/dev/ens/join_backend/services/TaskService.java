package dev.ens.join_backend.services;

import dev.ens.join_backend.model.Task;
import java.util.List;


public interface TaskService {

    List<Task> getTasksForUser(String username);

    List<Task> getAllTasks();

    Task createTask(Task task, String username);

    Task updateTask(Long taskId, Task task);

    void deleteTask(Long taskId);
}
