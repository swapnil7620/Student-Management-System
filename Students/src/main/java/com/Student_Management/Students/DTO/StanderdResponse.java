package com.Student_Management.Students.DTO;

import java.time.LocalDateTime;

public class StanderdResponse {
    private Integer id;
    private Integer standard;
    private LocalDateTime createdAt;

    public StanderdResponse(Integer id, Integer standard, LocalDateTime createdAt) {
        this.id = id;
        this.standard = standard;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }
    public Integer getStandard() {
        return standard;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
