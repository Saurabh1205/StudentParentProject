package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class UnviSubjModel {

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
         * SUBJECT_RULE_GRP_NUMBER : 351
         * SESSION_ID : 18
         * RULE_DESCRIPTION : S.E.Electrical (2015 course)
         * ACAD_SESS_TYPE :
         * ACAD_SESS_NAME : 2017-2018
         * SUBJECT_DESCRIPTION : Network Analysis (2015 course)
         * SUBJECT_GROUP_ID : 3041
         * SUBJECT_ID : 2600
         * ORG_SEMESTER_MST_ID : 4
         * SUB_SHORT_DESCRIPTION : NA
         * ORG_SHORT_DESCRIPTION : NA
         * MAIN_SEMESTER_NAME : SEMESTER IV
         * MAIN_SEMESTER_TYPE : E
         * MAIN_SEMESTER_MST_ID : 4
         * BRANCH_STANDARD_GRP_ID : 37
         * COMPULSORY_OPTIONAL_FLAG : COMPULSORY
         * APPLICABLE_NUMBER : 1
         * PATTERN :
         * UNIVERSITY_CODE : 203 147
         * ACTIVE_FLAG : Y
         * TYPE_SHORT_NAME : TH
         * TYPE_DESCRIPTION : THEORY
         * VALUE_FIELD : 3041µ351
         */

        private String SUBJECT_RULE_GRP_NUMBER;
        private String SESSION_ID;
        private String RULE_DESCRIPTION;
        private String ACAD_SESS_TYPE;
        private String ACAD_SESS_NAME;
        private String SUBJECT_DESCRIPTION;
        private String SUBJECT_GROUP_ID;
        private String SUBJECT_ID;
        private String ORG_SEMESTER_MST_ID;
        private String SUB_SHORT_DESCRIPTION;
        private String ORG_SHORT_DESCRIPTION;
        private String MAIN_SEMESTER_NAME;
        private String MAIN_SEMESTER_TYPE;
        private String MAIN_SEMESTER_MST_ID;
        private String BRANCH_STANDARD_GRP_ID;
        private String COMPULSORY_OPTIONAL_FLAG;
        private String APPLICABLE_NUMBER;
        private String PATTERN;
        private String UNIVERSITY_CODE;
        private String ACTIVE_FLAG;
        private String TYPE_SHORT_NAME;
        private String TYPE_DESCRIPTION;
        private String VALUE_FIELD;

        private String SUB_SHORT_DESC;

        private String UNIV_SUB_CODE;
        private String APPLI_TYPE_SHORT_NAME;
        private String APPLI_TYPE_DESCRIPTION;
        private String BRANCH_STANDARD_CODE;
        private String BRANCH_STANDARD_DESCRIPTION;

        private String SUBJECT_DETAIL_ID;

        private String BRANCH_STANDARD_ID;
        private String SEMESTER_MST_ID;
        private String SEMESTER_TYPE;

        private String SEMESTER_FROM_DATE;
        private String SEMESTER_UPTO_DATE;
        private String PERIOD_START_DATE;
        private String PERIOD_END_DATE;
        private String STU_BATCH_DET_ID;
        private String SUB_BATCH_NAME;

        private String Employee_id;
        private String MR_OR_MRS;
        private String EMPLOYEE_FIRST_NAME;
        private String EMPLOYEE_MIDDLE_NAME;
        private String EMPLOYEE_LAST_NAME;
        private String EMP_EXCH_FAC;
        private String BATCH_SHORT_NAME;


        public String getSUB_SHORT_DESC() {
            return SUB_SHORT_DESC;
        }

        public void setSUB_SHORT_DESC(String SUB_SHORT_DESC) {
            this.SUB_SHORT_DESC = SUB_SHORT_DESC;
        }

        public String getUNIV_SUB_CODE() {
            return UNIV_SUB_CODE;
        }

        public void setUNIV_SUB_CODE(String UNIV_SUB_CODE) {
            this.UNIV_SUB_CODE = UNIV_SUB_CODE;
        }

        public String getAPPLI_TYPE_SHORT_NAME() {
            return APPLI_TYPE_SHORT_NAME;
        }

        public void setAPPLI_TYPE_SHORT_NAME(String APPLI_TYPE_SHORT_NAME) {
            this.APPLI_TYPE_SHORT_NAME = APPLI_TYPE_SHORT_NAME;
        }

        public String getAPPLI_TYPE_DESCRIPTION() {
            return APPLI_TYPE_DESCRIPTION;
        }

        public void setAPPLI_TYPE_DESCRIPTION(String APPLI_TYPE_DESCRIPTION) {
            this.APPLI_TYPE_DESCRIPTION = APPLI_TYPE_DESCRIPTION;
        }

        public String getBRANCH_STANDARD_CODE() {
            return BRANCH_STANDARD_CODE;
        }

        public void setBRANCH_STANDARD_CODE(String BRANCH_STANDARD_CODE) {
            this.BRANCH_STANDARD_CODE = BRANCH_STANDARD_CODE;
        }

        public String getBRANCH_STANDARD_DESCRIPTION() {
            return BRANCH_STANDARD_DESCRIPTION;
        }

        public void setBRANCH_STANDARD_DESCRIPTION(String BRANCH_STANDARD_DESCRIPTION) {
            this.BRANCH_STANDARD_DESCRIPTION = BRANCH_STANDARD_DESCRIPTION;
        }

        public String getSUBJECT_DETAIL_ID() {
            return SUBJECT_DETAIL_ID;
        }

        public void setSUBJECT_DETAIL_ID(String SUBJECT_DETAIL_ID) {
            this.SUBJECT_DETAIL_ID = SUBJECT_DETAIL_ID;
        }

        public String getBRANCH_STANDARD_ID() {
            return BRANCH_STANDARD_ID;
        }

        public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
            this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
        }

        public String getSEMESTER_MST_ID() {
            return SEMESTER_MST_ID;
        }

        public void setSEMESTER_MST_ID(String SEMESTER_MST_ID) {
            this.SEMESTER_MST_ID = SEMESTER_MST_ID;
        }

        public String getSEMESTER_TYPE() {
            return SEMESTER_TYPE;
        }

        public void setSEMESTER_TYPE(String SEMESTER_TYPE) {
            this.SEMESTER_TYPE = SEMESTER_TYPE;
        }

        public String getSEMESTER_FROM_DATE() {
            return SEMESTER_FROM_DATE;
        }

        public void setSEMESTER_FROM_DATE(String SEMESTER_FROM_DATE) {
            this.SEMESTER_FROM_DATE = SEMESTER_FROM_DATE;
        }

        public String getSEMESTER_UPTO_DATE() {
            return SEMESTER_UPTO_DATE;
        }

        public void setSEMESTER_UPTO_DATE(String SEMESTER_UPTO_DATE) {
            this.SEMESTER_UPTO_DATE = SEMESTER_UPTO_DATE;
        }

        public String getPERIOD_START_DATE() {
            return PERIOD_START_DATE;
        }

        public void setPERIOD_START_DATE(String PERIOD_START_DATE) {
            this.PERIOD_START_DATE = PERIOD_START_DATE;
        }

        public String getPERIOD_END_DATE() {
            return PERIOD_END_DATE;
        }

        public void setPERIOD_END_DATE(String PERIOD_END_DATE) {
            this.PERIOD_END_DATE = PERIOD_END_DATE;
        }

        public String getSTU_BATCH_DET_ID() {
            return STU_BATCH_DET_ID;
        }

        public void setSTU_BATCH_DET_ID(String STU_BATCH_DET_ID) {
            this.STU_BATCH_DET_ID = STU_BATCH_DET_ID;
        }

        public String getSUB_BATCH_NAME() {
            return SUB_BATCH_NAME;
        }

        public void setSUB_BATCH_NAME(String SUB_BATCH_NAME) {
            this.SUB_BATCH_NAME = SUB_BATCH_NAME;
        }

        public String getEmployee_id() {
            return Employee_id;
        }

        public void setEmployee_id(String employee_id) {
            Employee_id = employee_id;
        }

        public String getMR_OR_MRS() {
            return MR_OR_MRS;
        }

        public void setMR_OR_MRS(String MR_OR_MRS) {
            this.MR_OR_MRS = MR_OR_MRS;
        }

        public String getEMPLOYEE_FIRST_NAME() {
            return EMPLOYEE_FIRST_NAME;
        }

        public void setEMPLOYEE_FIRST_NAME(String EMPLOYEE_FIRST_NAME) {
            this.EMPLOYEE_FIRST_NAME = EMPLOYEE_FIRST_NAME;
        }

        public String getEMPLOYEE_MIDDLE_NAME() {
            return EMPLOYEE_MIDDLE_NAME;
        }

        public void setEMPLOYEE_MIDDLE_NAME(String EMPLOYEE_MIDDLE_NAME) {
            this.EMPLOYEE_MIDDLE_NAME = EMPLOYEE_MIDDLE_NAME;
        }

        public String getEMPLOYEE_LAST_NAME() {
            return EMPLOYEE_LAST_NAME;
        }

        public void setEMPLOYEE_LAST_NAME(String EMPLOYEE_LAST_NAME) {
            this.EMPLOYEE_LAST_NAME = EMPLOYEE_LAST_NAME;
        }

        public String getSUBJECT_RULE_GRP_NUMBER() {
            return SUBJECT_RULE_GRP_NUMBER;
        }

        public void setSUBJECT_RULE_GRP_NUMBER(String SUBJECT_RULE_GRP_NUMBER) {
            this.SUBJECT_RULE_GRP_NUMBER = SUBJECT_RULE_GRP_NUMBER;
        }

        public String getSESSION_ID() {
            return SESSION_ID;
        }

        public void setSESSION_ID(String SESSION_ID) {
            this.SESSION_ID = SESSION_ID;
        }

        public String getRULE_DESCRIPTION() {
            return RULE_DESCRIPTION;
        }

        public void setRULE_DESCRIPTION(String RULE_DESCRIPTION) {
            this.RULE_DESCRIPTION = RULE_DESCRIPTION;
        }

        public String getACAD_SESS_TYPE() {
            return ACAD_SESS_TYPE;
        }

        public void setACAD_SESS_TYPE(String ACAD_SESS_TYPE) {
            this.ACAD_SESS_TYPE = ACAD_SESS_TYPE;
        }

        public String getACAD_SESS_NAME() {
            return ACAD_SESS_NAME;
        }

        public void setACAD_SESS_NAME(String ACAD_SESS_NAME) {
            this.ACAD_SESS_NAME = ACAD_SESS_NAME;
        }

        public String getSUBJECT_DESCRIPTION() {
            return SUBJECT_DESCRIPTION;
        }

        public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
            this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
        }

        public String getSUBJECT_GROUP_ID() {
            return SUBJECT_GROUP_ID;
        }

        public void setSUBJECT_GROUP_ID(String SUBJECT_GROUP_ID) {
            this.SUBJECT_GROUP_ID = SUBJECT_GROUP_ID;
        }

        public String getSUBJECT_ID() {
            return SUBJECT_ID;
        }

        public void setSUBJECT_ID(String SUBJECT_ID) {
            this.SUBJECT_ID = SUBJECT_ID;
        }

        public String getORG_SEMESTER_MST_ID() {
            return ORG_SEMESTER_MST_ID;
        }

        public void setORG_SEMESTER_MST_ID(String ORG_SEMESTER_MST_ID) {
            this.ORG_SEMESTER_MST_ID = ORG_SEMESTER_MST_ID;
        }

        public String getSUB_SHORT_DESCRIPTION() {
            return SUB_SHORT_DESCRIPTION;
        }

        public void setSUB_SHORT_DESCRIPTION(String SUB_SHORT_DESCRIPTION) {
            this.SUB_SHORT_DESCRIPTION = SUB_SHORT_DESCRIPTION;
        }

        public String getORG_SHORT_DESCRIPTION() {
            return ORG_SHORT_DESCRIPTION;
        }

        public void setORG_SHORT_DESCRIPTION(String ORG_SHORT_DESCRIPTION) {
            this.ORG_SHORT_DESCRIPTION = ORG_SHORT_DESCRIPTION;
        }

        public String getMAIN_SEMESTER_NAME() {
            return MAIN_SEMESTER_NAME;
        }

        public void setMAIN_SEMESTER_NAME(String MAIN_SEMESTER_NAME) {
            this.MAIN_SEMESTER_NAME = MAIN_SEMESTER_NAME;
        }

        public String getMAIN_SEMESTER_TYPE() {
            return MAIN_SEMESTER_TYPE;
        }

        public void setMAIN_SEMESTER_TYPE(String MAIN_SEMESTER_TYPE) {
            this.MAIN_SEMESTER_TYPE = MAIN_SEMESTER_TYPE;
        }

        public String getMAIN_SEMESTER_MST_ID() {
            return MAIN_SEMESTER_MST_ID;
        }

        public void setMAIN_SEMESTER_MST_ID(String MAIN_SEMESTER_MST_ID) {
            this.MAIN_SEMESTER_MST_ID = MAIN_SEMESTER_MST_ID;
        }

        public String getBRANCH_STANDARD_GRP_ID() {
            return BRANCH_STANDARD_GRP_ID;
        }

        public void setBRANCH_STANDARD_GRP_ID(String BRANCH_STANDARD_GRP_ID) {
            this.BRANCH_STANDARD_GRP_ID = BRANCH_STANDARD_GRP_ID;
        }

        public String getCOMPULSORY_OPTIONAL_FLAG() {
            return COMPULSORY_OPTIONAL_FLAG;
        }

        public String getEMP_EXCH_FAC() {
            return EMP_EXCH_FAC;
        }

        public void setEMP_EXCH_FAC(String EMP_EXCH_FAC) {
            this.EMP_EXCH_FAC = EMP_EXCH_FAC;
        }

        public String getBATCH_SHORT_NAME() {
            return BATCH_SHORT_NAME;
        }

        public void setBATCH_SHORT_NAME(String BATCH_SHORT_NAME) {
            this.BATCH_SHORT_NAME = BATCH_SHORT_NAME;
        }

        public void setCOMPULSORY_OPTIONAL_FLAG(String COMPULSORY_OPTIONAL_FLAG) {
            this.COMPULSORY_OPTIONAL_FLAG = COMPULSORY_OPTIONAL_FLAG;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getPATTERN() {
            return PATTERN;
        }

        public void setPATTERN(String PATTERN) {
            this.PATTERN = PATTERN;
        }

        public String getUNIVERSITY_CODE() {
            return UNIVERSITY_CODE;
        }

        public void setUNIVERSITY_CODE(String UNIVERSITY_CODE) {
            this.UNIVERSITY_CODE = UNIVERSITY_CODE;
        }

        public String getACTIVE_FLAG() {
            return ACTIVE_FLAG;
        }

        public void setACTIVE_FLAG(String ACTIVE_FLAG) {
            this.ACTIVE_FLAG = ACTIVE_FLAG;
        }

        public String getTYPE_SHORT_NAME() {
            return TYPE_SHORT_NAME;
        }

        public void setTYPE_SHORT_NAME(String TYPE_SHORT_NAME) {
            this.TYPE_SHORT_NAME = TYPE_SHORT_NAME;
        }

        public String getTYPE_DESCRIPTION() {
            return TYPE_DESCRIPTION;
        }

        public void setTYPE_DESCRIPTION(String TYPE_DESCRIPTION) {
            this.TYPE_DESCRIPTION = TYPE_DESCRIPTION;
        }

        public String getVALUE_FIELD() {
            return VALUE_FIELD;
        }

        public void setVALUE_FIELD(String VALUE_FIELD) {
            this.VALUE_FIELD = VALUE_FIELD;
        }
    }
}
