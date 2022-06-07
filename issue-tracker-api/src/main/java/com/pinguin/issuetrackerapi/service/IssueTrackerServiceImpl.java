package com.pinguin.issuetrackerapi.service;

import com.pinguin.issuetrackerapi.dto.InitialDataEntity;
import com.pinguin.issuetrackerapi.dto.IssueDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterIssueDto;
import com.pinguin.issuetrackerapi.entity.DeveloperDetails;
import com.pinguin.issuetrackerapi.entity.IssueDetails;
import com.pinguin.issuetrackerapi.enums.IssueSeverity;
import com.pinguin.issuetrackerapi.enums.IssueStatus;
import com.pinguin.issuetrackerapi.enums.IssueType;
import com.pinguin.issuetrackerapi.exception.BusinessException;
import com.pinguin.issuetrackerapi.exception.BusinessExceptionReason;
import com.pinguin.issuetrackerapi.repository.IssueRepository;
import com.pinguin.issuetrackerapi.repository.UserRepository;
import com.pinguin.issuetrackerapi.utility.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IssueTrackerServiceImpl implements IssueTrackerService {

    private IssueRepository issueRepository;

    private UserRepository userRepository;

    @Autowired
    IssueTrackerServiceImpl(IssueRepository issueRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IssueDetails postAnIssue(RegisterIssueDto registerIssueDto) {

        int storyPoints = Optional.of(registerIssueDto.getStoryPoints()).orElse(0);
        String issueSeverity = Optional.of(registerIssueDto.getIssueSeverity()).orElse("");
        IssueDetails issueDetails = IssueDetails.builder()
                .issueType(registerIssueDto.getIssueType()).description(registerIssueDto.getDescription())
                .createdDate(CommonUtil.getCurrentTimeStamp()).issueStatus(IssueStatus.NEW)
                .storyPoints(storyPoints).issueSeverity(registerIssueDto.getIssueSeverity().toString())
                .build();
        return issueRepository.save(issueDetails);
    }

    @Override
    public void deleteId(int issueId) {
        IssueDetails issueDetails = issueRepository.findById(issueId);
        Optional.ofNullable(issueDetails).orElseThrow(() -> new BusinessException(BusinessExceptionReason.NOT_FOUND, "No Issue With This Id Found"));
        issueRepository.delete(issueDetails);
    }

    @Override
    public Map<Integer, List<IssueDetailsDto>> planStory() {

        Comparator<IssueDetails> sortWithStoryPoints = Comparator.comparing(IssueDetails::getStoryPoints, (s1, s2) -> {
            return s2.compareTo(s1);
        });
        Comparator<DeveloperDetails> sortDevelopers = Comparator.comparing(DeveloperDetails::getStoryPoints, (s1, s2) -> {
            return s2.compareTo(s1);
        });
        List<DeveloperDetails> developerDetailsList = userRepository.findAll();
        List<IssueDetails> issueDetails = issueRepository.findAll().stream()
                .filter(issue -> issue.getIssueType().equals(IssueType.STORY))
                .filter(story -> story.getIssueStatus().equals(IssueStatus.NEW) || story.getIssueStatus().equals(IssueStatus.ESTIMATED))
                .sorted(sortWithStoryPoints).collect(Collectors.toList());

        // Throw exception if no developer found
        if(developerDetailsList.size()<1){
            throw new BusinessException(BusinessExceptionReason.NOT_FOUND, "User Not Found");
        }

        // Find the sum of all stories that are not completed
        int totalUnEstimatedStoryPoints = issueDetails.stream().mapToInt(IssueDetails::getStoryPoints).sum();
        // Find the total story points for a week
        int totalStoryPointsThatCanDoInAWeek = developerDetailsList.size() * 10;

        // Find how many week need to complete all story
        long totalNoOfWeekToCompleteStory = (long) totalUnEstimatedStoryPoints / totalStoryPointsThatCanDoInAWeek;

        ListIterator<IssueDetails> issueDetailsListIterator;
        ListIterator<DeveloperDetails> developerDetailsListIterator;
        Map<Integer, List<IssueDetailsDto>> storyPlan = new HashMap<>();
        int week = 1;

        // Start assign story for each week. Iterate n(number of week) time to assign the story
        while (totalNoOfWeekToCompleteStory >= 0) {
            // During the start of each new week story all developer have full potential
            // Assign full capacity to each developer during the start of each week
            developerDetailsList.stream().forEach(developerDetails -> developerDetails.setStoryPoints(10));

            issueDetailsListIterator = issueDetails.listIterator();
            List<IssueDetailsDto> plannedStories = new ArrayList<>();
            while (issueDetailsListIterator.hasNext()) {

                IssueDetails story = issueDetailsListIterator.next();
                // Sort the developers by its story points so developer with more story point will get the task.
                // Step to ensure all developers get the task almost equal
                developerDetailsList.sort(sortDevelopers);
                developerDetailsListIterator = developerDetailsList.listIterator();

                while (developerDetailsListIterator.hasNext()) {

                    DeveloperDetails developer = developerDetailsListIterator.next();

                    // Assign a task to a developer if the story pont is less than or equal to his capacity
                    if (developer.getStoryPoints() >= story.getStoryPoints()) {
                        story.setDeveloperId(developer.getId());
                        story.setIssueStatus(IssueStatus.ESTIMATED);
                        issueRepository.saveAndFlush(story);
                        int remainingStoryPointForDeveloperAfterAssignment = developer.getStoryPoints() - story.getStoryPoints();
                        developer.setStoryPoints(remainingStoryPointForDeveloperAfterAssignment);
                        plannedStories.add(mapIssueDetailsToDto(story, developer.getName()));
                        issueDetailsListIterator.remove();
                        break;
                    }
                }
            }
            storyPlan.put(week, plannedStories);
            week++;
            totalNoOfWeekToCompleteStory--;
        }

        return storyPlan;
    }

    @Override
    public InitialDataEntity getInitialData() {
        List<String> issueTypes = new ArrayList<>();
        List<String> issueSeverityList = new ArrayList<>();
        for (IssueType type : IssueType.values()) {
            issueTypes.add(type.toString());
        }

        for (IssueSeverity issueSeverity : IssueSeverity.values()) {
            issueSeverityList.add(issueSeverity.toString());
        }

        return new InitialDataEntity(issueTypes, issueSeverityList);
    }

    private IssueDetailsDto mapIssueDetailsToDto(IssueDetails issueDetails, String developerName) {
        return IssueDetailsDto.builder().id(issueDetails.getId()).issueType(issueDetails.getIssueType())
                .issueStatus(issueDetails.getIssueStatus()).description(issueDetails.getDescription())
                .developerId(issueDetails.getDeveloperId()).developerName(developerName)
                .createdDate(issueDetails.getCreatedDate()).storyPoints(issueDetails.getStoryPoints()).build();
    }
}
