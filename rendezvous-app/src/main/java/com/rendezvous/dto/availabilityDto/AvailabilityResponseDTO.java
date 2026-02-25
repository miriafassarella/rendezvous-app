package com.rendezvous.dto.availabilityDto;

import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Native;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AvailabilityResponseDTO {

    @NotNull
    private Long id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private Long providerId;

    public AvailabilityResponseDTO(Long id, DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, Long providerId){
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }


}
