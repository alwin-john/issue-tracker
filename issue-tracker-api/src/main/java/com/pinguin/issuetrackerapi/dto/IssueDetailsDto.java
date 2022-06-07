package com.pinguin.issuetrackerapi.dto;

import com.pinguin.issuetrackerapi.enums.IssueStatus;
import com.pinguin.issuetrackerapi.enums.IssueType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IssueDetailsDto {

    private int id;

    private IssueType issueType;

    private String description;

    private LocalDateTime createdDate;

    private int developerId;

    private String developerName;

    private IssueStatus issueStatus;

    private int storyPoints;

    private String issueSeverity;

    private LocalDateTime updatedDate;
}
