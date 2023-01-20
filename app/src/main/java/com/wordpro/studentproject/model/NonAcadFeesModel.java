package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 28-05-2018.
 */

public class NonAcadFeesModel {

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
         * STUD_FULL_NAME : Ms. Tejaswinee Sunil Ahirrao
         * FEE_TYPE_DESC : NON STRUCTURAL FEES
         * FEE_SUB_TYPE_DESC : OUTSTANDING FEES 2016-17(STUDENT)
         * ACC_BALSHEET_HEAD_DESC : ENGINEERING BALANCE SHEET
         * ACC_BALSHEET_MST_ID : 1
         * RECEIVABLE_AMOUNT : 4000
         * Fee_Sub_Type_Id : 401
         * FEE_RECEIPT_AMOUNT : 0
         * FEE_RECEI_AMOUNT_FROM : 5/1/2018 12:00:00 AM
         * FEE_RECEI_AMOUNT_UPTO : 4/30/2019 12:00:00 AM
         * SESSION_ID : 19
         * FEE_TYPE_ID : 2
         * BALANCE_AMOUNT : 4000
         * ADVN_AMT_SUBTYPEWISE : 0
         * DISCOUNT_AMOUNT : 0
         */

        private String STUD_FULL_NAME;
        private String FEE_TYPE_DESC;
        private String FEE_SUB_TYPE_DESC;
        private String ACC_BALSHEET_HEAD_DESC;
        private String ACC_BALSHEET_MST_ID;
        private String RECEIVABLE_AMOUNT;
        private String Fee_Sub_Type_Id;
        private String FEE_RECEIPT_AMOUNT;
        private String FEE_RECEI_AMOUNT_FROM;
        private String FEE_RECEI_AMOUNT_UPTO;
        private String SESSION_ID;
        private String FEE_TYPE_ID;
        private String BALANCE_AMOUNT;
        private String ADVN_AMT_SUBTYPEWISE;
        private String DISCOUNT_AMOUNT;

        public String getSTUD_FULL_NAME() {
            return STUD_FULL_NAME;
        }

        public void setSTUD_FULL_NAME(String STUD_FULL_NAME) {
            this.STUD_FULL_NAME = STUD_FULL_NAME;
        }

        public String getFEE_TYPE_DESC() {
            return FEE_TYPE_DESC;
        }

        public void setFEE_TYPE_DESC(String FEE_TYPE_DESC) {
            this.FEE_TYPE_DESC = FEE_TYPE_DESC;
        }

        public String getFEE_SUB_TYPE_DESC() {
            return FEE_SUB_TYPE_DESC;
        }

        public void setFEE_SUB_TYPE_DESC(String FEE_SUB_TYPE_DESC) {
            this.FEE_SUB_TYPE_DESC = FEE_SUB_TYPE_DESC;
        }

        public String getACC_BALSHEET_HEAD_DESC() {
            return ACC_BALSHEET_HEAD_DESC;
        }

        public void setACC_BALSHEET_HEAD_DESC(String ACC_BALSHEET_HEAD_DESC) {
            this.ACC_BALSHEET_HEAD_DESC = ACC_BALSHEET_HEAD_DESC;
        }

        public String getACC_BALSHEET_MST_ID() {
            return ACC_BALSHEET_MST_ID;
        }

        public void setACC_BALSHEET_MST_ID(String ACC_BALSHEET_MST_ID) {
            this.ACC_BALSHEET_MST_ID = ACC_BALSHEET_MST_ID;
        }

        public String getRECEIVABLE_AMOUNT() {
            return RECEIVABLE_AMOUNT;
        }

        public void setRECEIVABLE_AMOUNT(String RECEIVABLE_AMOUNT) {
            this.RECEIVABLE_AMOUNT = RECEIVABLE_AMOUNT;
        }

        public String getFee_Sub_Type_Id() {
            return Fee_Sub_Type_Id;
        }

        public void setFee_Sub_Type_Id(String Fee_Sub_Type_Id) {
            this.Fee_Sub_Type_Id = Fee_Sub_Type_Id;
        }

        public String getFEE_RECEIPT_AMOUNT() {
            return FEE_RECEIPT_AMOUNT;
        }

        public void setFEE_RECEIPT_AMOUNT(String FEE_RECEIPT_AMOUNT) {
            this.FEE_RECEIPT_AMOUNT = FEE_RECEIPT_AMOUNT;
        }

        public String getFEE_RECEI_AMOUNT_FROM() {
            return FEE_RECEI_AMOUNT_FROM;
        }

        public void setFEE_RECEI_AMOUNT_FROM(String FEE_RECEI_AMOUNT_FROM) {
            this.FEE_RECEI_AMOUNT_FROM = FEE_RECEI_AMOUNT_FROM;
        }

        public String getFEE_RECEI_AMOUNT_UPTO() {
            return FEE_RECEI_AMOUNT_UPTO;
        }

        public void setFEE_RECEI_AMOUNT_UPTO(String FEE_RECEI_AMOUNT_UPTO) {
            this.FEE_RECEI_AMOUNT_UPTO = FEE_RECEI_AMOUNT_UPTO;
        }

        public String getSESSION_ID() {
            return SESSION_ID;
        }

        public void setSESSION_ID(String SESSION_ID) {
            this.SESSION_ID = SESSION_ID;
        }

        public String getFEE_TYPE_ID() {
            return FEE_TYPE_ID;
        }

        public void setFEE_TYPE_ID(String FEE_TYPE_ID) {
            this.FEE_TYPE_ID = FEE_TYPE_ID;
        }

        public String getBALANCE_AMOUNT() {
            return BALANCE_AMOUNT;
        }

        public void setBALANCE_AMOUNT(String BALANCE_AMOUNT) {
            this.BALANCE_AMOUNT = BALANCE_AMOUNT;
        }

        public String getADVN_AMT_SUBTYPEWISE() {
            return ADVN_AMT_SUBTYPEWISE;
        }

        public void setADVN_AMT_SUBTYPEWISE(String ADVN_AMT_SUBTYPEWISE) {
            this.ADVN_AMT_SUBTYPEWISE = ADVN_AMT_SUBTYPEWISE;
        }

        public String getDISCOUNT_AMOUNT() {
            return DISCOUNT_AMOUNT;
        }

        public void setDISCOUNT_AMOUNT(String DISCOUNT_AMOUNT) {
            this.DISCOUNT_AMOUNT = DISCOUNT_AMOUNT;
        }
    }
}
