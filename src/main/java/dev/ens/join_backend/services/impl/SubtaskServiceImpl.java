package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.repository.SubtaskRepository;
import dev.ens.join_backend.services.SubtaskService;
import dev.ens.join_backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskService taskService;

    @Override
    public Subtask createSubtask(Long taskId, Subtask subtask) {
        Task task = taskService.getTaskById(taskId);

        subtask.setTask(task);

        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask getSubtaskById(Long subtaskId) {
        return null;
    }

    @Override
    public void deleteSubtask(Long subtaskId) {

    }

    @Override
    public List<Subtask> getSubtasksByTask(Long taskId) {
        return subtaskRepository.findByTaskId(taskId);
    }

    @Override
    public Subtask updateSubtask(Long taskId, Long subtaskId, String name, Boolean isCompleted) {
        Task task = taskService.getTaskById(taskId);
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow();
        subtask.setName(name);
        subtask.setCompleted(isCompleted);
        return subtaskRepository.save(subtask);
    }
}
