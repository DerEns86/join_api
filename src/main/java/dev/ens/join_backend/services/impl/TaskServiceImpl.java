package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.dtos.TaskRequestDTO;
import dev.ens.join_backend.dtos.TaskResponseDTO;
import dev.ens.join_backend.mapper.TaskMapper;
import dev.ens.join_backend.model.*;
import dev.ens.join_backend.model.enums.Priority;
import dev.ens.join_backend.model.enums.UpdateMessage;
import dev.ens.join_backend.repository.*;
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
    private final ContactRepository contactRepository;

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return getTaskResponseDTO(tasks);
    }

    @Override
    public Task createTask(TaskRequestDTO taskDto, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        //task.setStatus(Status.PENDING);

        Category category = categoryRepository.findByName(taskDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + taskDto.getCategoryName()));

        Task newTask = TaskMapper.toEntity(taskDto);

        newTask.setCreatedBy(user.getUserId());
        newTask.setCreatedAt(LocalDate.now());
        newTask.setUpdatedBy(user.getUserId());
        newTask.setUpdatedAt(LocalDate.now());
        newTask.setUpdateMessage(UpdateMessage.CREATED);
        newTask.setContacts(new ArrayList<>());
        newTask.setCategory(category);


        return taskRepository.save(newTask);
    }

    @Override
    public Task updateTask(Long taskId, TaskRequestDTO taskDto) {
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("No task found with id: " + taskId));

        Category category = categoryRepository.findByName(taskDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + taskDto.getCategoryName()));

        taskToUpdate.setName(taskDto.getName());
        taskToUpdate.setDescription(taskDto.getDescription());
        taskToUpdate.setStatus(taskDto.getStatus());
        taskToUpdate.setPriority(taskDto.getPriority());
        taskToUpdate.setDueDate(taskDto.getDueDate());
        taskToUpdate.setCategory(category);
        taskToUpdate.setUpdatedAt(LocalDate.now());
        taskToUpdate.setUpdateMessage(UpdateMessage.UPDATED);

        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Task assignContactToTask(Long taskId, Long contactId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        task.getContacts().add(contact);
        contact.getTasks().add(task);

        taskRepository.save(task);
        return task;
    }

    @Override
   public Task updateTaskStatus(Long taskId, Task task){
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("No task found with id: " + taskId));
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setUpdateMessage(UpdateMessage.UPDATED);
        taskToUpdate.setUpdatedAt(LocalDate.now());
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
            dto.setId(task.getId());
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

            List<Subtask> subtasks = task.getSubtasks();
            dto.setSubtasks(subtasks);
        }
        return taskResponseDTOs;
    }
}
