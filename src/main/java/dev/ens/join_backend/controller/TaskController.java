package dev.ens.join_backend.controller;

import dev.ens.join_backend.dtos.TaskResponseDTO;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Task createdTask = taskService.createTask(task, userDetails.getUsername());
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Task updatedTask = taskService.updateTaskStatus(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<TaskResponseDTO>> getUrgentTasks() {
        List<TaskResponseDTO> tasks = taskService.getUrgentTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{taskId}/contacts/{contactId}")
    public ResponseEntity<Task> assignContactToTask(
            @PathVariable Long taskId,
            @PathVariable Long contactId) {
        Task updatedTask = taskService.assignContactToTask(taskId, contactId);
        return ResponseEntity.ok(updatedTask);
    }
}
