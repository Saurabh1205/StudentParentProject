package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetStudentDetailsModel {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("PendingJobDetails")
    @Expose
    private List<PendingJobDetail> pendingJobDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PendingJobDetail> getPendingJobDetails() {
        return pendingJobDetails;
    }

    public void setPendingJobDetails(List<PendingJobDetail> pendingJobDetails) {
        this.pendingJobDetails = pendingJobDetails;
    }


    public class PendingJobDetail {
        @SerializedName("NO_OF_JOBS")
        @Expose
        private String nOOFJOBS;
        @SerializedName("HIDDEN1")
        @Expose
        private String hIDDEN1;
        @SerializedName("JOB_DESC")
        @Expose
        private String jOBDESC;
        @SerializedName("NOTICE_DESCRIPTION")
        @Expose
        private String nOTICEDESCRIPTION;
        @SerializedName("JOB_GEN_WRK_DESIG")
        @Expose
        private String jOBGENWRKDESIG;
        @SerializedName("PENDING_JOB_SRNO")
        @Expose
        private String pENDINGJOBSRNO;
        @SerializedName("WORK_REPORTING_DTL_ID")
        @Expose
        private String wORKREPORTINGDTLID;
        @SerializedName("APPR_PATTERN_APPLI_FOR")
        @Expose
        private String aPPRPATTERNAPPLIFOR;
        @SerializedName("NOTICE_TYPE")
        @Expose
        private String nOTICETYPE;
        @SerializedName("JOB_CODE")
        @Expose
        private String jOBCODE;
        @SerializedName("JOB_GEN_PERSON_TYPE")
        @Expose
        private String jOBGENPERSONTYPE;
        @SerializedName("NOTC_MASTER_ID")
        @Expose
        private Object nOTCMASTERID;
        @SerializedName("JOB_TABLE_PK_VALUE1")
        @Expose
        private String jOBTABLEPKVALUE1;

        public String getNOOFJOBS() {
            return nOOFJOBS;
        }

        public void setNOOFJOBS(String nOOFJOBS) {
            this.nOOFJOBS = nOOFJOBS;
        }

        public String getHIDDEN1() {
            return hIDDEN1;
        }

        public void setHIDDEN1(String hIDDEN1) {
            this.hIDDEN1 = hIDDEN1;
        }

        public String getJOBDESC() {
            return jOBDESC;
        }

        public void setJOBDESC(String jOBDESC) {
            this.jOBDESC = jOBDESC;
        }

        public String getNOTICEDESCRIPTION() {
            return nOTICEDESCRIPTION;
        }

        public void setNOTICEDESCRIPTION(String nOTICEDESCRIPTION) {
            this.nOTICEDESCRIPTION = nOTICEDESCRIPTION;
        }

        public String getJOBGENWRKDESIG() {
            return jOBGENWRKDESIG;
        }

        public void setJOBGENWRKDESIG(String jOBGENWRKDESIG) {
            this.jOBGENWRKDESIG = jOBGENWRKDESIG;
        }

        public String getPENDINGJOBSRNO() {
            return pENDINGJOBSRNO;
        }

        public void setPENDINGJOBSRNO(String pENDINGJOBSRNO) {
            this.pENDINGJOBSRNO = pENDINGJOBSRNO;
        }

        public String getWORKREPORTINGDTLID() {
            return wORKREPORTINGDTLID;
        }

        public void setWORKREPORTINGDTLID(String wORKREPORTINGDTLID) {
            this.wORKREPORTINGDTLID = wORKREPORTINGDTLID;
        }

        public String getAPPRPATTERNAPPLIFOR() {
            return aPPRPATTERNAPPLIFOR;
        }

        public void setAPPRPATTERNAPPLIFOR(String aPPRPATTERNAPPLIFOR) {
            this.aPPRPATTERNAPPLIFOR = aPPRPATTERNAPPLIFOR;
        }

        public String getNOTICETYPE() {
            return nOTICETYPE;
        }

        public void setNOTICETYPE(String nOTICETYPE) {
            this.nOTICETYPE = nOTICETYPE;
        }

        public String getJOBCODE() {
            return jOBCODE;
        }

        public void setJOBCODE(String jOBCODE) {
            this.jOBCODE = jOBCODE;
        }

        public String getJOBGENPERSONTYPE() {
            return jOBGENPERSONTYPE;
        }

        public void setJOBGENPERSONTYPE(String jOBGENPERSONTYPE) {
            this.jOBGENPERSONTYPE = jOBGENPERSONTYPE;
        }

        public Object getNOTCMASTERID() {
            return nOTCMASTERID;
        }

        public void setNOTCMASTERID(Object nOTCMASTERID) {
            this.nOTCMASTERID = nOTCMASTERID;
        }

        public String getJOBTABLEPKVALUE1() {
            return jOBTABLEPKVALUE1;
        }

        public void setJOBTABLEPKVALUE1(String jOBTABLEPKVALUE1) {
            this.jOBTABLEPKVALUE1 = jOBTABLEPKVALUE1;
        }

    }

}
