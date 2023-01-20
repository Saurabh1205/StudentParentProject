package com.wordpro.studentproject.model;

import java.util.List;

public class UpLDocuModel {

    private int status;
    private List<DtEmpsalAnnualBean> dtEmpsalAnnual;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DtEmpsalAnnualBean> getDtEmpsalAnnual() {
        return dtEmpsalAnnual;
    }

    public void setDtEmpsalAnnual(List<DtEmpsalAnnualBean> dtEmpsalAnnual) {
        this.dtEmpsalAnnual = dtEmpsalAnnual;
    }

    public static class DtEmpsalAnnualBean {
        /**
         * DOCUMENT_MST_ID : 1
         * DOCUMENT_NAME : Aadhaar Card
         * DOCUMENT_DESC : Aadhaar Card
         * DOCUMENT_TYPE_ID : 5
         * DOCUMENT_JOB_PROF_LINK_ID : 121
         * JOB_PROFILE_ID : 5
         * DOCUMENT_TYPE_DESC : Address Proof
         */

        private String DOCUMENT_MST_ID;
        private String DOCUMENT_NAME;
        private String DOCUMENT_DESC;
        private String DOCUMENT_TYPE_ID;
        private String DOCUMENT_JOB_PROF_LINK_ID;
        private String JOB_PROFILE_ID;
        private String DOCUMENT_TYPE_DESC;

        public String getDOCUMENT_MST_ID() {
            return DOCUMENT_MST_ID;
        }

        public void setDOCUMENT_MST_ID(String DOCUMENT_MST_ID) {
            this.DOCUMENT_MST_ID = DOCUMENT_MST_ID;
        }

        public String getDOCUMENT_NAME() {
            return DOCUMENT_NAME;
        }

        public void setDOCUMENT_NAME(String DOCUMENT_NAME) {
            this.DOCUMENT_NAME = DOCUMENT_NAME;
        }

        public String getDOCUMENT_DESC() {
            return DOCUMENT_DESC;
        }

        public void setDOCUMENT_DESC(String DOCUMENT_DESC) {
            this.DOCUMENT_DESC = DOCUMENT_DESC;
        }

        public String getDOCUMENT_TYPE_ID() {
            return DOCUMENT_TYPE_ID;
        }

        public void setDOCUMENT_TYPE_ID(String DOCUMENT_TYPE_ID) {
            this.DOCUMENT_TYPE_ID = DOCUMENT_TYPE_ID;
        }

        public String getDOCUMENT_JOB_PROF_LINK_ID() {
            return DOCUMENT_JOB_PROF_LINK_ID;
        }

        public void setDOCUMENT_JOB_PROF_LINK_ID(String DOCUMENT_JOB_PROF_LINK_ID) {
            this.DOCUMENT_JOB_PROF_LINK_ID = DOCUMENT_JOB_PROF_LINK_ID;
        }

        public String getJOB_PROFILE_ID() {
            return JOB_PROFILE_ID;
        }

        public void setJOB_PROFILE_ID(String JOB_PROFILE_ID) {
            this.JOB_PROFILE_ID = JOB_PROFILE_ID;
        }

        public String getDOCUMENT_TYPE_DESC() {
            return DOCUMENT_TYPE_DESC;
        }

        public void setDOCUMENT_TYPE_DESC(String DOCUMENT_TYPE_DESC) {
            this.DOCUMENT_TYPE_DESC = DOCUMENT_TYPE_DESC;
        }
    }
}
