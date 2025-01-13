package dev.ens.join_backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UpdateMessage {

    CREATED,
    UPDATED,
    DELETED
}
