package dev.ens.join_backend.controller;

import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
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
        public List<Task> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
            String username = userDetails.getUsername();
            return taskService.getTasksForUser(username);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.createTask(task, userDetails.getUsername());
    }

    @PutMapping("/{taskId}")
        public Task updateTask(@PathVariable Long taskId, @RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
            return taskService.updateTask(taskId, task);
        }


    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(taskId);
    }
}
