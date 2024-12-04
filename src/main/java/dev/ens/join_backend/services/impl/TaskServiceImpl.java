package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.model.User;
import dev.ens.join_backend.repository.TaskRepository;
import dev.ens.join_backend.repository.UserRepository;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> getTasksForUser(String username) {
        return taskRepository.findByAssignee(username);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setCreatedByUserId(user.getUserId());
        return taskRepository.save(task);
    }


    public Task updateTask(Long taskId, Task task) {
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("No task found with id: " + taskId));

        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setAssignee(task.getAssignee());

        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
