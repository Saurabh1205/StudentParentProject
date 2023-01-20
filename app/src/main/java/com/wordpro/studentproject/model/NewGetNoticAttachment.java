package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewGetNoticAttachment {
    @SerializedName("SRNO")
    @Expose
    private Integer sRNO;
    @SerializedName("ATTACHMENT_NAME")
    @Expose
    private String aTTACHMENTNAME;
    @SerializedName("SOFT_COPY_NAME")
    @Expose
    private String sOFTCOPYNAME;
    @SerializedName("SOFT_COPY_PATH")
    @Expose
    private String sOFTCOPYPATH;
    @SerializedName("SOFT_COPY_SIZE")
    @Expose
    private String sOFTCOPYSIZE;
    @SerializedName("STANDARD_PATH")
    @Expose
    private String sTANDARDPATH;
    @SerializedName("PATH_TYPE")
    @Expose
    private String pATHTYPE;
    @SerializedName("Path")
    @Expose
    private String path;

    public Integer getsRNO() {
        return sRNO;
    }

    public void setsRNO(Integer sRNO) {
        this.sRNO = sRNO;
    }

    public String getaTTACHMENTNAME() {
        return aTTACHMENTNAME;
    }

    public void setaTTACHMENTNAME(String aTTACHMENTNAME) {
        this.aTTACHMENTNAME = aTTACHMENTNAME;
    }

    public String getsOFTCOPYNAME() {
        return sOFTCOPYNAME;
    }

    public void setsOFTCOPYNAME(String sOFTCOPYNAME) {
        this.sOFTCOPYNAME = sOFTCOPYNAME;
    }

    public String getsOFTCOPYPATH() {
        return sOFTCOPYPATH;
    }

    public void setsOFTCOPYPATH(String sOFTCOPYPATH) {
        this.sOFTCOPYPATH = sOFTCOPYPATH;
    }

    public String getsOFTCOPYSIZE() {
        return sOFTCOPYSIZE;
    }

    public void setsOFTCOPYSIZE(String sOFTCOPYSIZE) {
        this.sOFTCOPYSIZE = sOFTCOPYSIZE;
    }

    public String getsTANDARDPATH() {
        return sTANDARDPATH;
    }

    public void setsTANDARDPATH(String sTANDARDPATH) {
        this.sTANDARDPATH = sTANDARDPATH;
    }

    public String getpATHTYPE() {
        return pATHTYPE;
    }

    public void setpATHTYPE(String pATHTYPE) {
        this.pATHTYPE = pATHTYPE;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}