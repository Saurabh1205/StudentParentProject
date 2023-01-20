package com.wordpro.studentproject.model;

import java.util.List;

public class BookReservtnModel {

    private int status;
    private List<LibBokReserStatusBean> LibBokReserStatus;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LibBokReserStatusBean> getLibBokReserStatus() {
        return LibBokReserStatus;
    }

    public void setLibBokReserStatus(List<LibBokReserStatusBean> LibBokReserStatus) {
        this.LibBokReserStatus = LibBokReserStatus;
    }

    public static class LibBokReserStatusBean {
        /**
         * LIB_RESERVATION_ID : 3
         * RESERVATION_CODE : LIB/RES/CEN/022018/0000003
         * BOOK_TITLE_ID : 83
         * BOOK_TITLE : A Hand Book Of Practical Physical Pharmacy & Practical Physical Pharmaceutical
         * comb_author_l_name_wise : Hadkar U B
         * RESERVATION_DATE : 2/19/2018 12:00:00 AM
         * RESERVATION_STATUS : PENDING
         * Access_Id :
         * ACCESSION_NUMBER :
         */

        private String LIB_RESERVATION_ID;
        private String RESERVATION_CODE;
        private String BOOK_TITLE_ID;
        private String BOOK_TITLE;
        private String comb_author_l_name_wise;
        private String RESERVATION_DATE;
        private String RESERVATION_STATUS;
        private String Access_Id;
        private String ACCESSION_NUMBER;

        public String getLIB_RESERVATION_ID() {
            return LIB_RESERVATION_ID;
        }

        public void setLIB_RESERVATION_ID(String LIB_RESERVATION_ID) {
            this.LIB_RESERVATION_ID = LIB_RESERVATION_ID;
        }

        public String getRESERVATION_CODE() {
            return RESERVATION_CODE;
        }

        public void setRESERVATION_CODE(String RESERVATION_CODE) {
            this.RESERVATION_CODE = RESERVATION_CODE;
        }

        public String getBOOK_TITLE_ID() {
            return BOOK_TITLE_ID;
        }

        public void setBOOK_TITLE_ID(String BOOK_TITLE_ID) {
            this.BOOK_TITLE_ID = BOOK_TITLE_ID;
        }

        public String getBOOK_TITLE() {
            return BOOK_TITLE;
        }

        public void setBOOK_TITLE(String BOOK_TITLE) {
            this.BOOK_TITLE = BOOK_TITLE;
        }

        public String getComb_author_l_name_wise() {
            return comb_author_l_name_wise;
        }

        public void setComb_author_l_name_wise(String comb_author_l_name_wise) {
            this.comb_author_l_name_wise = comb_author_l_name_wise;
        }

        public String getRESERVATION_DATE() {
            return RESERVATION_DATE;
        }

        public void setRESERVATION_DATE(String RESERVATION_DATE) {
            this.RESERVATION_DATE = RESERVATION_DATE;
        }

        public String getRESERVATION_STATUS() {
            return RESERVATION_STATUS;
        }

        public void setRESERVATION_STATUS(String RESERVATION_STATUS) {
            this.RESERVATION_STATUS = RESERVATION_STATUS;
        }

        public String getAccess_Id() {
            return Access_Id;
        }

        public void setAccess_Id(String Access_Id) {
            this.Access_Id = Access_Id;
        }

        public String getACCESSION_NUMBER() {
            return ACCESSION_NUMBER;
        }

        public void setACCESSION_NUMBER(String ACCESSION_NUMBER) {
            this.ACCESSION_NUMBER = ACCESSION_NUMBER;
        }
    }

}
