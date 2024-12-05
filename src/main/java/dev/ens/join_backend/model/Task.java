package dev.ens.join_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Task {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String assignee;

    private Long createdByUserId;

 @ElementCollection
 @CollectionTable(name = "subtasks", joinColumns = @JoinColumn(name = "task_id"))
 private List<Subtask> subtasks = new ArrayList<>();
}
