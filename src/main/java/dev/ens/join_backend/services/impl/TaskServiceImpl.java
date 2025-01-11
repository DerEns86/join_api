package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.*;
import dev.ens.join_backend.repository.TaskRepository;
import dev.ens.join_backend.repository.UserRepository;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setStatus(Status.PENDING);
        if(task.getPriority() != Priority.LOW && task.getPriority() != Priority.URGENT){
            task.setPriority(Priority.MEDIUM);
        }
        task.setDueDate(LocalDate.now());
        task.setCreatedBy(user.getUserId());
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedBy(user.getUserId());
        task.setUpdatedAt(LocalDate.now());
        task.setUpdateMessage(UpdateMessage.CREATED);
        task.setContacts(new ArrayList<>());
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
    public List<Task> getUrgentTasks() {
        return taskRepository.findAllByPriority(Priority.URGENT);
    }
}
