package com.pinguin.issuetrackerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="DEVELOPER_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DeveloperDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    private transient int storyPoints;

    public DeveloperDetails(){

    }

}
