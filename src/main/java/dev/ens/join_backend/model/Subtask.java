package dev.ens.join_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Subtask {

    private String name;

    private boolean completed;

    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;


}
