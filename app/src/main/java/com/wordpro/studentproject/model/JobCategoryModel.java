package com.wordpro.studentproject.model;

import java.util.List;

public class JobCategoryModel {

    /**
     * status : 1
     * STATUS : TRUE
     * REASON :
     * Records : [{"WELLDONE":"","ALERT":"1","APPROVALS":"","WARNING":"","INFO":"","V_OTHER":""}]
     */

    private int status;
    private String STATUS;
    private String REASON;
    private List<RecordsBean> Records;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    public List<RecordsBean> getRecords() {
        return Records;
    }

    public void setRecords(List<RecordsBean> Records) {
        this.Records = Records;
    }

    public static class RecordsBean {
        /**
         * WELLDONE :
         * ALERT : 1
         * APPROVALS :
         * WARNING :
         * INFO :
         * V_OTHER :
         */

        private String WELLDONE;
        private String ALERT;
        private String APPROVALS;
        private String WARNING;
        private String INFO;
        private String V_OTHER;

        public String getWELLDONE() {
            return WELLDONE;
        }

        public void setWELLDONE(String WELLDONE) {
            this.WELLDONE = WELLDONE;
        }

        public String getALERT() {
            return ALERT;
        }

        public void setALERT(String ALERT) {
            this.ALERT = ALERT;
        }

        public String getAPPROVALS() {
            return APPROVALS;
        }

        public void setAPPROVALS(String APPROVALS) {
            this.APPROVALS = APPROVALS;
        }

        public String getWARNING() {
            return WARNING;
        }

        public void setWARNING(String WARNING) {
            this.WARNING = WARNING;
        }

        public String getINFO() {
            return INFO;
        }

        public void setINFO(String INFO) {
            this.INFO = INFO;
        }

        public String getV_OTHER() {
            return V_OTHER;
        }

        public void setV_OTHER(String V_OTHER) {
            this.V_OTHER = V_OTHER;
        }
    }

}
