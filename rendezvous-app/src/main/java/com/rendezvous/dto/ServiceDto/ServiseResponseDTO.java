package com.rendezvous.dto.ServiceDto;

import java.math.BigDecimal;

public class ServiseResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Integer bufferMinutes;
    private BigDecimal price;
    private Long providerId;
    boolean active;


    public ServiseResponseDTO(Long id, String name, String description, Integer durationMinutes, Integer bufferMinutes, BigDecimal price, Long providerId, boolean active ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.bufferMinutes = bufferMinutes;
        this.price = price;
        this.providerId = providerId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getBufferMinutes() {
        return bufferMinutes;
    }

    public void setBufferMinutes(Integer bufferMinutes) {
        this.bufferMinutes = bufferMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
