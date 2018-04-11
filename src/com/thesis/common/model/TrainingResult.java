package com.thesis.common.model;

import java.util.Date;

public class TrainingResult {
    private Long id;

    private Long patientId;

    private Byte equipmentType;

    private Date trainingTime;

    private String trainingResult;

    private Byte trainingScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Byte getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(Byte equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Date getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(Date trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getTrainingResult() {
        return trainingResult;
    }

    public void setTrainingResult(String trainingResult) {
        this.trainingResult = trainingResult == null ? null : trainingResult.trim();
    }

    public Byte getTrainingScore() {
        return trainingScore;
    }

    public void setTrainingScore(Byte trainingScore) {
        this.trainingScore = trainingScore;
    }
}