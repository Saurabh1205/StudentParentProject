package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterGroupNameListModel  {

    @SerializedName("NOTC_GROUP_MASTER_ID")
    @Expose
    private String nOTCGROUPMASTERID;
    @SerializedName("GROUP_NAME")
    @Expose
    private String gROUPNAME;

    public String getnOTCGROUPMASTERID() {
        return nOTCGROUPMASTERID;
    }

    public void setnOTCGROUPMASTERID(String nOTCGROUPMASTERID) {
        this.nOTCGROUPMASTERID = nOTCGROUPMASTERID;
    }

    public String getgROUPNAME() {
        return gROUPNAME;
    }

    public void setgROUPNAME(String gROUPNAME) {
        this.gROUPNAME = gROUPNAME;
    }
}
