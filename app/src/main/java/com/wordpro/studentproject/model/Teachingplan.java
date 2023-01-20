package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teachingplan {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    public List<TeachingPlanData> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TeachingPlanData> getData() {
        return data;
    }

    public void setData() {
        setData();
    }

    public void setData(List<TeachingPlanData> data) {
        this.data = data;
    }

}
