package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 21-04-2018.
 */

public class TeachPlanSubjModel {
    
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
             * Sub_Dept_number : 8
             * Employee_Id : 1039140331
             * CODE_SHORT_DESC : ELEC
             * Emp_DEP_NUMBER : 8
             * Session_id : 18
             * SEMESTER_TYPE : E
             * SUBJECT_DETAIL_ID : 8201
             * TOTAL_TOPICS_IN_SYLLABUS : 33
             * TOT_SCHEDULED_TOPICS : 33
             * TOT_COVERED_TOPICS : 1
             * Proposed_No_Of_Lectures :
             * ACTUAL_COVERED_PERCENTAGE : 3.03 %
             * SYLLABUS_COVERED_PERCENTAGE : 3.03 %
             * No_of_Periods :
             * Status :
             * BRANCH_STANDARD_CODE : SE-ELEC-1ST SHIFT
             * UNIVERSITY_CODE : 203 148
             * SUBJECT_SHORT_CODE : NMCP
             * SUBJECT_DESCRIPTION : Numerical Methods and Computer Programming (2015 course)
             * PROF_EMP_FN_MN_SHORT : Prof Mrs. N.N.Jangle
             * ACAD_SESS_NAME : 2017-2018
             * DATE_OF_JOINING : 9/12/2007 12:00:00 AM
             * SYLLABUS_ALLOCATION : Complete
             * TENTATIVE_ACTIVE_FLAG : Y
             * STANDARD_ID : 18
             * GRADE_SEQUENCE_NO : 1
             * EMP_DATE_OF_JOINING : 9/12/2007 12:00:00 AM
             * DESIGNATION_LEVEL : 5
             * CENTRE_CODE : ENGG_SC
             */

            private String Sub_Dept_number;
            private String Employee_Id;
            private String CODE_SHORT_DESC;
            private String Emp_DEP_NUMBER;
            private String Session_id;
            private String SEMESTER_TYPE;
            private String SUBJECT_DETAIL_ID;
            private String TOTAL_TOPICS_IN_SYLLABUS;
            private String TOT_SCHEDULED_TOPICS;
            private String TOT_COVERED_TOPICS;
            private String Proposed_No_Of_Lectures;
            private String ACTUAL_COVERED_PERCENTAGE;
            private String SYLLABUS_COVERED_PERCENTAGE;
            private String No_of_Periods;
            private String Status;
            private String BRANCH_STANDARD_CODE;
            private String UNIVERSITY_CODE;
            private String SUBJECT_SHORT_CODE;
            private String SUBJECT_DESCRIPTION;
            private String PROF_EMP_FN_MN_SHORT;
            private String ACAD_SESS_NAME;
            private String DATE_OF_JOINING;
            private String SYLLABUS_ALLOCATION;
            private String TENTATIVE_ACTIVE_FLAG;
            private String STANDARD_ID;
            private String GRADE_SEQUENCE_NO;
            private String EMP_DATE_OF_JOINING;
            private String DESIGNATION_LEVEL;
            private String CENTRE_CODE;
            private String TYPE_SHORT_NAME;

        public String getTYPE_SHORT_NAME() {
            return TYPE_SHORT_NAME;
        }

        public void setTYPE_SHORT_NAME(String TYPE_SHORT_NAME) {
            this.TYPE_SHORT_NAME = TYPE_SHORT_NAME;
        }

        public String getSub_Dept_number() {
                return Sub_Dept_number;
            }

            public void setSub_Dept_number(String Sub_Dept_number) {
                this.Sub_Dept_number = Sub_Dept_number;
            }

            public String getEmployee_Id() {
                return Employee_Id;
            }

            public void setEmployee_Id(String Employee_Id) {
                this.Employee_Id = Employee_Id;
            }

            public String getCODE_SHORT_DESC() {
                return CODE_SHORT_DESC;
            }

            public void setCODE_SHORT_DESC(String CODE_SHORT_DESC) {
                this.CODE_SHORT_DESC = CODE_SHORT_DESC;
            }

            public String getEmp_DEP_NUMBER() {
                return Emp_DEP_NUMBER;
            }

            public void setEmp_DEP_NUMBER(String Emp_DEP_NUMBER) {
                this.Emp_DEP_NUMBER = Emp_DEP_NUMBER;
            }

            public String getSession_id() {
                return Session_id;
            }

            public void setSession_id(String Session_id) {
                this.Session_id = Session_id;
            }

            public String getSEMESTER_TYPE() {
                return SEMESTER_TYPE;
            }

            public void setSEMESTER_TYPE(String SEMESTER_TYPE) {
                this.SEMESTER_TYPE = SEMESTER_TYPE;
            }

            public String getSUBJECT_DETAIL_ID() {
                return SUBJECT_DETAIL_ID;
            }

            public void setSUBJECT_DETAIL_ID(String SUBJECT_DETAIL_ID) {
                this.SUBJECT_DETAIL_ID = SUBJECT_DETAIL_ID;
            }

            public String getTOTAL_TOPICS_IN_SYLLABUS() {
                return TOTAL_TOPICS_IN_SYLLABUS;
            }

            public void setTOTAL_TOPICS_IN_SYLLABUS(String TOTAL_TOPICS_IN_SYLLABUS) {
                this.TOTAL_TOPICS_IN_SYLLABUS = TOTAL_TOPICS_IN_SYLLABUS;
            }

            public String getTOT_SCHEDULED_TOPICS() {
                return TOT_SCHEDULED_TOPICS;
            }

            public void setTOT_SCHEDULED_TOPICS(String TOT_SCHEDULED_TOPICS) {
                this.TOT_SCHEDULED_TOPICS = TOT_SCHEDULED_TOPICS;
            }

            public String getTOT_COVERED_TOPICS() {
                return TOT_COVERED_TOPICS;
            }

            public void setTOT_COVERED_TOPICS(String TOT_COVERED_TOPICS) {
                this.TOT_COVERED_TOPICS = TOT_COVERED_TOPICS;
            }

            public String getProposed_No_Of_Lectures() {
                return Proposed_No_Of_Lectures;
            }

            public void setProposed_No_Of_Lectures(String Proposed_No_Of_Lectures) {
                this.Proposed_No_Of_Lectures = Proposed_No_Of_Lectures;
            }

            public String getACTUAL_COVERED_PERCENTAGE() {
                return ACTUAL_COVERED_PERCENTAGE;
            }

            public void setACTUAL_COVERED_PERCENTAGE(String ACTUAL_COVERED_PERCENTAGE) {
                this.ACTUAL_COVERED_PERCENTAGE = ACTUAL_COVERED_PERCENTAGE;
            }

            public String getSYLLABUS_COVERED_PERCENTAGE() {
                return SYLLABUS_COVERED_PERCENTAGE;
            }

            public void setSYLLABUS_COVERED_PERCENTAGE(String SYLLABUS_COVERED_PERCENTAGE) {
                this.SYLLABUS_COVERED_PERCENTAGE = SYLLABUS_COVERED_PERCENTAGE;
            }

            public String getNo_of_Periods() {
                return No_of_Periods;
            }

            public void setNo_of_Periods(String No_of_Periods) {
                this.No_of_Periods = No_of_Periods;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getBRANCH_STANDARD_CODE() {
                return BRANCH_STANDARD_CODE;
            }

            public void setBRANCH_STANDARD_CODE(String BRANCH_STANDARD_CODE) {
                this.BRANCH_STANDARD_CODE = BRANCH_STANDARD_CODE;
            }

            public String getUNIVERSITY_CODE() {
                return UNIVERSITY_CODE;
            }

            public void setUNIVERSITY_CODE(String UNIVERSITY_CODE) {
                this.UNIVERSITY_CODE = UNIVERSITY_CODE;
            }

            public String getSUBJECT_SHORT_CODE() {
                return SUBJECT_SHORT_CODE;
            }

            public void setSUBJECT_SHORT_CODE(String SUBJECT_SHORT_CODE) {
                this.SUBJECT_SHORT_CODE = SUBJECT_SHORT_CODE;
            }

            public String getSUBJECT_DESCRIPTION() {
                return SUBJECT_DESCRIPTION;
            }

            public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
                this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
            }

            public String getPROF_EMP_FN_MN_SHORT() {
                return PROF_EMP_FN_MN_SHORT;
            }

            public void setPROF_EMP_FN_MN_SHORT(String PROF_EMP_FN_MN_SHORT) {
                this.PROF_EMP_FN_MN_SHORT = PROF_EMP_FN_MN_SHORT;
            }

            public String getACAD_SESS_NAME() {
                return ACAD_SESS_NAME;
            }

            public void setACAD_SESS_NAME(String ACAD_SESS_NAME) {
                this.ACAD_SESS_NAME = ACAD_SESS_NAME;
            }

            public String getDATE_OF_JOINING() {
                return DATE_OF_JOINING;
            }

            public void setDATE_OF_JOINING(String DATE_OF_JOINING) {
                this.DATE_OF_JOINING = DATE_OF_JOINING;
            }

            public String getSYLLABUS_ALLOCATION() {
                return SYLLABUS_ALLOCATION;
            }

            public void setSYLLABUS_ALLOCATION(String SYLLABUS_ALLOCATION) {
                this.SYLLABUS_ALLOCATION = SYLLABUS_ALLOCATION;
            }

            public String getTENTATIVE_ACTIVE_FLAG() {
                return TENTATIVE_ACTIVE_FLAG;
            }

            public void setTENTATIVE_ACTIVE_FLAG(String TENTATIVE_ACTIVE_FLAG) {
                this.TENTATIVE_ACTIVE_FLAG = TENTATIVE_ACTIVE_FLAG;
            }

            public String getSTANDARD_ID() {
                return STANDARD_ID;
            }

            public void setSTANDARD_ID(String STANDARD_ID) {
                this.STANDARD_ID = STANDARD_ID;
            }

            public String getGRADE_SEQUENCE_NO() {
                return GRADE_SEQUENCE_NO;
            }

            public void setGRADE_SEQUENCE_NO(String GRADE_SEQUENCE_NO) {
                this.GRADE_SEQUENCE_NO = GRADE_SEQUENCE_NO;
            }

            public String getEMP_DATE_OF_JOINING() {
                return EMP_DATE_OF_JOINING;
            }

            public void setEMP_DATE_OF_JOINING(String EMP_DATE_OF_JOINING) {
                this.EMP_DATE_OF_JOINING = EMP_DATE_OF_JOINING;
            }

            public String getDESIGNATION_LEVEL() {
                return DESIGNATION_LEVEL;
            }

            public void setDESIGNATION_LEVEL(String DESIGNATION_LEVEL) {
                this.DESIGNATION_LEVEL = DESIGNATION_LEVEL;
            }

            public String getCENTRE_CODE() {
                return CENTRE_CODE;
            }

            public void setCENTRE_CODE(String CENTRE_CODE) {
                this.CENTRE_CODE = CENTRE_CODE;
            }
    }
}
