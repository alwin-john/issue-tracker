package com.pinguin.issuetrackerapi.service;

import com.pinguin.issuetrackerapi.dto.DeveloperDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterDeveloperDto;

import java.util.List;

public interface UserService {

    DeveloperDetailsDto registerDeveloper(RegisterDeveloperDto registerDeveloperDto);

    void updateDeveloper(RegisterDeveloperDto registerDeveloperDto);

    List<DeveloperDetailsDto> getAllDeveloper();

    void deleteDeveloper(int developerId);
}
