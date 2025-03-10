package dev.ens.join_backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING,
    IN_PROGRESS,
    AWAITING_FEEDBACK,
    DONE
}
