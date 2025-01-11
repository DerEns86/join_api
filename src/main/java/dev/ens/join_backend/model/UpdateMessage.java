package dev.ens.join_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UpdateMessage {

    CREATED,
    UPDATED,
    DELETED
}
