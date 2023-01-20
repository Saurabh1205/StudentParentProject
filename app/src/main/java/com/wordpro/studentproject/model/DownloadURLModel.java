package com.wordpro.studentproject.model;

import java.util.List;

public class DownloadURLModel {

    private int status;
    private List<DataBean> data;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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
         * FilePath : APP_DOWNLOAD\25012019024218714.docx
         * FileName : documentfile
         */

        private String FilePath;
        private String FileName;

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }
    }
}