package com.wordpro.studentproject.model;

import java.util.List;

public class SlabModel {

    private int status;
    private List<LateChgDtlsBean> LateChgDtls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LateChgDtlsBean> getLateChgDtls() {
        return LateChgDtls;
    }

    public void setLateChgDtls(List<LateChgDtlsBean> LateChgDtls) {
        this.LateChgDtls = LateChgDtls;
    }

    public static class LateChgDtlsBean {
        /**
         * SLAB_MASTER_ID : 2
         * SLAB_DESC : test 2
         * Slab_for : CUMULATIVE
         * CALC_Pattern : DAYWISE
         * CENTRE_CODE : ENGG_SC
         * SLAB_FROM : 1
         * SLAB_TO : 10
         * SLAB_CHARGES : 100
         * CURRENCY_SYMBOL : Rupees
         * CURR_IMAGE :
         */

        private String SLAB_MASTER_ID;
        private String SLAB_DESC;
        private String Slab_for;
        private String CALC_Pattern;
        private String CENTRE_CODE;
        private String SLAB_FROM;
        private String SLAB_TO;
        private String SLAB_CHARGES;
        private String CURRENCY_SYMBOL;
        private String CURR_IMAGE;

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

        public String getSlab_for() {
            return Slab_for;
        }

        public void setSlab_for(String Slab_for) {
            this.Slab_for = Slab_for;
        }

        public String getCALC_Pattern() {
            return CALC_Pattern;
        }

        public void setCALC_Pattern(String CALC_Pattern) {
            this.CALC_Pattern = CALC_Pattern;
        }

        public String getCENTRE_CODE() {
            return CENTRE_CODE;
        }

        public void setCENTRE_CODE(String CENTRE_CODE) {
            this.CENTRE_CODE = CENTRE_CODE;
        }

        public String getSLAB_FROM() {
            return SLAB_FROM;
        }

        public void setSLAB_FROM(String SLAB_FROM) {
            this.SLAB_FROM = SLAB_FROM;
        }

        public String getSLAB_TO() {
            return SLAB_TO;
        }

        public void setSLAB_TO(String SLAB_TO) {
            this.SLAB_TO = SLAB_TO;
        }

        public String getSLAB_CHARGES() {
            return SLAB_CHARGES;
        }

        public void setSLAB_CHARGES(String SLAB_CHARGES) {
            this.SLAB_CHARGES = SLAB_CHARGES;
        }

        public String getCURRENCY_SYMBOL() {
            return CURRENCY_SYMBOL;
        }

        public void setCURRENCY_SYMBOL(String CURRENCY_SYMBOL) {
            this.CURRENCY_SYMBOL = CURRENCY_SYMBOL;
        }

        public String getCURR_IMAGE() {
            return CURR_IMAGE;
        }

        public void setCURR_IMAGE(String CURR_IMAGE) {
            this.CURR_IMAGE = CURR_IMAGE;
        }
    }

}
