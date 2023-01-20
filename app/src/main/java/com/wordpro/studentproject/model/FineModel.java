package com.wordpro.studentproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FineModel {

    @SerializedName("status")
    private int status;
    @SerializedName("BookFineDetails")
    private List<BookFineDetailsBean> BookFineDetails;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BookFineDetailsBean> getBookFineDetails() {
        return BookFineDetails;
    }

    public void setBookFineDetails(List<BookFineDetailsBean> BookFineDetails) {
        this.BookFineDetails = BookFineDetails;
    }

    public static class BookFineDetailsBean {
        /**
         * TRANSACTION_DATE : 8/2/2019 12:00:00 AM
         * Person_Type : EMPLOYEE
         * Person_Id : 1419180001
         * Member_Name : Dr. Bedse Anjali P
         * CHARGE_AMOUNT : 100
         * PARTICULARS : Late charges from Bedse Anjali Prashant for book return.
         * Access_Id : 87
         * BOOK_TITLE : Concise Medical Physiology
         * Book_Tentitive_Returning_Date : 7/27/2019 12:00:00 AM
         * Book_Returning_Date : 8/2/2019 12:00:00 AM
         * Receivable_Amount : 100
         * Actual_Received_Amt : 0
         * Balance_Amt : 100
         * BRANCH_STANDARD_CODE :
         * DEPARTMENT_NUMBER : 91
         * DEPARTMENT_NAME : Pharmaceutics
         * Deptt_Short_Code : Pharmaceutics
         * DESIGNATION_ID : 87
         * DEsig_description : ASSOCIATE PROFESSOR
         * Desig_SHORT_CODE : ASSO PROF
         * Charges_For : LATE
         */

        @SerializedName("TRANSACTION_DATE")
        private String TRANSACTIONDATE;
        @SerializedName("Person_Type")
        private String PersonType;
        @SerializedName("Person_Id")
        private String PersonId;
        @SerializedName("Member_Name")
        private String MemberName;
        @SerializedName("CHARGE_AMOUNT")
        private String CHARGEAMOUNT;
        @SerializedName("PARTICULARS")
        private String PARTICULARS;
        @SerializedName("Access_Id")
        private String AccessId;
        @SerializedName("BOOK_TITLE")
        private String BOOKTITLE;
        @SerializedName("Book_Tentitive_Returning_Date")
        private String BookTentitiveReturningDate;
        @SerializedName("Book_Returning_Date")
        private String BookReturningDate;
        @SerializedName("Receivable_Amount")
        private String ReceivableAmount;
        @SerializedName("Actual_Received_Amt")
        private String ActualReceivedAmt;
        @SerializedName("Balance_Amt")
        private String BalanceAmt;
        @SerializedName("BRANCH_STANDARD_CODE")
        private String BRANCHSTANDARDCODE;
        @SerializedName("DEPARTMENT_NUMBER")
        private String DEPARTMENTNUMBER;
        @SerializedName("DEPARTMENT_NAME")
        private String DEPARTMENTNAME;
        @SerializedName("Deptt_Short_Code")
        private String DepttShortCode;
        @SerializedName("DESIGNATION_ID")
        private String DESIGNATIONID;
        @SerializedName("DEsig_description")
        private String DEsigDescription;
        @SerializedName("Desig_SHORT_CODE")
        private String DesigSHORTCODE;
        @SerializedName("Charges_For")
        private String ChargesFor;

        public String getTRANSACTIONDATE() {
            return TRANSACTIONDATE;
        }

        public void setTRANSACTIONDATE(String TRANSACTIONDATE) {
            this.TRANSACTIONDATE = TRANSACTIONDATE;
        }

        public String getPersonType() {
            return PersonType;
        }

        public void setPersonType(String PersonType) {
            this.PersonType = PersonType;
        }

        public String getPersonId() {
            return PersonId;
        }

        public void setPersonId(String PersonId) {
            this.PersonId = PersonId;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String MemberName) {
            this.MemberName = MemberName;
        }

        public String getCHARGEAMOUNT() {
            return CHARGEAMOUNT;
        }

        public void setCHARGEAMOUNT(String CHARGEAMOUNT) {
            this.CHARGEAMOUNT = CHARGEAMOUNT;
        }

        public String getPARTICULARS() {
            return PARTICULARS;
        }

        public void setPARTICULARS(String PARTICULARS) {
            this.PARTICULARS = PARTICULARS;
        }

        public String getAccessId() {
            return AccessId;
        }

        public void setAccessId(String AccessId) {
            this.AccessId = AccessId;
        }

        public String getBOOKTITLE() {
            return BOOKTITLE;
        }

        public void setBOOKTITLE(String BOOKTITLE) {
            this.BOOKTITLE = BOOKTITLE;
        }

        public String getBookTentitiveReturningDate() {
            return BookTentitiveReturningDate;
        }

        public void setBookTentitiveReturningDate(String BookTentitiveReturningDate) {
            this.BookTentitiveReturningDate = BookTentitiveReturningDate;
        }

        public String getBookReturningDate() {
            return BookReturningDate;
        }

        public void setBookReturningDate(String BookReturningDate) {
            this.BookReturningDate = BookReturningDate;
        }

        public String getReceivableAmount() {
            return ReceivableAmount;
        }

        public void setReceivableAmount(String ReceivableAmount) {
            this.ReceivableAmount = ReceivableAmount;
        }

        public String getActualReceivedAmt() {
            return ActualReceivedAmt;
        }

        public void setActualReceivedAmt(String ActualReceivedAmt) {
            this.ActualReceivedAmt = ActualReceivedAmt;
        }

        public String getBalanceAmt() {
            return BalanceAmt;
        }

        public void setBalanceAmt(String BalanceAmt) {
            this.BalanceAmt = BalanceAmt;
        }

        public String getBRANCHSTANDARDCODE() {
            return BRANCHSTANDARDCODE;
        }

        public void setBRANCHSTANDARDCODE(String BRANCHSTANDARDCODE) {
            this.BRANCHSTANDARDCODE = BRANCHSTANDARDCODE;
        }

        public String getDEPARTMENTNUMBER() {
            return DEPARTMENTNUMBER;
        }

        public void setDEPARTMENTNUMBER(String DEPARTMENTNUMBER) {
            this.DEPARTMENTNUMBER = DEPARTMENTNUMBER;
        }

        public String getDEPARTMENTNAME() {
            return DEPARTMENTNAME;
        }

        public void setDEPARTMENTNAME(String DEPARTMENTNAME) {
            this.DEPARTMENTNAME = DEPARTMENTNAME;
        }

        public String getDepttShortCode() {
            return DepttShortCode;
        }

        public void setDepttShortCode(String DepttShortCode) {
            this.DepttShortCode = DepttShortCode;
        }

        public String getDESIGNATIONID() {
            return DESIGNATIONID;
        }

        public void setDESIGNATIONID(String DESIGNATIONID) {
            this.DESIGNATIONID = DESIGNATIONID;
        }

        public String getDEsigDescription() {
            return DEsigDescription;
        }

        public void setDEsigDescription(String DEsigDescription) {
            this.DEsigDescription = DEsigDescription;
        }

        public String getDesigSHORTCODE() {
            return DesigSHORTCODE;
        }

        public void setDesigSHORTCODE(String DesigSHORTCODE) {
            this.DesigSHORTCODE = DesigSHORTCODE;
        }

        public String getChargesFor() {
            return ChargesFor;
        }

        public void setChargesFor(String ChargesFor) {
            this.ChargesFor = ChargesFor;
        }
    }
}
