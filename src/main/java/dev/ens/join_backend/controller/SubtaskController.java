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

    @PutMapping("/{taskId}/subtask/{subtaskId}")
    public ResponseEntity<Subtask> updateSubtask(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @RequestBody Subtask subtask) {
        return ResponseEntity.ok(subtaskService.updateSubtask(taskId, subtaskId, subtask.getName(), subtask.isCompleted()));
    }

    @DeleteMapping("/subtask/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(
            @PathVariable Long subtaskId) {
        subtaskService.deleteSubtask(subtaskId);
        return ResponseEntity.noContent().build();
    }
}
