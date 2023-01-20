package com.wordpro.studentproject.model;

import java.util.List;

public class NewArrvlMnthModel {

    private int status;
    private List<LibBokNewArrAbstBean> LibBokNewArrAbst;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LibBokNewArrAbstBean> getLibBokNewArrAbst() {
        return LibBokNewArrAbst;
    }

    public void setLibBokNewArrAbst(List<LibBokNewArrAbstBean> LibBokNewArrAbst) {
        this.LibBokNewArrAbst = LibBokNewArrAbst;
    }

    public static class LibBokNewArrAbstBean {
        /**
         * LIB_LIBRARY_MST_ID : 1
         * LIBRARY_DESCRIPTION : CENTRAL LIBRARY
         * LIBRARY_NAME : CENTRAL LIBRARY
         * For_Month_Year : MAY/2019
         * Start_Day_Month : 8/1/2018 12:00:00 AM
         * Last_Day_Month : 8/31/2018 12:00:00 AM
         * No_of_Book_Title : 1
         */

        private String LIB_LIBRARY_MST_ID;
        private String LIBRARY_DESCRIPTION;
        private String LIBRARY_NAME;
        private String For_Month_Year;
        private String Start_Day_Month;
        private String Last_Day_Month;
        private String No_of_Book_Title;

        public String getLIB_LIBRARY_MST_ID() {
            return LIB_LIBRARY_MST_ID;
        }

        public void setLIB_LIBRARY_MST_ID(String LIB_LIBRARY_MST_ID) {
            this.LIB_LIBRARY_MST_ID = LIB_LIBRARY_MST_ID;
        }

        public String getLIBRARY_DESCRIPTION() {
            return LIBRARY_DESCRIPTION;
        }

        public void setLIBRARY_DESCRIPTION(String LIBRARY_DESCRIPTION) {
            this.LIBRARY_DESCRIPTION = LIBRARY_DESCRIPTION;
        }

        public String getLIBRARY_NAME() {
            return LIBRARY_NAME;
        }

        public void setLIBRARY_NAME(String LIBRARY_NAME) {
            this.LIBRARY_NAME = LIBRARY_NAME;
        }

        public String getFor_Month_Year() {
            return For_Month_Year;
        }

        public void setFor_Month_Year(String For_Month_Year) {
            this.For_Month_Year = For_Month_Year;
        }

        public String getStart_Day_Month() {
            return Start_Day_Month;
        }

        public void setStart_Day_Month(String Start_Day_Month) {
            this.Start_Day_Month = Start_Day_Month;
        }

        public String getLast_Day_Month() {
            return Last_Day_Month;
        }

        public void setLast_Day_Month(String Last_Day_Month) {
            this.Last_Day_Month = Last_Day_Month;
        }

        public String getNo_of_Book_Title() {
            return No_of_Book_Title;
        }

        public void setNo_of_Book_Title(String No_of_Book_Title) {
            this.No_of_Book_Title = No_of_Book_Title;
        }
    }
}

