package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterGroupNameModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<FilterGroupNameListModel> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<FilterGroupNameListModel> getData() {
        return data;
    }

    public void setData(List<FilterGroupNameListModel> data) {
        this.data = data;
    }
}
