package com.wordpro.studentproject.model;

import java.util.List;

public class NewArrivalBookModel {


    private int status;
    private List<LibBokNewArrDtlsBean> LibBokNewArrDtls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LibBokNewArrDtlsBean> getLibBokNewArrDtls() {
        return LibBokNewArrDtls;
    }

    public void setLibBokNewArrDtls(List<LibBokNewArrDtlsBean> LibBokNewArrDtls) {
        this.LibBokNewArrDtls = LibBokNewArrDtls;
    }

    public static class LibBokNewArrDtlsBean {
        /**
         * book_title_id : 162
         * book_title : A  Text Book Of Pathology
         * Synonyms :
         * book_cover_image :
         * sub_category_id : 2
         * category_description : Text book
         * sub_class_description :
         * publication_id : 13
         * publication_name : NEW CENTRAL BOOK AGENCY
         * publication_short_name : NCB
         * AUTHOR : Sant M
         * BOOK_ARRIVAL_DATE : 11/27/2018 12:00:00 AM
         * ISBN_CODE :
         * BOOK_PRICE : 1000
         * Total_books : 2
         * TotBk_OnShelf : 2
         */

        private String book_title_id;
        private String book_title;
        private String Synonyms;
        private String book_cover_image;
        private String sub_category_id;
        private String category_description;
        private String sub_class_description;
        private String publication_id;
        private String publication_name;
        private String publication_short_name;
        private String AUTHOR;
        private String BOOK_ARRIVAL_DATE;
        private String ISBN_CODE;
        private String BOOK_PRICE;
        private String Total_books;
        private String TotBk_OnShelf;

        public String getBook_title_id() {
            return book_title_id;
        }

        public void setBook_title_id(String book_title_id) {
            this.book_title_id = book_title_id;
        }

        public String getBook_title() {
            return book_title;
        }

        public void setBook_title(String book_title) {
            this.book_title = book_title;
        }

        public String getSynonyms() {
            return Synonyms;
        }

        public void setSynonyms(String Synonyms) {
            this.Synonyms = Synonyms;
        }

        public String getBook_cover_image() {
            return book_cover_image;
        }

        public void setBook_cover_image(String book_cover_image) {
            this.book_cover_image = book_cover_image;
        }

        public String getSub_category_id() {
            return sub_category_id;
        }

        public void setSub_category_id(String sub_category_id) {
            this.sub_category_id = sub_category_id;
        }

        public String getCategory_description() {
            return category_description;
        }

        public void setCategory_description(String category_description) {
            this.category_description = category_description;
        }

        public String getSub_class_description() {
            return sub_class_description;
        }

        public void setSub_class_description(String sub_class_description) {
            this.sub_class_description = sub_class_description;
        }

        public String getPublication_id() {
            return publication_id;
        }

        public void setPublication_id(String publication_id) {
            this.publication_id = publication_id;
        }

        public String getPublication_name() {
            return publication_name;
        }

        public void setPublication_name(String publication_name) {
            this.publication_name = publication_name;
        }

        public String getPublication_short_name() {
            return publication_short_name;
        }

        public void setPublication_short_name(String publication_short_name) {
            this.publication_short_name = publication_short_name;
        }

        public String getAUTHOR() {
            return AUTHOR;
        }

        public void setAUTHOR(String AUTHOR) {
            this.AUTHOR = AUTHOR;
        }

        public String getBOOK_ARRIVAL_DATE() {
            return BOOK_ARRIVAL_DATE;
        }

        public void setBOOK_ARRIVAL_DATE(String BOOK_ARRIVAL_DATE) {
            this.BOOK_ARRIVAL_DATE = BOOK_ARRIVAL_DATE;
        }

        public String getISBN_CODE() {
            return ISBN_CODE;
        }

        public void setISBN_CODE(String ISBN_CODE) {
            this.ISBN_CODE = ISBN_CODE;
        }

        public String getBOOK_PRICE() {
            return BOOK_PRICE;
        }

        public void setBOOK_PRICE(String BOOK_PRICE) {
            this.BOOK_PRICE = BOOK_PRICE;
        }

        public String getTotal_books() {
            return Total_books;
        }

        public void setTotal_books(String Total_books) {
            this.Total_books = Total_books;
        }

        public String getTotBk_OnShelf() {
            return TotBk_OnShelf;
        }

        public void setTotBk_OnShelf(String TotBk_OnShelf) {
            this.TotBk_OnShelf = TotBk_OnShelf;
        }
    }
}
