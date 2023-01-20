package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 08-05-2018.
 */

public class SpanwiseModel {

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
         * Attendance_Date : 12/18/2017 12:00:00 AM
         * ToTal_periods : 7
         * Period_Attended : 0
         */

        private String Attendance_Date;
        private String ToTal_periods;
        private String Period_Attended;
        private String CommonDate;

        public String getCommonDate() {
            return CommonDate;
        }

        public void setCommonDate(String commonDate) {
            CommonDate = commonDate;
        }

        public String getAttendance_Date() {
            return Attendance_Date;
        }

        public void setAttendance_Date(String Attendance_Date) {
            this.Attendance_Date = Attendance_Date;
        }

        public String getToTal_periods() {
            return ToTal_periods;
        }

        public void setToTal_periods(String ToTal_periods) {
            this.ToTal_periods = ToTal_periods;
        }

        public String getPeriod_Attended() {
            return Period_Attended;
        }

        public void setPeriod_Attended(String Period_Attended) {
            this.Period_Attended = Period_Attended;
        }
    }
}
