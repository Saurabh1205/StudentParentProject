package com.wordpro.studentproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RulesModel {

    @SerializedName("status")
    private int status;
    @SerializedName("CirculationRuleDtls")
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
         * CENTRE_CODE : ENGG_SC
         * CENTRE_NAME : K. K. Wagh Institute of Engineering Education & Research
         * LIB_LIBRARY_MST_ID : 1
         * LIBRARY_NAME : CENTRAL LIBRARY
         * LIBRARY_DESCRIPTION : CENTRAL LIBRARY
         * BOOK_RULE_MST_ID : 1
         * BOOK_RULE_DESC : LIB/RULE/CEN/2016/00001
         * BOOK_RULE_TYPE : NORMAL
         * MEMBER_TYPE : STUDENT
         * Consol_Tot_Books : 10
         * RULE_DET2_ID : 1
         * BOOK_TYPE_ID : 1
         * BOOK_TYPE_DESCRIPTION : REGULAR
         * BoKTyp_BokAllowed : 10
         * MAX_DAYS_ALLOWED : 5
         * SLAB_MASTER_ID : 1
         * SLAB_DESC : Test 1
         * Cons_Applicable_to : ,
         * ACTIVE_STATUS : Y
         * LOCKING_STATUS : N
         */

        private String CENTRE_CODE;
        private String CENTRE_NAME;
        private String LIB_LIBRARY_MST_ID;
        private String LIBRARY_NAME;
        private String LIBRARY_DESCRIPTION;
        private String BOOK_RULE_MST_ID;
        private String BOOK_RULE_DESC;
        private String BOOK_RULE_TYPE;
        private String MEMBER_TYPE;
        private String Consol_Tot_Books;
        private String RULE_DET2_ID;
        private String BOOK_TYPE_ID;
        private String BOOK_TYPE_DESCRIPTION;
        private String BoKTyp_BokAllowed;
        private String MAX_DAYS_ALLOWED;
        private String SLAB_MASTER_ID;
        private String SLAB_DESC;
        private String Cons_Applicable_to;
        private String ACTIVE_STATUS;
        private String LOCKING_STATUS;

        public String getCENTRE_CODE() {
            return CENTRE_CODE;
        }

        public void setCENTRE_CODE(String CENTRE_CODE) {
            this.CENTRE_CODE = CENTRE_CODE;
        }

        public String getCENTRE_NAME() {
            return CENTRE_NAME;
        }

        public void setCENTRE_NAME(String CENTRE_NAME) {
            this.CENTRE_NAME = CENTRE_NAME;
        }

        public String getLIB_LIBRARY_MST_ID() {
            return LIB_LIBRARY_MST_ID;
        }

        public void setLIB_LIBRARY_MST_ID(String LIB_LIBRARY_MST_ID) {
            this.LIB_LIBRARY_MST_ID = LIB_LIBRARY_MST_ID;
        }

        public String getLIBRARY_NAME() {
            return LIBRARY_NAME;
        }

        public void setLIBRARY_NAME(String LIBRARY_NAME) {
            this.LIBRARY_NAME = LIBRARY_NAME;
        }

        public String getLIBRARY_DESCRIPTION() {
            return LIBRARY_DESCRIPTION;
        }

        public void setLIBRARY_DESCRIPTION(String LIBRARY_DESCRIPTION) {
            this.LIBRARY_DESCRIPTION = LIBRARY_DESCRIPTION;
        }

        public String getBOOK_RULE_MST_ID() {
            return BOOK_RULE_MST_ID;
        }

        public void setBOOK_RULE_MST_ID(String BOOK_RULE_MST_ID) {
            this.BOOK_RULE_MST_ID = BOOK_RULE_MST_ID;
        }

        public String getBOOK_RULE_DESC() {
            return BOOK_RULE_DESC;
        }

        public void setBOOK_RULE_DESC(String BOOK_RULE_DESC) {
            this.BOOK_RULE_DESC = BOOK_RULE_DESC;
        }

        public String getBOOK_RULE_TYPE() {
            return BOOK_RULE_TYPE;
        }

        public void setBOOK_RULE_TYPE(String BOOK_RULE_TYPE) {
            this.BOOK_RULE_TYPE = BOOK_RULE_TYPE;
        }

        public String getMEMBER_TYPE() {
            return MEMBER_TYPE;
        }

        public void setMEMBER_TYPE(String MEMBER_TYPE) {
            this.MEMBER_TYPE = MEMBER_TYPE;
        }

        public String getConsol_Tot_Books() {
            return Consol_Tot_Books;
        }

        public void setConsol_Tot_Books(String Consol_Tot_Books) {
            this.Consol_Tot_Books = Consol_Tot_Books;
        }

        public String getRULE_DET2_ID() {
            return RULE_DET2_ID;
        }

        public void setRULE_DET2_ID(String RULE_DET2_ID) {
            this.RULE_DET2_ID = RULE_DET2_ID;
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

        public String getBoKTyp_BokAllowed() {
            return BoKTyp_BokAllowed;
        }

        public void setBoKTyp_BokAllowed(String BoKTyp_BokAllowed) {
            this.BoKTyp_BokAllowed = BoKTyp_BokAllowed;
        }

        public String getMAX_DAYS_ALLOWED() {
            return MAX_DAYS_ALLOWED;
        }

        public void setMAX_DAYS_ALLOWED(String MAX_DAYS_ALLOWED) {
            this.MAX_DAYS_ALLOWED = MAX_DAYS_ALLOWED;
        }

        public String getSLAB_MASTER_ID() {
            return SLAB_MASTER_ID;
        }

        public void setSLAB_MASTER_ID(String SLAB_MASTER_ID) {
            this.SLAB_MASTER_ID = SLAB_MASTER_ID;
        }

        public String getSLAB_DESC() {
            return SLAB_DESC;
        }

        public void setSLAB_DESC(String SLAB_DESC) {
            this.SLAB_DESC = SLAB_DESC;
        }

        public String getCons_Applicable_to() {
            return Cons_Applicable_to;
        }

        public void setCons_Applicable_to(String Cons_Applicable_to) {
            this.Cons_Applicable_to = Cons_Applicable_to;
        }

        public String getACTIVE_STATUS() {
            return ACTIVE_STATUS;
        }

        public void setACTIVE_STATUS(String ACTIVE_STATUS) {
            this.ACTIVE_STATUS = ACTIVE_STATUS;
        }

        public String getLOCKING_STATUS() {
            return LOCKING_STATUS;
        }

        public void setLOCKING_STATUS(String LOCKING_STATUS) {
            this.LOCKING_STATUS = LOCKING_STATUS;
        }
    }
}
