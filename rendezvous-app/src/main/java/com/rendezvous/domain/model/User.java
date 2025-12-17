package com.rendezvous.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    private Long id;
    private String email;
    private String password;
    private boolean enable;
    private LocalDateTime createdAt;

    private List<Role> role;
}
