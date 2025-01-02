package dev.ens.join_backend.controller;

import dev.ens.join_backend.model.Subtask;
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

    @GetMapping("/user")
        public ResponseEntity<List<Task>> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
            String username = userDetails.getUsername();
            List<Task> tasks = taskService.getTasksForUser(username);
            return ResponseEntity.ok(tasks);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        List<Task> tasks = taskService.getAllTasks();
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

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

//    endpoints for subtasks

    @PatchMapping("/{taskId}/subtasks")
    public ResponseEntity<Task> updateSubtasks(
            @PathVariable Long taskId,
            @RequestBody List<Subtask> subtasks) {
        return ResponseEntity.ok(taskService.updateSubtasks(taskId, subtasks));
    }

    @PostMapping("/{taskId}/subtask")
    public ResponseEntity<Task> addSubtask(
            @PathVariable Long taskId,
            @RequestBody Subtask subtask,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.addSubtask(taskId, subtask));
    }

}
