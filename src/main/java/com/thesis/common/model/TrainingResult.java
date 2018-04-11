package com.thesis.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrainingResult {
    private Long id;

    private Long patientId;

    private Byte equipmentType;

    private Date trainingTime;

    private String trainingResult;

    private Byte trainingScore;

}