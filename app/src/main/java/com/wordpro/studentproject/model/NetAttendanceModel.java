package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 25-04-2018.
 */

public class NetAttendanceModel {

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
         * STUDENT_CODE : 0116061026
         * STUDENT_ID : 1032160253
         * ROLL_NO_INDEX : 54
         * YEARLY_ROLL_NUMBER : 54
         * STUD_FULL_NAME : Ms. Bhumika Vasant Sarode
         * STUD_ML_NAME_SHORT : Sarode Bhumika Vasant
         * STUD_FN_MN_SHORT : Ms. B.V.Sarode
         * STUD_INITIAL : BVS
         * STUD_LNAMEWISE : Sarode Bhumika Vasant
         * STUD_FNAMEWISE : BHUMIKA VASANT SARODE
         * SUBJECT_DETAIL_ID : 8201
         * APPLICABLE_NUMBER : 1
         * NO_OF_PERIODS_PRSNT : 3
         * TOTAL_NOOF_PERIODS : 4
         * NO_OF_PERIODS_LEAVE : 0
         * ATTEND_PER : 75
         * SUBJECT_DESCRIPTION : Numerical Methods and Computer Programming (2015 course)
         * SUB_SHORT_DESC : NMCP
         * APPLI_TYPE_SHORT_NAME : TH
         * TH_PRESENT : 10
         * TH_TOTPRDAPPL : 13
         * TH_PERCTG : 77
         * PR_PRESENT : 8
         * PR_TOTPRDAPPL : 8
         * PR_PERCTG : 100
         * TU_PRESENT : 0
         * TU_TOTPRDAPPL : 0
         * TU_PERCTG : 0
         */

        private String STUDENT_CODE;
        private String STUDENT_ID;
        private String ROLL_NO_INDEX;
        private String YEARLY_ROLL_NUMBER;
        private String STUD_FULL_NAME;
        private String STUD_ML_NAME_SHORT;
        private String STUD_FN_MN_SHORT;
        private String STUD_INITIAL;
        private String STUD_LNAMEWISE;
        private String STUD_FNAMEWISE;
        private String SUBJECT_DETAIL_ID;
        private String APPLICABLE_NUMBER;
        private String NO_OF_PERIODS_PRSNT;
        private String TOTAL_NOOF_PERIODS;
        private String NO_OF_PERIODS_LEAVE;
        private String ATTEND_PER;
        private String SUBJECT_DESCRIPTION;
        private String SUB_SHORT_DESC;
        private String APPLI_TYPE_SHORT_NAME;
        private String TH_PRESENT;
        private String TH_TOTPRDAPPL;
        private String TH_PERCTG;
        private String PR_PRESENT;
        private String PR_TOTPRDAPPL;
        private String PR_PERCTG;
        private String TU_PRESENT;
        private String TU_TOTPRDAPPL;
        private String TU_PERCTG;

        public String getSTUDENT_CODE() {
            return STUDENT_CODE;
        }

        public void setSTUDENT_CODE(String STUDENT_CODE) {
            this.STUDENT_CODE = STUDENT_CODE;
        }

        public String getSTUDENT_ID() {
            return STUDENT_ID;
        }

        public void setSTUDENT_ID(String STUDENT_ID) {
            this.STUDENT_ID = STUDENT_ID;
        }

        public String getROLL_NO_INDEX() {
            return ROLL_NO_INDEX;
        }

        public void setROLL_NO_INDEX(String ROLL_NO_INDEX) {
            this.ROLL_NO_INDEX = ROLL_NO_INDEX;
        }

        public String getYEARLY_ROLL_NUMBER() {
            return YEARLY_ROLL_NUMBER;
        }

        public void setYEARLY_ROLL_NUMBER(String YEARLY_ROLL_NUMBER) {
            this.YEARLY_ROLL_NUMBER = YEARLY_ROLL_NUMBER;
        }

        public String getSTUD_FULL_NAME() {
            return STUD_FULL_NAME;
        }

        public void setSTUD_FULL_NAME(String STUD_FULL_NAME) {
            this.STUD_FULL_NAME = STUD_FULL_NAME;
        }

        public String getSTUD_ML_NAME_SHORT() {
            return STUD_ML_NAME_SHORT;
        }

        public void setSTUD_ML_NAME_SHORT(String STUD_ML_NAME_SHORT) {
            this.STUD_ML_NAME_SHORT = STUD_ML_NAME_SHORT;
        }

        public String getSTUD_FN_MN_SHORT() {
            return STUD_FN_MN_SHORT;
        }

        public void setSTUD_FN_MN_SHORT(String STUD_FN_MN_SHORT) {
            this.STUD_FN_MN_SHORT = STUD_FN_MN_SHORT;
        }

        public String getSTUD_INITIAL() {
            return STUD_INITIAL;
        }

        public void setSTUD_INITIAL(String STUD_INITIAL) {
            this.STUD_INITIAL = STUD_INITIAL;
        }

        public String getSTUD_LNAMEWISE() {
            return STUD_LNAMEWISE;
        }

        public void setSTUD_LNAMEWISE(String STUD_LNAMEWISE) {
            this.STUD_LNAMEWISE = STUD_LNAMEWISE;
        }

        public String getSTUD_FNAMEWISE() {
            return STUD_FNAMEWISE;
        }

        public void setSTUD_FNAMEWISE(String STUD_FNAMEWISE) {
            this.STUD_FNAMEWISE = STUD_FNAMEWISE;
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

        public String getNO_OF_PERIODS_PRSNT() {
            return NO_OF_PERIODS_PRSNT;
        }

        public void setNO_OF_PERIODS_PRSNT(String NO_OF_PERIODS_PRSNT) {
            this.NO_OF_PERIODS_PRSNT = NO_OF_PERIODS_PRSNT;
        }

        public String getTOTAL_NOOF_PERIODS() {
            return TOTAL_NOOF_PERIODS;
        }

        public void setTOTAL_NOOF_PERIODS(String TOTAL_NOOF_PERIODS) {
            this.TOTAL_NOOF_PERIODS = TOTAL_NOOF_PERIODS;
        }

        public String getNO_OF_PERIODS_LEAVE() {
            return NO_OF_PERIODS_LEAVE;
        }

        public void setNO_OF_PERIODS_LEAVE(String NO_OF_PERIODS_LEAVE) {
            this.NO_OF_PERIODS_LEAVE = NO_OF_PERIODS_LEAVE;
        }

        public String getATTEND_PER() {
            return ATTEND_PER;
        }

        public void setATTEND_PER(String ATTEND_PER) {
            this.ATTEND_PER = ATTEND_PER;
        }

        public String getSUBJECT_DESCRIPTION() {
            return SUBJECT_DESCRIPTION;
        }

        public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
            this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
        }

        public String getSUB_SHORT_DESC() {
            return SUB_SHORT_DESC;
        }

        public void setSUB_SHORT_DESC(String SUB_SHORT_DESC) {
            this.SUB_SHORT_DESC = SUB_SHORT_DESC;
        }

        public String getAPPLI_TYPE_SHORT_NAME() {
            return APPLI_TYPE_SHORT_NAME;
        }

        public void setAPPLI_TYPE_SHORT_NAME(String APPLI_TYPE_SHORT_NAME) {
            this.APPLI_TYPE_SHORT_NAME = APPLI_TYPE_SHORT_NAME;
        }

        public String getTH_PRESENT() {
            return TH_PRESENT;
        }

        public void setTH_PRESENT(String TH_PRESENT) {
            this.TH_PRESENT = TH_PRESENT;
        }

        public String getTH_TOTPRDAPPL() {
            return TH_TOTPRDAPPL;
        }

        public void setTH_TOTPRDAPPL(String TH_TOTPRDAPPL) {
            this.TH_TOTPRDAPPL = TH_TOTPRDAPPL;
        }

        public String getTH_PERCTG() {
            return TH_PERCTG;
        }

        public void setTH_PERCTG(String TH_PERCTG) {
            this.TH_PERCTG = TH_PERCTG;
        }

        public String getPR_PRESENT() {
            return PR_PRESENT;
        }

        public void setPR_PRESENT(String PR_PRESENT) {
            this.PR_PRESENT = PR_PRESENT;
        }

        public String getPR_TOTPRDAPPL() {
            return PR_TOTPRDAPPL;
        }

        public void setPR_TOTPRDAPPL(String PR_TOTPRDAPPL) {
            this.PR_TOTPRDAPPL = PR_TOTPRDAPPL;
        }

        public String getPR_PERCTG() {
            return PR_PERCTG;
        }

        public void setPR_PERCTG(String PR_PERCTG) {
            this.PR_PERCTG = PR_PERCTG;
        }

        public String getTU_PRESENT() {
            return TU_PRESENT;
        }

        public void setTU_PRESENT(String TU_PRESENT) {
            this.TU_PRESENT = TU_PRESENT;
        }

        public String getTU_TOTPRDAPPL() {
            return TU_TOTPRDAPPL;
        }

        public void setTU_TOTPRDAPPL(String TU_TOTPRDAPPL) {
            this.TU_TOTPRDAPPL = TU_TOTPRDAPPL;
        }

        public String getTU_PERCTG() {
            return TU_PERCTG;
        }

        public void setTU_PERCTG(String TU_PERCTG) {
            this.TU_PERCTG = TU_PERCTG;
        }
    }
}
