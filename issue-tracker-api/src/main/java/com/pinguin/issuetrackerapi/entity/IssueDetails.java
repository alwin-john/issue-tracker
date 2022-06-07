package com.pinguin.issuetrackerapi.entity;

import com.pinguin.issuetrackerapi.enums.IssueSeverity;
import com.pinguin.issuetrackerapi.enums.IssueStatus;
import com.pinguin.issuetrackerapi.enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ISSUE_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class IssueDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "issue_type")
    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "developer_id")
    private int developerId;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @Column(name = "story_points")
    private int storyPoints;

    @Column(name = "issue_severity")
    private String issueSeverity;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public IssueDetails(){

    }

}
