package dev.ens.join_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @ManyToMany(mappedBy = "contacts")
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    private Long createdBy;
    private Long updatedBy;
    private LocalDate updatedAt;
    private UpdateMessage updateMessage;
}
