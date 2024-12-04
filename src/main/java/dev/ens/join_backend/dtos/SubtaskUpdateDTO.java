package dev.ens.join_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubtaskUpdateDTO {
    private String name;
    private Boolean isCompleted;

}
