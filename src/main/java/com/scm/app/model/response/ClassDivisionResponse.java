package com.scm.app.model.response;


public class ClassDivisionResponse {

    private Long classId;
    private Long divisionId;

    public ClassDivisionResponse(Long classId, Long divisionId) {
        this.classId = classId;
        this.divisionId = divisionId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }
}

