package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 30-04-2018.
 */

public class UnivSyllabusModel {

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * SYLLABUS_GROUP_ID : 2590
         * APPLICABLE_NUMBER : 1
         * SYLLABUS_DESC : Industrial and Technology Management (2015 course)
         * SYLLABUS_UNIT_TYPE : U
         * SUBJECT_GROUP_ID : 3283
         * SYLLABUS_GROUP_DET_ID : 17494
         * SYLLABUS_DESCRIPTION : Introduction to managerial and economical demand
         * WEIGHTAGE : 16
         * PERCENTAGE : 22.86
         * SYLLABUS_TOPIC_GROUP_ID : 50822
         * TOPIC_NAME : Types of business ownership
         * TOPIC_DESCRIPTION : Partnership (Act 1934), LLP (Limited Liability Partnership), (Act2008). Business Organizations: Line organization
         * NO_OF_LECTURES : 1
         * Unit_Tot_Lectures : 6
         */

        private String SYLLABUS_GROUP_ID;
        private String APPLICABLE_NUMBER;
        private String SYLLABUS_DESC;
        private String SYLLABUS_UNIT_TYPE;
        private String SUBJECT_GROUP_ID;
        private String SYLLABUS_GROUP_DET_ID;
        private String SYLLABUS_DESCRIPTION;
        private String WEIGHTAGE;
        private String PERCENTAGE;
        private String SYLLABUS_TOPIC_GROUP_ID;
        private String TOPIC_NAME;
        private String TOPIC_DESCRIPTION;
        private String NO_OF_LECTURES;
        private String Unit_Tot_Lectures;
        private String UNIT_NO;

        public String getUNIT_NO() {
            return UNIT_NO;
        }

        public void setUNIT_NO(String UNIT_NO) {
            this.UNIT_NO = UNIT_NO;
        }

        public String getSYLLABUS_GROUP_ID() {
            return SYLLABUS_GROUP_ID;
        }

        public void setSYLLABUS_GROUP_ID(String SYLLABUS_GROUP_ID) {
            this.SYLLABUS_GROUP_ID = SYLLABUS_GROUP_ID;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getSYLLABUS_DESC() {
            return SYLLABUS_DESC;
        }

        public void setSYLLABUS_DESC(String SYLLABUS_DESC) {
            this.SYLLABUS_DESC = SYLLABUS_DESC;
        }

        public String getSYLLABUS_UNIT_TYPE() {
            return SYLLABUS_UNIT_TYPE;
        }

        public void setSYLLABUS_UNIT_TYPE(String SYLLABUS_UNIT_TYPE) {
            this.SYLLABUS_UNIT_TYPE = SYLLABUS_UNIT_TYPE;
        }

        public String getSUBJECT_GROUP_ID() {
            return SUBJECT_GROUP_ID;
        }

        public void setSUBJECT_GROUP_ID(String SUBJECT_GROUP_ID) {
            this.SUBJECT_GROUP_ID = SUBJECT_GROUP_ID;
        }

        public String getSYLLABUS_GROUP_DET_ID() {
            return SYLLABUS_GROUP_DET_ID;
        }

        public void setSYLLABUS_GROUP_DET_ID(String SYLLABUS_GROUP_DET_ID) {
            this.SYLLABUS_GROUP_DET_ID = SYLLABUS_GROUP_DET_ID;
        }

        public String getSYLLABUS_DESCRIPTION() {
            return SYLLABUS_DESCRIPTION;
        }

        public void setSYLLABUS_DESCRIPTION(String SYLLABUS_DESCRIPTION) {
            this.SYLLABUS_DESCRIPTION = SYLLABUS_DESCRIPTION;
        }

        public String getWEIGHTAGE() {
            return WEIGHTAGE;
        }

        public void setWEIGHTAGE(String WEIGHTAGE) {
            this.WEIGHTAGE = WEIGHTAGE;
        }

        public String getPERCENTAGE() {
            return PERCENTAGE;
        }

        public void setPERCENTAGE(String PERCENTAGE) {
            this.PERCENTAGE = PERCENTAGE;
        }

        public String getSYLLABUS_TOPIC_GROUP_ID() {
            return SYLLABUS_TOPIC_GROUP_ID;
        }

        public void setSYLLABUS_TOPIC_GROUP_ID(String SYLLABUS_TOPIC_GROUP_ID) {
            this.SYLLABUS_TOPIC_GROUP_ID = SYLLABUS_TOPIC_GROUP_ID;
        }

        public String getTOPIC_NAME() {
            return TOPIC_NAME;
        }

        public void setTOPIC_NAME(String TOPIC_NAME) {
            this.TOPIC_NAME = TOPIC_NAME;
        }

        public String getTOPIC_DESCRIPTION() {
            return TOPIC_DESCRIPTION;
        }

        public void setTOPIC_DESCRIPTION(String TOPIC_DESCRIPTION) {
            this.TOPIC_DESCRIPTION = TOPIC_DESCRIPTION;
        }

        public String getNO_OF_LECTURES() {
            return NO_OF_LECTURES;
        }

        public void setNO_OF_LECTURES(String NO_OF_LECTURES) {
            this.NO_OF_LECTURES = NO_OF_LECTURES;
        }

        public String getUnit_Tot_Lectures() {
            return Unit_Tot_Lectures;
        }

        public void setUnit_Tot_Lectures(String Unit_Tot_Lectures) {
            this.Unit_Tot_Lectures = Unit_Tot_Lectures;
        }
    }
}
