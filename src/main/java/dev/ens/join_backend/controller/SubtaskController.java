package dev.ens.join_backend.controller;

import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.services.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class SubtaskController {

    private final SubtaskService subtaskService;

    @GetMapping("/{taskId}/subtasks")
    public ResponseEntity<List<Subtask>> getSubtasks(@PathVariable Long taskId) {
        return ResponseEntity.ok(subtaskService.getSubtasksByTask(taskId));
    }

    @PostMapping("/{taskId}/subtask")
    public ResponseEntity<Subtask> addSubtask(
            @PathVariable Long taskId,
            @RequestBody Subtask subtask) {
        return ResponseEntity.ok(subtaskService.createSubtask(taskId, subtask));
    }
}
