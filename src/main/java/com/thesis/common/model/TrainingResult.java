package com.thesis.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TrainingResult {
    private Long id;

    private Long patientId;

    private Byte equipmentType;

    private Date trainingTime;

    private String trainingResult;

    private Byte trainingScore;


}