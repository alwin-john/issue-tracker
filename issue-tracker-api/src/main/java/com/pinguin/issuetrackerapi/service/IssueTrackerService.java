package com.pinguin.issuetrackerapi.service;

import com.pinguin.issuetrackerapi.dto.InitialDataEntity;
import com.pinguin.issuetrackerapi.dto.IssueDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterIssueDto;
import com.pinguin.issuetrackerapi.entity.IssueDetails;

import java.util.List;
import java.util.Map;

public interface IssueTrackerService {

    IssueDetails postAnIssue(RegisterIssueDto registerIssueDto);

    void deleteId(int issueId);

    Map<Integer, List<IssueDetailsDto>> planStory();

    InitialDataEntity getInitialData();
}
