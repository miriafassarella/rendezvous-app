package com.rendezvous.dto.appointmentDto;

import com.rendezvous.domain.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class AppointmentResponseDTO {

    @NotNull
    private Long id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private Long serviceId;
    @NotNull
    private Long providerId;
    @NotNull
    private Long clientId;
    @NotNull
    private Status status;

    public AppointmentResponseDTO(Long id, DayOfWeek dayOfWeek, LocalDateTime startTime,
                                  LocalDateTime endTime, Long serviceId, Long providerId, Long clientId, Status status){
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceId = serviceId;
        this.providerId = providerId;
        this.clientId = clientId;
        this.status = status;
    }

    public AppointmentResponseDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
