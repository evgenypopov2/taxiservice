package ru.otus.common.dto;

import ru.otus.common.model.TaxiStatus;

import java.io.Serializable;
import java.util.UUID;

public class TaxiStatusDTO implements Serializable {
    private UUID id;
    private TaxiStatus status;

    public TaxiStatusDTO(UUID id, TaxiStatus status) {
        this.id = id;
        this.status = status;
    }

    public TaxiStatusDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TaxiStatus getStatus() {
        return status;
    }

    public void setStatus(TaxiStatus status) {
        this.status = status;
    }
}
