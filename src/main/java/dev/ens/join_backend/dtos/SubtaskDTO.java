package dev.ens.join_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskDTO {
    private String name;
    private boolean isCompleted = false;
}