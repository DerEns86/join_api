package dev.ens.join_backend.mapper;

import dev.ens.join_backend.dtos.SubtaskDTO;
import dev.ens.join_backend.dtos.TaskRequestDTO;
import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.model.enums.Priority;

import java.util.stream.Collectors;

public class TaskMapper {


    public static TaskRequestDTO toDto(Task task){
        TaskRequestDTO dto = new TaskRequestDTO();

        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setCategoryName(task.getCategory().getName());

        if(task.getPriority() != Priority.LOW && task.getPriority() != Priority.URGENT){
            dto.setPriority(Priority.MEDIUM);
        } else {
            dto.setPriority(task.getPriority());
        }

        dto.setSubtasks(task.getSubtasks().stream()
                .map(subtask -> new SubtaskDTO(subtask.getName(), subtask.isCompleted()))
                .collect(Collectors.toList()));

        return dto;
    }

    public static Task toEntity(TaskRequestDTO dto){
        Task task = new Task();

        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        if (dto.getSubtasks() != null) {
            task.setSubtasks(dto.getSubtasks().stream().map(subtaskDTO -> {
                Subtask subtask = new Subtask();
                subtask.setName(subtaskDTO.getName());
                subtask.setCompleted(false);
                subtask.setTask(task);
                return subtask;
            }).collect(Collectors.toList()));
        }

        if(dto.getPriority() != Priority.LOW && dto.getPriority() != Priority.URGENT){
            task.setPriority(Priority.MEDIUM);
        } else {
            task.setPriority(dto.getPriority());
        }

        return task;
    }
}
