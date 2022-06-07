package com.pinguin.issuetrackerapi.repository;

import com.pinguin.issuetrackerapi.entity.DeveloperDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DeveloperDetails, Integer> {

    DeveloperDetails findById(int developerId);
}
