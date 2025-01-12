package dev.ens.join_backend.dtos;

import dev.ens.join_backend.model.Priority;
import dev.ens.join_backend.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private Long taskId;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private List<String> assignedContacts;
    private String categoryName;

}
