package com.github.bloiseleo.dto;

import java.time.LocalDate;

public record CreateTaskDTO(
        String name,
        String description,
        LocalDate localDate
) { }
