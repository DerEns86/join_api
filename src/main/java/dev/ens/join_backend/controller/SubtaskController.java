package dev.ens.join_backend.controller;

import dev.ens.join_backend.dtos.SubtaskUpdateDTO;
import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.services.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subtaskService;


    @PostMapping("/{taskId}")
    public ResponseEntity<Subtask> createSubtask(@PathVariable Long taskId, @RequestBody Subtask subtask) {
        return ResponseEntity.ok(subtaskService.createSubtask(taskId, subtask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subtask> getSubtaskById(@PathVariable Long id) {
        return ResponseEntity.ok(subtaskService.getSubtaskById(id));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Subtask>> getSubtasksForTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(subtaskService.getSubtasksForTask(taskId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Long id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subtask> updateSubtask(
            @PathVariable Long id,
            @RequestBody SubtaskUpdateDTO updateRequest) {

        Subtask updatedSubtask = subtaskService.updateSubtask(id, updateRequest.getName(), updateRequest.getIsCompleted());
        return ResponseEntity.ok(updatedSubtask);
    }
}
