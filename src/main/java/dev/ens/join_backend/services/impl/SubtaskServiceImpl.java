package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.repository.SubtaskRepository;
import dev.ens.join_backend.repository.TaskRepository;
import dev.ens.join_backend.services.SubtaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Subtask createSubtask(Long taskId, Subtask subtask) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        subtask.setTask(task);
        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask getSubtaskById(Long subtaskId) {
        return subtaskRepository.findById(subtaskId)
            .orElseThrow(() -> new RuntimeException("Subtask not found"));
    }

    @Override
    public void deleteSubtask(Long subtaskId) {
        subtaskRepository.deleteById(subtaskId);
    }

    @Override
    public List<Subtask> getSubtasksForTask(Long taskId) {
        return subtaskRepository.findByTaskId(taskId);
    }

    @Override
    public Subtask updateSubtask(Long subtaskId, String name, Boolean isCompleted) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));

        if (name != null) {
            subtask.setName(name);
        }
        if (isCompleted != null) {
            subtask.setCompleted(isCompleted);
        }

        return subtaskRepository.save(subtask);
    }
}
