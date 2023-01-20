package com.wordpro.studentproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBkModel {

    @SerializedName("status")
    private int status;
    @SerializedName("BookSearchDetails")
    private List<BookSearchDetailsBean> BookSearchDetails;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BookSearchDetailsBean> getBookSearchDetails() {
        return BookSearchDetails;
    }

    public void setBookSearchDetails(List<BookSearchDetailsBean> BookSearchDetails) {
        this.BookSearchDetails = BookSearchDetails;
    }

    public static class BookSearchDetailsBean {
        /**
         * BOOK_TITLE_ID : 539
         * PUBLICATION_ID : 63
         * BOOK_TITLE : Using Samba
         * PUBLICATION_NAME : Oreilly
         * AUTHOR_NAME : Ghoshal A
         * TOPIC_NAME :
         */

        @SerializedName("BOOK_TITLE_ID")
        private String BOOKTITLEID;
        @SerializedName("PUBLICATION_ID")
        private String PUBLICATIONID;
        @SerializedName("BOOK_TITLE")
        private String BOOKTITLE;
        @SerializedName("PUBLICATION_NAME")
        private String PUBLICATIONNAME;
        @SerializedName("AUTHOR_NAME")
        private String AUTHORNAME;
        @SerializedName("TOPIC_NAME")
        private String TOPICNAME;

        public String getBOOKTITLEID() {
            return BOOKTITLEID;
        }

        public void setBOOKTITLEID(String BOOKTITLEID) {
            this.BOOKTITLEID = BOOKTITLEID;
        }

        public String getPUBLICATIONID() {
            return PUBLICATIONID;
        }

        public void setPUBLICATIONID(String PUBLICATIONID) {
            this.PUBLICATIONID = PUBLICATIONID;
        }

        public String getBOOKTITLE() {
            return BOOKTITLE;
        }

        public void setBOOKTITLE(String BOOKTITLE) {
            this.BOOKTITLE = BOOKTITLE;
        }

        public String getPUBLICATIONNAME() {
            return PUBLICATIONNAME;
        }

        public void setPUBLICATIONNAME(String PUBLICATIONNAME) {
            this.PUBLICATIONNAME = PUBLICATIONNAME;
        }

        public String getAUTHORNAME() {
            return AUTHORNAME;
        }

        public void setAUTHORNAME(String AUTHORNAME) {
            this.AUTHORNAME = AUTHORNAME;
        }

        public String getTOPICNAME() {
            return TOPICNAME;
        }

        public void setTOPICNAME(String TOPICNAME) {
            this.TOPICNAME = TOPICNAME;
        }
    }
}
