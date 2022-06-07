package com.pinguin.issuetrackerapi.service;

import com.pinguin.issuetrackerapi.dto.IssueDetailsDto;
import com.pinguin.issuetrackerapi.entity.DeveloperDetails;
import com.pinguin.issuetrackerapi.entity.IssueDetails;
import com.pinguin.issuetrackerapi.enums.IssueSeverity;
import com.pinguin.issuetrackerapi.enums.IssueStatus;
import com.pinguin.issuetrackerapi.enums.IssueType;
import com.pinguin.issuetrackerapi.repository.IssueRepository;
import com.pinguin.issuetrackerapi.repository.UserRepository;
import com.pinguin.issuetrackerapi.utility.CommonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IssueTrackerServiceImplTests {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private UserRepository userRepository;

    private IssueTrackerService issueTrackerService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        issueTrackerService = new IssueTrackerServiceImpl(issueRepository, userRepository);
    }


    @Test
    void planStory() {
        when(userRepository.findAll()).thenReturn(getDeveloper());
        when(issueRepository.findAll()).thenReturn(getIssueDetails());
        Map<Integer, List<IssueDetailsDto>> storyPlan = issueTrackerService.planStory();
        List<IssueDetailsDto> issueDetails = storyPlan.get(1);
        assertEquals("Test Developer", issueDetails.get(0).getDeveloperName());
        assertEquals("ESTIMATED", issueDetails.get(0).getIssueStatus().toString());
    }

    private List<IssueDetails> getIssueDetails() {
        List<IssueDetails> issueDetailsList = new ArrayList<>();

        IssueDetails issueDetails1 = IssueDetails.builder().id(1)
                .issueType(IssueType.STORY).description("test").createdDate(CommonUtil.getCurrentTimeStamp())
                .developerId(1).issueStatus(IssueStatus.NEW).storyPoints(10).issueSeverity("")
                .updatedDate(CommonUtil.getCurrentTimeStamp()).build();

        IssueDetails issueDetails2 = IssueDetails.builder().id(1)
                .issueType(IssueType.BUG).description("test").createdDate(CommonUtil.getCurrentTimeStamp())
                .developerId(1).issueStatus(IssueStatus.NEW).storyPoints(0).issueSeverity(IssueSeverity.MAJOR.toString())
                .updatedDate(CommonUtil.getCurrentTimeStamp()).build();
        issueDetailsList.add(issueDetails1);
        issueDetailsList.add(issueDetails2);
        return issueDetailsList;
    }

    private List<DeveloperDetails> getDeveloper() {
        List<DeveloperDetails> developerDetailsList = new ArrayList<>();

        DeveloperDetails developerDetails = DeveloperDetails.builder()
                .id(1).name("Test Developer").teamId(1).createdDate(CommonUtil.getCurrentTimeStamp()).storyPoints(0).build();
        developerDetailsList.add(developerDetails);
        return developerDetailsList;
    }
}