package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 26-06-2018.
 */

public class SubjectModel {


    /**
     * status : 1
     * data : [{"SUB_SHORT_DESC":"CL-II","SUBJECT_DESCRIPTION":"Computer Laboratory-II","UNIV_SUB_CODE":"410447","APPLI_TYPE_SHORT_NAME":"PR","APPLI_TYPE_DESCRIPTION":"PRACTICAL","SUBJECT_DETAIL_ID":"62","APPLICABLE_NUMBER":"2","SUBJECT_GROUP_ID":"36","COMPULSORY_OPTIONAL_FLAG":"COMPULSORY","TEXT_FIELD":"Computer Laboratory-II(410447)(PR)","VALUE_FIELD":"2µ36"},{"SUB_SHORT_DESC":"DAA","SUBJECT_DESCRIPTION":"Design & Analysis of Algorithms","UNIV_SUB_CODE":"410441","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"52","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"31","COMPULSORY_OPTIONAL_FLAG":"COMPULSORY","TEXT_FIELD":"Design & Analysis of Algorithms(410441)(TH)","VALUE_FIELD":"1µ31"},{"SUB_SHORT_DESC":"PMCD","SUBJECT_DESCRIPTION":"Principles of Modern Compiler Design","UNIV_SUB_CODE":"410442","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"54","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"32","COMPULSORY_OPTIONAL_FLAG":"COMPULSORY","TEXT_FIELD":"Principles of Modern Compiler Design(410442)(TH)","VALUE_FIELD":"1µ32"},{"SUB_SHORT_DESC":"DMTA-ELE-I","SUBJECT_DESCRIPTION":"Data Mining Techniques and Applications","UNIV_SUB_CODE":"410444","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"1983","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"830","COMPULSORY_OPTIONAL_FLAG":"OPTIONAL","TEXT_FIELD":"Data Mining Techniques and Applications(410444)(TH)","VALUE_FIELD":"1µ830"},{"SUB_SHORT_DESC":"CL-I","SUBJECT_DESCRIPTION":"Computer Laboratory-I","UNIV_SUB_CODE":"410446","APPLI_TYPE_SHORT_NAME":"PR","APPLI_TYPE_DESCRIPTION":"PRACTICAL","SUBJECT_DETAIL_ID":"58","APPLICABLE_NUMBER":"2","SUBJECT_GROUP_ID":"34","COMPULSORY_OPTIONAL_FLAG":"COMPULSORY","TEXT_FIELD":"Computer Laboratory-I(410446)(PR)","VALUE_FIELD":"2µ34"},{"SUB_SHORT_DESC":"SSDA","SUBJECT_DESCRIPTION":"Smart System Design and Applications","UNIV_SUB_CODE":"410443","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"56","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"33","COMPULSORY_OPTIONAL_FLAG":"COMPULSORY","TEXT_FIELD":"Smart System Design and Applications(410443)(TH)","VALUE_FIELD":"1µ33"},{"SUB_SHORT_DESC":"PC-ELE-II","SUBJECT_DESCRIPTION":"Pervasive Computing","UNIV_SUB_CODE":"410445","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"2567","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"1151","COMPULSORY_OPTIONAL_FLAG":"OPTIONAL","TEXT_FIELD":"Pervasive Computing(410445)(TH)","VALUE_FIELD":"1µ1151"},{"SUB_SHORT_DESC":"MNLP-ELE-II","SUBJECT_DESCRIPTION":"Multidisciplinary NLP","UNIV_SUB_CODE":"410445","APPLI_TYPE_SHORT_NAME":"TH","APPLI_TYPE_DESCRIPTION":"THEORY","SUBJECT_DETAIL_ID":"2587","APPLICABLE_NUMBER":"1","SUBJECT_GROUP_ID":"1155","COMPULSORY_OPTIONAL_FLAG":"OPTIONAL","TEXT_FIELD":"Multidisciplinary NLP(410445)(TH)","VALUE_FIELD":"1µ1155"}]
     * fromdate : 15/06/2017
     * todate : 14/10/2017
     */

    private int status;
    private String fromdate;
    private String todate;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * SUB_SHORT_DESC : CL-II
         * SUBJECT_DESCRIPTION : Computer Laboratory-II
         * UNIV_SUB_CODE : 410447
         * APPLI_TYPE_SHORT_NAME : PR
         * APPLI_TYPE_DESCRIPTION : PRACTICAL
         * SUBJECT_DETAIL_ID : 62
         * APPLICABLE_NUMBER : 2
         * SUBJECT_GROUP_ID : 36
         * COMPULSORY_OPTIONAL_FLAG : COMPULSORY
         * TEXT_FIELD : Computer Laboratory-II(410447)(PR)
         * VALUE_FIELD : 2µ36
         */

        private String SUB_SHORT_DESC;
        private String SUBJECT_DESCRIPTION;
        private String UNIV_SUB_CODE;
        private String APPLI_TYPE_SHORT_NAME;
        private String APPLI_TYPE_DESCRIPTION;
        private String SUBJECT_DETAIL_ID;
        private String APPLICABLE_NUMBER;
        private String SUBJECT_GROUP_ID;
        private String COMPULSORY_OPTIONAL_FLAG;
        private String TEXT_FIELD;
        private String VALUE_FIELD;

        public String getSUB_SHORT_DESC() {
            return SUB_SHORT_DESC;
        }

        public void setSUB_SHORT_DESC(String SUB_SHORT_DESC) {
            this.SUB_SHORT_DESC = SUB_SHORT_DESC;
        }

        public String getSUBJECT_DESCRIPTION() {
            return SUBJECT_DESCRIPTION;
        }

        public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
            this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
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

        public String getSUBJECT_DETAIL_ID() {
            return SUBJECT_DETAIL_ID;
        }

        public void setSUBJECT_DETAIL_ID(String SUBJECT_DETAIL_ID) {
            this.SUBJECT_DETAIL_ID = SUBJECT_DETAIL_ID;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getSUBJECT_GROUP_ID() {
            return SUBJECT_GROUP_ID;
        }

        public void setSUBJECT_GROUP_ID(String SUBJECT_GROUP_ID) {
            this.SUBJECT_GROUP_ID = SUBJECT_GROUP_ID;
        }

        public String getCOMPULSORY_OPTIONAL_FLAG() {
            return COMPULSORY_OPTIONAL_FLAG;
        }

        public void setCOMPULSORY_OPTIONAL_FLAG(String COMPULSORY_OPTIONAL_FLAG) {
            this.COMPULSORY_OPTIONAL_FLAG = COMPULSORY_OPTIONAL_FLAG;
        }

        public String getTEXT_FIELD() {
            return TEXT_FIELD;
        }

        public void setTEXT_FIELD(String TEXT_FIELD) {
            this.TEXT_FIELD = TEXT_FIELD;
        }

        public String getVALUE_FIELD() {
            return VALUE_FIELD;
        }

        public void setVALUE_FIELD(String VALUE_FIELD) {
            this.VALUE_FIELD = VALUE_FIELD;
        }
    }
}
