package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingJobType {

    @Expose
    private Integer status;
    @SerializedName("PendingJobDetails")
    @Expose
    private List<NewGetNoticDetails> pendingJobDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<NewGetNoticDetails> getPendingJobDetails() {
        return pendingJobDetails;
    }

    public void setPendingJobDetails(List<NewGetNoticDetails> pendingJobDetails) {
        this.pendingJobDetails = pendingJobDetails;
    }


}
