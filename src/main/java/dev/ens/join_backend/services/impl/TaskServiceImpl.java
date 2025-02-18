package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.dtos.TaskResponseDTO;
import dev.ens.join_backend.model.*;
import dev.ens.join_backend.model.enums.Priority;
import dev.ens.join_backend.model.enums.Status;
import dev.ens.join_backend.model.enums.UpdateMessage;
import dev.ens.join_backend.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return getTaskResponseDTO(tasks);
    }

    @Override
    public Task createTask(Task task, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setStatus(Status.PENDING);
        if(task.getPriority() != Priority.LOW && task.getPriority() != Priority.URGENT){
            task.setPriority(Priority.MEDIUM);
        } else {
            task.setPriority(task.getPriority());
        }
        task.setCreatedBy(user.getUserId());
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedBy(user.getUserId());
        task.setUpdatedAt(LocalDate.now());
        task.setUpdateMessage(UpdateMessage.CREATED);
        task.setContacts(new ArrayList<>());

        if (task.getCategory() != null && task.getCategory().getName() != null) {
            Category category = categoryRepository.findByName(task.getCategory().getName())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + task.getCategory().getName()));
            task.setCategory(category);
        }

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
    public List<TaskResponseDTO> getUrgentTasks() {
        List<Task> tasks = taskRepository.findAllByPriority(Priority.URGENT);
        return getTaskResponseDTO(tasks);
    }

    private List<TaskResponseDTO> getTaskResponseDTO(List<Task> tasks) {
        List<TaskResponseDTO> taskResponseDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponseDTO dto = new TaskResponseDTO();
            dto.setTaskId(task.getId());
            dto.setName(task.getName());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());
            List<String> contactNames = task.getContacts().stream()
                    .map(contact -> contact.getFirstName() + " " + contact.getLastName())
                    .toList();
            dto.setAssignedContacts(contactNames);
            dto.setCategoryName(task.getCategory() != null ? task.getCategory().getName() : null);
            taskResponseDTOs.add(dto);
        }
        return taskResponseDTOs;
    }
}
