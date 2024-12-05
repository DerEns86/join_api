package dev.ens.join_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Subtask {

    private String name;
    private boolean completed;

}
