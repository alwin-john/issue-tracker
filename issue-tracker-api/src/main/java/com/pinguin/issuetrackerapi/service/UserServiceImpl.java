package com.pinguin.issuetrackerapi.service;

import com.pinguin.issuetrackerapi.dto.DeveloperDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterDeveloperDto;
import com.pinguin.issuetrackerapi.entity.DeveloperDetails;
import com.pinguin.issuetrackerapi.repository.UserRepository;
import com.pinguin.issuetrackerapi.utility.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DeveloperDetailsDto registerDeveloper(RegisterDeveloperDto registerDeveloperDto) {
        DeveloperDetails developerDetails = userRepository.save(createNewDeveloperEntity(registerDeveloperDto));
        return mapDeveloperEntityToDto(developerDetails);
    }


    @Override
    public void updateDeveloper(RegisterDeveloperDto registerDeveloperDto) {

    }

    @Override
    public List<DeveloperDetailsDto> getAllDeveloper() {
        List<DeveloperDetails> developerDetails = userRepository.findAll();
        return developerDetails.stream().map(developer -> mapDeveloperEntityToDto(developer)).collect(Collectors.toList());
    }

    @Override
    public void deleteDeveloper(int developerId) {
        DeveloperDetails developerDetails = userRepository.findById(developerId);
        userRepository.delete(developerDetails);
    }

    private DeveloperDetails createNewDeveloperEntity(RegisterDeveloperDto registerDeveloperDto) {
        return DeveloperDetails.builder().name(registerDeveloperDto.getName())
                .teamId(registerDeveloperDto.getTeamId())
                .createdDate(CommonUtil.getCurrentTimeStamp()).build();
    }

    private DeveloperDetailsDto mapDeveloperEntityToDto(DeveloperDetails developerDetails) {
        return DeveloperDetailsDto.builder().id(developerDetails.getId())
                .name(developerDetails.getName()).teamId(developerDetails.getTeamId())
                .createdDate(developerDetails.getCreatedDate()).build();
    }


}
