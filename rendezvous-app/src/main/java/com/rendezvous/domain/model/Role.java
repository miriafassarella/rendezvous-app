package com.rendezvous.domain.model;

import com.rendezvous.domain.enums.Name;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private Name name;

    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
