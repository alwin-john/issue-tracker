package com.pinguin.issuetrackerapi.dto;

import com.pinguin.issuetrackerapi.enums.IssueSeverity;
import com.pinguin.issuetrackerapi.enums.IssueType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterIssueDto {

    private IssueType issueType;

    private String description;

    private int storyPoints;

    private String issueSeverity;

}
