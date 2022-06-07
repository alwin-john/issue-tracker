package com.pinguin.issuetrackerapi.controller;

import com.pinguin.issuetrackerapi.dto.InitialDataEntity;
import com.pinguin.issuetrackerapi.dto.IssueDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterIssueDto;
import com.pinguin.issuetrackerapi.entity.IssueDetails;
import com.pinguin.issuetrackerapi.service.IssueTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issue-tracker/api/v1")
@CrossOrigin("*")
public class IssueTrackerController {

    private IssueTrackerService issueTrackerService;

    IssueTrackerController(IssueTrackerService issueTrackerService) {
        this.issueTrackerService = issueTrackerService;
    }

    @PostMapping("/issue")
    private ResponseEntity<IssueDetails> addIssueDetails(@RequestBody RegisterIssueDto registerIssueDto) {

        return new ResponseEntity<IssueDetails>(issueTrackerService.postAnIssue(registerIssueDto), HttpStatus.OK);
    }

    @DeleteMapping("/issue")
    private ResponseEntity<IssueDetails> deleteAnIssue(@RequestParam int issueId) {
        issueTrackerService.deleteId(issueId);
        return new ResponseEntity<IssueDetails>(HttpStatus.OK);
    }

    @GetMapping("/story-plan")
    private ResponseEntity<Map<Integer, List<IssueDetailsDto>>> getStoryPlan() {
        return new ResponseEntity<Map<Integer, List<IssueDetailsDto>>>(issueTrackerService.planStory(), HttpStatus.OK);
    }

    @GetMapping("/initial-data")
    private ResponseEntity<InitialDataEntity> getInitialData() {
        return new ResponseEntity<InitialDataEntity>(issueTrackerService.getInitialData(),HttpStatus.OK);
    }

}
