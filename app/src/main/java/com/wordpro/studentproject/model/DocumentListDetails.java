package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentListDetails {

    @SerializedName("DOCUMENT_MST_ID")
    @Expose
    private String dOCUMENTMSTID;
    @SerializedName("DOCUMENT_NAME")
    @Expose
    private String dOCUMENTNAME;
    @SerializedName("DOCUMENT_DESC")
    @Expose
    private String dOCUMENTDESC;
    @SerializedName("DOCUMENT_TYPE_ID")
    @Expose
    private String dOCUMENTTYPEID;
    @SerializedName("DOCUMENT_JOB_PROF_LINK_ID")
    @Expose
    private String dOCUMENTJOBPROFLINKID;
    @SerializedName("DOCUMENT_TYPE_DESC")
    @Expose
    private String dOCUMENTTYPEDESC;
    @SerializedName("DOC_COUNT")
    @Expose
    private String dOCCOUNT;

    public String getdOCUMENTMSTID() {
        return dOCUMENTMSTID;
    }

    public void setdOCUMENTMSTID(String dOCUMENTMSTID) {
        this.dOCUMENTMSTID = dOCUMENTMSTID;
    }

    public String getdOCUMENTNAME() {
        return dOCUMENTNAME;
    }

    public void setdOCUMENTNAME(String dOCUMENTNAME) {
        this.dOCUMENTNAME = dOCUMENTNAME;
    }

    public String getdOCUMENTDESC() {
        return dOCUMENTDESC;
    }

    public void setdOCUMENTDESC(String dOCUMENTDESC) {
        this.dOCUMENTDESC = dOCUMENTDESC;
    }

    public String getdOCUMENTTYPEID() {
        return dOCUMENTTYPEID;
    }

    public void setdOCUMENTTYPEID(String dOCUMENTTYPEID) {
        this.dOCUMENTTYPEID = dOCUMENTTYPEID;
    }

    public String getdOCUMENTJOBPROFLINKID() {
        return dOCUMENTJOBPROFLINKID;
    }

    public void setdOCUMENTJOBPROFLINKID(String dOCUMENTJOBPROFLINKID) {
        this.dOCUMENTJOBPROFLINKID = dOCUMENTJOBPROFLINKID;
    }

    public String getdOCUMENTTYPEDESC() {
        return dOCUMENTTYPEDESC;
    }

    public void setdOCUMENTTYPEDESC(String dOCUMENTTYPEDESC) {
        this.dOCUMENTTYPEDESC = dOCUMENTTYPEDESC;
    }

    public String getdOCCOUNT() {
        return dOCCOUNT;
    }

    public void setdOCCOUNT(String dOCCOUNT) {
        this.dOCCOUNT = dOCCOUNT;
    }
}
