package dev.ens.join_backend.dtos;

import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.enums.Priority;
import dev.ens.join_backend.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private List<Subtask> subtasks;
    private List<String> assignedContacts;
    private String categoryName;

}
