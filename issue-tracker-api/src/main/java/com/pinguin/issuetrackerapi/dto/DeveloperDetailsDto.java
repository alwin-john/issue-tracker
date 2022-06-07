package com.pinguin.issuetrackerapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeveloperDetailsDto {

    private int id;

    private String name;

    private int teamId;

    private LocalDateTime createdDate;
}
