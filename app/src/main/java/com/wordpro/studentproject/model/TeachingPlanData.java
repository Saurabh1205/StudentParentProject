package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeachingPlanData {


    @SerializedName("Employee_Id")
    @Expose
    private String employeeId;
    @SerializedName("Emp_DEP_NUMBER")
    @Expose
    private String empDEPNUMBER;
    @SerializedName("Session_id")
    @Expose
    private String sessionId;
    @SerializedName("SEMESTER_TYPE")
    @Expose
    private String sEMESTERTYPE;
    @SerializedName("SUBJECT_DETAIL_ID")
    @Expose
    private String sUBJECTDETAILID;
    @SerializedName("Proposed_No_Of_Lectures")
    @Expose
    private String proposedNoOfLectures;
    @SerializedName("UNIVERSITY_CODE")
    @Expose
    private String uNIVERSITYCODE;
    @SerializedName("SUBJECT_DESCRIPTION")
    @Expose
    private String sUBJECTDESCRIPTION;
    @SerializedName("PROF_EMP_FN_MN_SHORT")
    @Expose
    private String pROFEMPFNMNSHORT;
    @SerializedName("ACAD_SESS_NAME")
    @Expose
    private String aCADSESSNAME;
    @SerializedName("CENTRE_CODE")
    @Expose
    private String cENTRECODE;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpDEPNUMBER() {
        return empDEPNUMBER;
    }

    public void setEmpDEPNUMBER(String empDEPNUMBER) {
        this.empDEPNUMBER = empDEPNUMBER;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSEMESTERTYPE() {
        return sEMESTERTYPE;
    }

    public void setSEMESTERTYPE(String sEMESTERTYPE) {
        this.sEMESTERTYPE = sEMESTERTYPE;
    }

    public String getSUBJECTDETAILID() {
        return sUBJECTDETAILID;
    }

    public void setSUBJECTDETAILID(String sUBJECTDETAILID) {
        this.sUBJECTDETAILID = sUBJECTDETAILID;
    }

    public String getProposedNoOfLectures() {
        return proposedNoOfLectures;
    }

    public void setProposedNoOfLectures(String proposedNoOfLectures) {
        this.proposedNoOfLectures = proposedNoOfLectures;
    }

    public String getUNIVERSITYCODE() {
        return uNIVERSITYCODE;
    }

    public void setUNIVERSITYCODE(String uNIVERSITYCODE) {
        this.uNIVERSITYCODE = uNIVERSITYCODE;
    }

    public String getSUBJECTDESCRIPTION() {
        return sUBJECTDESCRIPTION;
    }

    public void setSUBJECTDESCRIPTION(String sUBJECTDESCRIPTION) {
        this.sUBJECTDESCRIPTION = sUBJECTDESCRIPTION;
    }

    public String getPROFEMPFNMNSHORT() {
        return pROFEMPFNMNSHORT;
    }

    public void setPROFEMPFNMNSHORT(String pROFEMPFNMNSHORT) {
        this.pROFEMPFNMNSHORT = pROFEMPFNMNSHORT;
    }

    public String getACADSESSNAME() {
        return aCADSESSNAME;
    }

    public void setACADSESSNAME(String aCADSESSNAME) {
        this.aCADSESSNAME = aCADSESSNAME;
    }

    public String getCENTRECODE() {
        return cENTRECODE;
    }

    public void setCENTRECODE(String cENTRECODE) {
        this.cENTRECODE = cENTRECODE;
    }

}
