package com.rendezvous.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "provider_service")
public class ProviderService {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String description;
    private Integer duration_minutes;
    private Integer buffer_minutes;
    private BigDecimal price;
    private boolean active = true;


    @ManyToOne()
    @JoinColumn(name = "provider_profile_id", nullable = false)
    private ProviderProfile provider;

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

    public Integer getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(Integer duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public Integer getBuffer_minutes() {
        return buffer_minutes;
    }

    public void setBuffer_minutes(Integer buffer_minutes) {
        this.buffer_minutes = buffer_minutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProviderProfile getProvider() {
        return provider;
    }

    public void setProvider(ProviderProfile provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProviderService service = (ProviderService) o;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
