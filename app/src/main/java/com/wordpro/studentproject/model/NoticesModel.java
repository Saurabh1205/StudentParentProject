package com.wordpro.studentproject.model;

import java.util.List;

public class NoticesModel {

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
         * GROUP_NAME : Annual function
         * NOTC_GROUP_MASTER_ID : 2
         * MARQUEE : Y
         * APP_NOTIFICATION : Y
         * NOTICE_DESCRIPTION : Collage Annual function start from 06/02/2019 to 10/02/2019
         * NOTICE_HEADER : Collage Annual function
         * NOTC_MASTER_ID : 2
         * NOTICE_PATTERN : EVENTBOX
         * NOTC_DISPLAY_PATTRN_ID : 2
         * ATTACHMENT_LINK :
         * ATTACHMENT_SOURCE : OFFLINE
         * ATTACHMENT_TYPE : FILE
         * STANDARD_PATH : D:/EmployeeDocuments\NOTICE\
         * SOFT_COPY_SIZE : 879394
         * SOFT_COPY_PATH : HOME\EVENTBOX\ANNUAL FUNCTION\522019_17493242.jpg
         * SOFT_COPY_NAME : Chrysanthemum.jpg
         * ATTACHMENT_NAME : Collage annual function
         * NOTC_ATTACHMENT_DTLS_ID : 3
         */

        private String GROUP_NAME;
        private String NOTC_GROUP_MASTER_ID;
        private String MARQUEE;
        private String APP_NOTIFICATION;
        private String NOTICE_DESCRIPTION;
        private String NOTICE_HEADER;
        private String NOTC_MASTER_ID;
        private String NOTICE_PATTERN;
        private String NOTC_DISPLAY_PATTRN_ID;
        private String ATTACHMENT_LINK;
        private String ATTACHMENT_SOURCE;
        private String ATTACHMENT_TYPE;
        private String STANDARD_PATH;
        private String SOFT_COPY_SIZE;
        private String SOFT_COPY_PATH;
        private String SOFT_COPY_NAME;
        private String ATTACHMENT_NAME;
        private String NOTC_ATTACHMENT_DTLS_ID;

        public String getGROUP_NAME() {
            return GROUP_NAME;
        }

        public void setGROUP_NAME(String GROUP_NAME) {
            this.GROUP_NAME = GROUP_NAME;
        }

        public String getNOTC_GROUP_MASTER_ID() {
            return NOTC_GROUP_MASTER_ID;
        }

        public void setNOTC_GROUP_MASTER_ID(String NOTC_GROUP_MASTER_ID) {
            this.NOTC_GROUP_MASTER_ID = NOTC_GROUP_MASTER_ID;
        }

        public String getMARQUEE() {
            return MARQUEE;
        }

        public void setMARQUEE(String MARQUEE) {
            this.MARQUEE = MARQUEE;
        }

        public String getAPP_NOTIFICATION() {
            return APP_NOTIFICATION;
        }

        public void setAPP_NOTIFICATION(String APP_NOTIFICATION) {
            this.APP_NOTIFICATION = APP_NOTIFICATION;
        }

        public String getNOTICE_DESCRIPTION() {
            return NOTICE_DESCRIPTION;
        }

        public void setNOTICE_DESCRIPTION(String NOTICE_DESCRIPTION) {
            this.NOTICE_DESCRIPTION = NOTICE_DESCRIPTION;
        }

        public String getNOTICE_HEADER() {
            return NOTICE_HEADER;
        }

        public void setNOTICE_HEADER(String NOTICE_HEADER) {
            this.NOTICE_HEADER = NOTICE_HEADER;
        }

        public String getNOTC_MASTER_ID() {
            return NOTC_MASTER_ID;
        }

        public void setNOTC_MASTER_ID(String NOTC_MASTER_ID) {
            this.NOTC_MASTER_ID = NOTC_MASTER_ID;
        }

        public String getNOTICE_PATTERN() {
            return NOTICE_PATTERN;
        }

        public void setNOTICE_PATTERN(String NOTICE_PATTERN) {
            this.NOTICE_PATTERN = NOTICE_PATTERN;
        }

        public String getNOTC_DISPLAY_PATTRN_ID() {
            return NOTC_DISPLAY_PATTRN_ID;
        }

        public void setNOTC_DISPLAY_PATTRN_ID(String NOTC_DISPLAY_PATTRN_ID) {
            this.NOTC_DISPLAY_PATTRN_ID = NOTC_DISPLAY_PATTRN_ID;
        }

        public String getATTACHMENT_LINK() {
            return ATTACHMENT_LINK;
        }

        public void setATTACHMENT_LINK(String ATTACHMENT_LINK) {
            this.ATTACHMENT_LINK = ATTACHMENT_LINK;
        }

        public String getATTACHMENT_SOURCE() {
            return ATTACHMENT_SOURCE;
        }

        public void setATTACHMENT_SOURCE(String ATTACHMENT_SOURCE) {
            this.ATTACHMENT_SOURCE = ATTACHMENT_SOURCE;
        }

        public String getATTACHMENT_TYPE() {
            return ATTACHMENT_TYPE;
        }

        public void setATTACHMENT_TYPE(String ATTACHMENT_TYPE) {
            this.ATTACHMENT_TYPE = ATTACHMENT_TYPE;
        }

        public String getSTANDARD_PATH() {
            return STANDARD_PATH;
        }

        public void setSTANDARD_PATH(String STANDARD_PATH) {
            this.STANDARD_PATH = STANDARD_PATH;
        }

        public String getSOFT_COPY_SIZE() {
            return SOFT_COPY_SIZE;
        }

        public void setSOFT_COPY_SIZE(String SOFT_COPY_SIZE) {
            this.SOFT_COPY_SIZE = SOFT_COPY_SIZE;
        }

        public String getSOFT_COPY_PATH() {
            return SOFT_COPY_PATH;
        }

        public void setSOFT_COPY_PATH(String SOFT_COPY_PATH) {
            this.SOFT_COPY_PATH = SOFT_COPY_PATH;
        }

        public String getSOFT_COPY_NAME() {
            return SOFT_COPY_NAME;
        }

        public void setSOFT_COPY_NAME(String SOFT_COPY_NAME) {
            this.SOFT_COPY_NAME = SOFT_COPY_NAME;
        }

        public String getATTACHMENT_NAME() {
            return ATTACHMENT_NAME;
        }

        public void setATTACHMENT_NAME(String ATTACHMENT_NAME) {
            this.ATTACHMENT_NAME = ATTACHMENT_NAME;
        }

        public String getNOTC_ATTACHMENT_DTLS_ID() {
            return NOTC_ATTACHMENT_DTLS_ID;
        }

        public void setNOTC_ATTACHMENT_DTLS_ID(String NOTC_ATTACHMENT_DTLS_ID) {
            this.NOTC_ATTACHMENT_DTLS_ID = NOTC_ATTACHMENT_DTLS_ID;
        }
    }
}
