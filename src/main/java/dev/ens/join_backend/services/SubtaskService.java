package dev.ens.join_backend.services;

import dev.ens.join_backend.model.Subtask;

import java.util.List;

public interface SubtaskService {
    Subtask createSubtask(Long taskId, Subtask subtask);

    Subtask getSubtaskById(Long subtaskId);

    void deleteSubtask(Long subtaskId);

    List<Subtask> getSubtasksByTask(Long taskId);

    Subtask updateSubtask(Long taskId, Long subtaskId, String name, Boolean isCompleted);

}
