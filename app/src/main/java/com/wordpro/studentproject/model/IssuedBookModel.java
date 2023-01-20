package com.wordpro.studentproject.model;

import java.util.List;

public class IssuedBookModel {

    private int status;
    private List<CirculationRuleDtlsBean> CirculationRuleDtls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CirculationRuleDtlsBean> getCirculationRuleDtls() {
        return CirculationRuleDtls;
    }

    public void setCirculationRuleDtls(List<CirculationRuleDtlsBean> CirculationRuleDtls) {
        this.CirculationRuleDtls = CirculationRuleDtls;
    }

    public static class CirculationRuleDtlsBean {
        /**
         * BOOK_ISSUE_REGISTER_ID : 12
         * PERSON_TYPE : STUDENT
         * PERSON_ID : 1032130536
         * MEMBER_NAME : Patel Mayank P
         * ACCESS_ID : 36
         * ACCESSION_NUMBER : M000000027
         * BOOK_TITLE : A Guidebook
         * COMB_AUTH_LASTNAME : Mr.   Kakade S S
         * PUBLICATION_ID : 19
         * PUBLICATION_NAME : PEARSON
         * BOOK_TYPE_ID : 1
         * BOOK_TYPE_DESCRIPTION : REGULAR
         * BOOK_ALLOCATION_DATE : 2/10/2018 7:10:00 AM
         * BOOK_TENTITIVE_RETURNING_DATE : 2/6/2018 7:10:00 AM
         * BOOK_RETURNING_DATE : 2/10/2018 7:10:00 AM
         * BOOK_RETURNING_STATUS : DAMAGED
         * FINE_APPLICABLE : Y
         * FINE_AMOUNT : 150
         */

        private String BOOK_ISSUE_REGISTER_ID;
        private String PERSON_TYPE;
        private String PERSON_ID;
        private String MEMBER_NAME;
        private String ACCESS_ID;
        private String ACCESSION_NUMBER;
        private String BOOK_TITLE;
        private String COMB_AUTH_LASTNAME;
        private String PUBLICATION_ID;
        private String PUBLICATION_NAME;
        private String BOOK_TYPE_ID;
        private String BOOK_TYPE_DESCRIPTION;
        private String BOOK_ALLOCATION_DATE;
        private String BOOK_TENTITIVE_RETURNING_DATE;
        private String BOOK_RETURNING_DATE;
        private String BOOK_RETURNING_STATUS;
        private String FINE_APPLICABLE;
        private String FINE_AMOUNT;

        public String getBOOK_ISSUE_REGISTER_ID() {
            return BOOK_ISSUE_REGISTER_ID;
        }

        public void setBOOK_ISSUE_REGISTER_ID(String BOOK_ISSUE_REGISTER_ID) {
            this.BOOK_ISSUE_REGISTER_ID = BOOK_ISSUE_REGISTER_ID;
        }

        public String getPERSON_TYPE() {
            return PERSON_TYPE;
        }

        public void setPERSON_TYPE(String PERSON_TYPE) {
            this.PERSON_TYPE = PERSON_TYPE;
        }

        public String getPERSON_ID() {
            return PERSON_ID;
        }

        public void setPERSON_ID(String PERSON_ID) {
            this.PERSON_ID = PERSON_ID;
        }

        public String getMEMBER_NAME() {
            return MEMBER_NAME;
        }

        public void setMEMBER_NAME(String MEMBER_NAME) {
            this.MEMBER_NAME = MEMBER_NAME;
        }

        public String getACCESS_ID() {
            return ACCESS_ID;
        }

        public void setACCESS_ID(String ACCESS_ID) {
            this.ACCESS_ID = ACCESS_ID;
        }

        public String getACCESSION_NUMBER() {
            return ACCESSION_NUMBER;
        }

        public void setACCESSION_NUMBER(String ACCESSION_NUMBER) {
            this.ACCESSION_NUMBER = ACCESSION_NUMBER;
        }

        public String getBOOK_TITLE() {
            return BOOK_TITLE;
        }

        public void setBOOK_TITLE(String BOOK_TITLE) {
            this.BOOK_TITLE = BOOK_TITLE;
        }

        public String getCOMB_AUTH_LASTNAME() {
            return COMB_AUTH_LASTNAME;
        }

        public void setCOMB_AUTH_LASTNAME(String COMB_AUTH_LASTNAME) {
            this.COMB_AUTH_LASTNAME = COMB_AUTH_LASTNAME;
        }

        public String getPUBLICATION_ID() {
            return PUBLICATION_ID;
        }

        public void setPUBLICATION_ID(String PUBLICATION_ID) {
            this.PUBLICATION_ID = PUBLICATION_ID;
        }

        public String getPUBLICATION_NAME() {
            return PUBLICATION_NAME;
        }

        public void setPUBLICATION_NAME(String PUBLICATION_NAME) {
            this.PUBLICATION_NAME = PUBLICATION_NAME;
        }

        public String getBOOK_TYPE_ID() {
            return BOOK_TYPE_ID;
        }

        public void setBOOK_TYPE_ID(String BOOK_TYPE_ID) {
            this.BOOK_TYPE_ID = BOOK_TYPE_ID;
        }

        public String getBOOK_TYPE_DESCRIPTION() {
            return BOOK_TYPE_DESCRIPTION;
        }

        public void setBOOK_TYPE_DESCRIPTION(String BOOK_TYPE_DESCRIPTION) {
            this.BOOK_TYPE_DESCRIPTION = BOOK_TYPE_DESCRIPTION;
        }

        public String getBOOK_ALLOCATION_DATE() {
            return BOOK_ALLOCATION_DATE;
        }

        public void setBOOK_ALLOCATION_DATE(String BOOK_ALLOCATION_DATE) {
            this.BOOK_ALLOCATION_DATE = BOOK_ALLOCATION_DATE;
        }

        public String getBOOK_TENTITIVE_RETURNING_DATE() {
            return BOOK_TENTITIVE_RETURNING_DATE;
        }

        public void setBOOK_TENTITIVE_RETURNING_DATE(String BOOK_TENTITIVE_RETURNING_DATE) {
            this.BOOK_TENTITIVE_RETURNING_DATE = BOOK_TENTITIVE_RETURNING_DATE;
        }

        public String getBOOK_RETURNING_DATE() {
            return BOOK_RETURNING_DATE;
        }

        public void setBOOK_RETURNING_DATE(String BOOK_RETURNING_DATE) {
            this.BOOK_RETURNING_DATE = BOOK_RETURNING_DATE;
        }

        public String getBOOK_RETURNING_STATUS() {
            return BOOK_RETURNING_STATUS;
        }

        public void setBOOK_RETURNING_STATUS(String BOOK_RETURNING_STATUS) {
            this.BOOK_RETURNING_STATUS = BOOK_RETURNING_STATUS;
        }

        public String getFINE_APPLICABLE() {
            return FINE_APPLICABLE;
        }

        public void setFINE_APPLICABLE(String FINE_APPLICABLE) {
            this.FINE_APPLICABLE = FINE_APPLICABLE;
        }

        public String getFINE_AMOUNT() {
            return FINE_AMOUNT;
        }

        public void setFINE_AMOUNT(String FINE_AMOUNT) {
            this.FINE_AMOUNT = FINE_AMOUNT;
        }
    }


}
