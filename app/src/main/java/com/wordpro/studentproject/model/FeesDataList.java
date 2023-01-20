package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeesDataList {

    @SerializedName("FEE_HEAD")
    @Expose
    private String fEEHEAD;
    @SerializedName("Parent")
    @Expose
    private List<FeesParentModel> parent = null;

    public String getfEEHEAD() {
        return fEEHEAD;
    }

    public void setfEEHEAD(String fEEHEAD) {
        this.fEEHEAD = fEEHEAD;
    }

    public List<FeesParentModel> getParent() {
        return parent;
    }

    public void setParent(List<FeesParentModel> parent) {
        this.parent = parent;
    }
}
