package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeesChildModel {

    @SerializedName("STUD_FULL_NAME")
    @Expose
    private String sTUDFULLNAME;
    @SerializedName("FEE_TYPE_DESC")
    @Expose
    private String fEETYPEDESC;
    @SerializedName("FEE_SUB_TYPE_DESC")
    @Expose
    private String fEESUBTYPEDESC;
    @SerializedName("FEE_HEAD")
    @Expose
    private String fEEHEAD;
    @SerializedName("FEE_INSTALLMENT_NO")
    @Expose
    private String fEEINSTALLMENTNO;
    @SerializedName("ACC_BALSHEET_HEAD_DESC")
    @Expose
    private String aCCBALSHEETHEADDESC;
    @SerializedName("ACC_BALSHEET_MST_ID")
    @Expose
    private String aCCBALSHEETMSTID;
    @SerializedName("RECEIVABLE_AMOUNT")
    @Expose
    private String rECEIVABLEAMOUNT;
    @SerializedName("FEE_SUB_TYPE_ID")
    @Expose
    private String fEESUBTYPEID;
    @SerializedName("FEE_RECEIPT_AMOUNT")
    @Expose
    private String fEERECEIPTAMOUNT;
    @SerializedName("FEE_RECEI_AMOUNT_FROM")
    @Expose
    private String fEERECEIAMOUNTFROM;
    @SerializedName("FEE_RECEI_AMOUNT_UPTO")
    @Expose
    private String fEERECEIAMOUNTUPTO;
    @SerializedName("SESSION_ID")
    @Expose
    private String sESSIONID;
    @SerializedName("FEE_TYPE_ID")
    @Expose
    private String fEETYPEID;
    @SerializedName("BALANCE_AMOUNT")
    @Expose
    private String bALANCEAMOUNT;
    @SerializedName("ADVN_AMT_SUBTYPEWISE")
    @Expose
    private String aDVNAMTSUBTYPEWISE;
    @SerializedName("DISCOUNT_AMOUNT")
    @Expose
    private String dISCOUNTAMOUNT;

    public String getsTUDFULLNAME() {
        return sTUDFULLNAME;
    }

    public void setsTUDFULLNAME(String sTUDFULLNAME) {
        this.sTUDFULLNAME = sTUDFULLNAME;
    }

    public String getfEETYPEDESC() {
        return fEETYPEDESC;
    }

    public void setfEETYPEDESC(String fEETYPEDESC) {
        this.fEETYPEDESC = fEETYPEDESC;
    }

    public String getfEESUBTYPEDESC() {
        return fEESUBTYPEDESC;
    }

    public void setfEESUBTYPEDESC(String fEESUBTYPEDESC) {
        this.fEESUBTYPEDESC = fEESUBTYPEDESC;
    }

    public String getfEEHEAD() {
        return fEEHEAD;
    }

    public void setfEEHEAD(String fEEHEAD) {
        this.fEEHEAD = fEEHEAD;
    }

    public String getfEEINSTALLMENTNO() {
        return fEEINSTALLMENTNO;
    }

    public void setfEEINSTALLMENTNO(String fEEINSTALLMENTNO) {
        this.fEEINSTALLMENTNO = fEEINSTALLMENTNO;
    }

    public String getaCCBALSHEETHEADDESC() {
        return aCCBALSHEETHEADDESC;
    }

    public void setaCCBALSHEETHEADDESC(String aCCBALSHEETHEADDESC) {
        this.aCCBALSHEETHEADDESC = aCCBALSHEETHEADDESC;
    }

    public String getaCCBALSHEETMSTID() {
        return aCCBALSHEETMSTID;
    }

    public void setaCCBALSHEETMSTID(String aCCBALSHEETMSTID) {
        this.aCCBALSHEETMSTID = aCCBALSHEETMSTID;
    }

    public String getrECEIVABLEAMOUNT() {
        return rECEIVABLEAMOUNT;
    }

    public void setrECEIVABLEAMOUNT(String rECEIVABLEAMOUNT) {
        this.rECEIVABLEAMOUNT = rECEIVABLEAMOUNT;
    }

    public String getfEESUBTYPEID() {
        return fEESUBTYPEID;
    }

    public void setfEESUBTYPEID(String fEESUBTYPEID) {
        this.fEESUBTYPEID = fEESUBTYPEID;
    }

    public String getfEERECEIPTAMOUNT() {
        return fEERECEIPTAMOUNT;
    }

    public void setfEERECEIPTAMOUNT(String fEERECEIPTAMOUNT) {
        this.fEERECEIPTAMOUNT = fEERECEIPTAMOUNT;
    }

    public String getfEERECEIAMOUNTFROM() {
        return fEERECEIAMOUNTFROM;
    }

    public void setfEERECEIAMOUNTFROM(String fEERECEIAMOUNTFROM) {
        this.fEERECEIAMOUNTFROM = fEERECEIAMOUNTFROM;
    }

    public String getfEERECEIAMOUNTUPTO() {
        return fEERECEIAMOUNTUPTO;
    }

    public void setfEERECEIAMOUNTUPTO(String fEERECEIAMOUNTUPTO) {
        this.fEERECEIAMOUNTUPTO = fEERECEIAMOUNTUPTO;
    }

    public String getsESSIONID() {
        return sESSIONID;
    }

    public void setsESSIONID(String sESSIONID) {
        this.sESSIONID = sESSIONID;
    }

    public String getfEETYPEID() {
        return fEETYPEID;
    }

    public void setfEETYPEID(String fEETYPEID) {
        this.fEETYPEID = fEETYPEID;
    }

    public String getbALANCEAMOUNT() {
        return bALANCEAMOUNT;
    }

    public void setbALANCEAMOUNT(String bALANCEAMOUNT) {
        this.bALANCEAMOUNT = bALANCEAMOUNT;
    }

    public String getaDVNAMTSUBTYPEWISE() {
        return aDVNAMTSUBTYPEWISE;
    }

    public void setaDVNAMTSUBTYPEWISE(String aDVNAMTSUBTYPEWISE) {
        this.aDVNAMTSUBTYPEWISE = aDVNAMTSUBTYPEWISE;
    }

    public String getdISCOUNTAMOUNT() {
        return dISCOUNTAMOUNT;
    }

    public void setdISCOUNTAMOUNT(String dISCOUNTAMOUNT) {
        this.dISCOUNTAMOUNT = dISCOUNTAMOUNT;
    }
}
