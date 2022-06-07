package com.pinguin.issuetrackerapi.repository;

import com.pinguin.issuetrackerapi.entity.IssueDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<IssueDetails, Integer> {

    IssueDetails findById(int issueId);
}