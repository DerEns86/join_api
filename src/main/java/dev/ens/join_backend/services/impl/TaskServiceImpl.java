package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.model.User;
import dev.ens.join_backend.repository.TaskRepository;
import dev.ens.join_backend.repository.UserRepository;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getTasksForUser(String username) {
        return taskRepository.findByAssignee(username);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setCreatedByUserId(user.getUserId());
        return taskRepository.save(task);
    }

    @Override
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

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    @Override
    public Task updateSubtasks(Long taskId, List<Subtask> subtasks) {
        Task task = getTaskById(taskId);
        task.setSubtasks(subtasks);
        return taskRepository.save(task);
    }

    @Override
    public Task addSubtask(Long taskId, Subtask subtask) {
        // Task aus der Datenbank holen
        Task task = getTaskById(taskId);

        // Subtask zur Liste hinzufügen
        if (task.getSubtasks() == null) {
            task.setSubtasks(new ArrayList<>());
        }
        task.getSubtasks().add(subtask);

        // Task inklusive neuer Subtask persistieren
        return taskRepository.save(task);
    }

}
