package com.pinguin.issuetrackerapi.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@Builder
@AllArgsConstructor
@Getter
@Setter
public class InitialDataEntity {

    private List<String> issueType;

    private List<String> issueSeverity;

    public InitialDataEntity(){

    }
}
