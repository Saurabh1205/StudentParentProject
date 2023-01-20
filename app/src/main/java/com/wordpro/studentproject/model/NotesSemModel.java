package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 26-06-2018.
 */

public class NotesSemModel {


    /**
     * status : 1
     * Semester_Dtls : [{"SEMESTER_TYPE_ID":"1","SEMESTER_TYPE_CODE":"O","SEMESTER_TYPE_NAME":"ODD","GRP_SEMESTER_IDENTITY":"BISEMESTER"},{"SEMESTER_TYPE_ID":"2","SEMESTER_TYPE_CODE":"E","SEMESTER_TYPE_NAME":"EVEN","GRP_SEMESTER_IDENTITY":"BISEMESTER"}]
     */

    private int status;
    private List<SemesterDtlsBean> Semester_Dtls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SemesterDtlsBean> getSemester_Dtls() {
        return Semester_Dtls;
    }

    public void setSemester_Dtls(List<SemesterDtlsBean> Semester_Dtls) {
        this.Semester_Dtls = Semester_Dtls;
    }

    public static class SemesterDtlsBean {
        /**
         * SEMESTER_TYPE_ID : 1
         * SEMESTER_TYPE_CODE : O
         * SEMESTER_TYPE_NAME : ODD
         * GRP_SEMESTER_IDENTITY : BISEMESTER
         */

        private String SEMESTER_TYPE_ID;
        private String SEMESTER_TYPE_CODE;
        private String SEMESTER_TYPE_NAME;
        private String GRP_SEMESTER_IDENTITY;

        public String getSEMESTER_TYPE_ID() {
            return SEMESTER_TYPE_ID;
        }

        public void setSEMESTER_TYPE_ID(String SEMESTER_TYPE_ID) {
            this.SEMESTER_TYPE_ID = SEMESTER_TYPE_ID;
        }

        public String getSEMESTER_TYPE_CODE() {
            return SEMESTER_TYPE_CODE;
        }

        public void setSEMESTER_TYPE_CODE(String SEMESTER_TYPE_CODE) {
            this.SEMESTER_TYPE_CODE = SEMESTER_TYPE_CODE;
        }

        public String getSEMESTER_TYPE_NAME() {
            return SEMESTER_TYPE_NAME;
        }

        public void setSEMESTER_TYPE_NAME(String SEMESTER_TYPE_NAME) {
            this.SEMESTER_TYPE_NAME = SEMESTER_TYPE_NAME;
        }

        public String getGRP_SEMESTER_IDENTITY() {
            return GRP_SEMESTER_IDENTITY;
        }

        public void setGRP_SEMESTER_IDENTITY(String GRP_SEMESTER_IDENTITY) {
            this.GRP_SEMESTER_IDENTITY = GRP_SEMESTER_IDENTITY;
        }
    }
}
