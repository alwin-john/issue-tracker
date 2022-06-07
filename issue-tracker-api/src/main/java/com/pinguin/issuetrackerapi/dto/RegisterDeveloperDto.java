package com.pinguin.issuetrackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterDeveloperDto {

    private String name;

    private int teamId;

}
