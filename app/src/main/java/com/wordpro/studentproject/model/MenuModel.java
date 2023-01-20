package com.wordpro.studentproject.model;

import java.util.List;

public class MenuModel {


    /**
     * status : 1
     * MenuCodeList : [{"MENU_ID":"3817","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"Attendance","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_ATTND","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3818","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Day-wise","PARENT_MENU_ID":"3817","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_ATTND_DAYWISE","PARENT_MENU_CODE":"STUAPP_ATTND","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3819","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Span-wise","PARENT_MENU_ID":"3817","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_ATTND_SPANWISE","PARENT_MENU_CODE":"STUAPP_ATTND","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3820","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Subject-wise","PARENT_MENU_ID":"3817","MENU_DISPLAY_SEQ_NO":"3","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"3","MENU_CODE":"STUAPP_ATTND_SUBJ_WISE","PARENT_MENU_CODE":"STUAPP_ATTND","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3821","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Net","PARENT_MENU_ID":"3817","MENU_DISPLAY_SEQ_NO":"4","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"4","MENU_CODE":"STUAPP_ATTND_NET","PARENT_MENU_CODE":"STUAPP_ATTND","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3822","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"Syllabus","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_SYLBUS","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3823","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Course List","PARENT_MENU_ID":"3822","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_SYLBUS_SUBJ","PARENT_MENU_CODE":"STUAPP_SYLBUS","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3824","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"University Syllabus","PARENT_MENU_ID":"3822","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_SYLBUS_UNVSITY","PARENT_MENU_CODE":"STUAPP_SYLBUS","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3825","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Teaching Plan","PARENT_MENU_ID":"3822","MENU_DISPLAY_SEQ_NO":"3","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"3","MENU_CODE":"STUAPP_SYLBUS_TEACH_PLAN","PARENT_MENU_CODE":"STUAPP_SYLBUS","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3826","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Syllabus Status","PARENT_MENU_ID":"3822","MENU_DISPLAY_SEQ_NO":"4","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"4","MENU_CODE":"STUAPP_SYLBUS_STATUS","PARENT_MENU_CODE":"STUAPP_SYLBUS","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3827","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Subject-wise Faculty","PARENT_MENU_ID":"3822","MENU_DISPLAY_SEQ_NO":"5","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"5","MENU_CODE":"STUAPP_SYLBUS_FAC","PARENT_MENU_CODE":"STUAPP_SYLBUS","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3828","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"Schedule","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"3","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"3","MENU_CODE":"STUAPP_SCHDUL","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3829","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Time table","PARENT_MENU_ID":"3828","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_SCHDUL_TMTBL","PARENT_MENU_CODE":"STUAPP_SCHDUL","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3830","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Extra Lecture","PARENT_MENU_ID":"3828","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_SCHDUL_EXTR_LEC","PARENT_MENU_CODE":"STUAPP_SCHDUL","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3831","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"My Batches","PARENT_MENU_ID":"3828","MENU_DISPLAY_SEQ_NO":"3","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"3","MENU_CODE":"STUAPP_SCHDUL_BATCHES","PARENT_MENU_CODE":"STUAPP_SCHDUL","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3832","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"Fees","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"4","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"4","MENU_CODE":"STUAPP_FEE","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3833","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Academic","PARENT_MENU_ID":"3832","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_FEE_ACAD","PARENT_MENU_CODE":"STUAPP_FEE","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3834","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Non-Academic","PARENT_MENU_ID":"3832","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_FEE_NONACAD","PARENT_MENU_CODE":"STUAPP_FEE","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3835","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Complete Fees","PARENT_MENU_ID":"3832","MENU_DISPLAY_SEQ_NO":"3","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"3","MENU_CODE":"STUAPP_FEE_CMPLTFEE","PARENT_MENU_CODE":"STUAPP_FEE","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3836","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"Profile","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"5","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"5","MENU_CODE":"STUAPP_PRFL","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3837","MODULE_ID":"63","MNMENU":"M","MENU_NAME":"E-Learning","PARENT_MENU_ID":"0","MENU_DISPLAY_SEQ_NO":"6","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"6","MENU_CODE":"STUAPP_E_LEARN","PARENT_MENU_CODE":"","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3838","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Assignment","PARENT_MENU_ID":"3837","MENU_DISPLAY_SEQ_NO":"1","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"1","MENU_CODE":"STUAPP_E_LEARN_ASSIG","PARENT_MENU_CODE":"STUAPP_E_LEARN","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""},{"MENU_ID":"3839","MODULE_ID":"63","MNMENU":"S","MENU_NAME":"Notes","PARENT_MENU_ID":"3837","MENU_DISPLAY_SEQ_NO":"2","MENU_VER_NO":"Premium","MENU_INSTALLED_FLAG":"Y","MENU_LINK":"*","MENU_TOOL_TIP":"*","PARENT_MENU_NAME":"","NEW_MENU_DISP_SEQ_NO":"2","MENU_CODE":"STUAPP_E_LEARN_NOTES","PARENT_MENU_CODE":"STUAPP_E_LEARN","MN_MSEQNO":"","SB_MSEQNO":"","OPT_MSEQNO":""}]
     * ShortCutList : null
     */

    private int status;
    private Object ShortCutList;
    private List<MenuCodeListBean> MenuCodeList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getShortCutList() {
        return ShortCutList;
    }

    public void setShortCutList(Object ShortCutList) {
        this.ShortCutList = ShortCutList;
    }

    public List<MenuCodeListBean> getMenuCodeList() {
        return MenuCodeList;
    }

    public void setMenuCodeList(List<MenuCodeListBean> MenuCodeList) {
        this.MenuCodeList = MenuCodeList;
    }

    public static class MenuCodeListBean {
        /**
         * MENU_ID : 3817
         * MODULE_ID : 63
         * MNMENU : M
         * MENU_NAME : Attendance
         * PARENT_MENU_ID : 0
         * MENU_DISPLAY_SEQ_NO : 1
         * MENU_VER_NO : Premium
         * MENU_INSTALLED_FLAG : Y
         * MENU_LINK : *
         * MENU_TOOL_TIP : *
         * PARENT_MENU_NAME :
         * NEW_MENU_DISP_SEQ_NO : 1
         * MENU_CODE : STUAPP_ATTND
         * PARENT_MENU_CODE :
         * MN_MSEQNO :
         * SB_MSEQNO :
         * OPT_MSEQNO :
         */

        private String MENU_ID;
        private String MODULE_ID;
        private String MNMENU;
        private String MENU_NAME;
        private String PARENT_MENU_ID;
        private String MENU_DISPLAY_SEQ_NO;
        private String MENU_VER_NO;
        private String MENU_INSTALLED_FLAG;
        private String MENU_LINK;
        private String MENU_TOOL_TIP;
        private String PARENT_MENU_NAME;
        private String NEW_MENU_DISP_SEQ_NO;
        private String MENU_CODE;
        private String PARENT_MENU_CODE;
        private String MN_MSEQNO;
        private String SB_MSEQNO;
        private String OPT_MSEQNO;

        public String getMENU_ID() {
            return MENU_ID;
        }

        public void setMENU_ID(String MENU_ID) {
            this.MENU_ID = MENU_ID;
        }

        public String getMODULE_ID() {
            return MODULE_ID;
        }

        public void setMODULE_ID(String MODULE_ID) {
            this.MODULE_ID = MODULE_ID;
        }

        public String getMNMENU() {
            return MNMENU;
        }

        public void setMNMENU(String MNMENU) {
            this.MNMENU = MNMENU;
        }

        public String getMENU_NAME() {
            return MENU_NAME;
        }

        public void setMENU_NAME(String MENU_NAME) {
            this.MENU_NAME = MENU_NAME;
        }

        public String getPARENT_MENU_ID() {
            return PARENT_MENU_ID;
        }

        public void setPARENT_MENU_ID(String PARENT_MENU_ID) {
            this.PARENT_MENU_ID = PARENT_MENU_ID;
        }

        public String getMENU_DISPLAY_SEQ_NO() {
            return MENU_DISPLAY_SEQ_NO;
        }

        public void setMENU_DISPLAY_SEQ_NO(String MENU_DISPLAY_SEQ_NO) {
            this.MENU_DISPLAY_SEQ_NO = MENU_DISPLAY_SEQ_NO;
        }

        public String getMENU_VER_NO() {
            return MENU_VER_NO;
        }

        public void setMENU_VER_NO(String MENU_VER_NO) {
            this.MENU_VER_NO = MENU_VER_NO;
        }

        public String getMENU_INSTALLED_FLAG() {
            return MENU_INSTALLED_FLAG;
        }

        public void setMENU_INSTALLED_FLAG(String MENU_INSTALLED_FLAG) {
            this.MENU_INSTALLED_FLAG = MENU_INSTALLED_FLAG;
        }

        public String getMENU_LINK() {
            return MENU_LINK;
        }

        public void setMENU_LINK(String MENU_LINK) {
            this.MENU_LINK = MENU_LINK;
        }

        public String getMENU_TOOL_TIP() {
            return MENU_TOOL_TIP;
        }

        public void setMENU_TOOL_TIP(String MENU_TOOL_TIP) {
            this.MENU_TOOL_TIP = MENU_TOOL_TIP;
        }

        public String getPARENT_MENU_NAME() {
            return PARENT_MENU_NAME;
        }

        public void setPARENT_MENU_NAME(String PARENT_MENU_NAME) {
            this.PARENT_MENU_NAME = PARENT_MENU_NAME;
        }

        public String getNEW_MENU_DISP_SEQ_NO() {
            return NEW_MENU_DISP_SEQ_NO;
        }

        public void setNEW_MENU_DISP_SEQ_NO(String NEW_MENU_DISP_SEQ_NO) {
            this.NEW_MENU_DISP_SEQ_NO = NEW_MENU_DISP_SEQ_NO;
        }

        public String getMENU_CODE() {
            return MENU_CODE;
        }

        public void setMENU_CODE(String MENU_CODE) {
            this.MENU_CODE = MENU_CODE;
        }

        public String getPARENT_MENU_CODE() {
            return PARENT_MENU_CODE;
        }

        public void setPARENT_MENU_CODE(String PARENT_MENU_CODE) {
            this.PARENT_MENU_CODE = PARENT_MENU_CODE;
        }

        public String getMN_MSEQNO() {
            return MN_MSEQNO;
        }

        public void setMN_MSEQNO(String MN_MSEQNO) {
            this.MN_MSEQNO = MN_MSEQNO;
        }

        public String getSB_MSEQNO() {
            return SB_MSEQNO;
        }

        public void setSB_MSEQNO(String SB_MSEQNO) {
            this.SB_MSEQNO = SB_MSEQNO;
        }

        public String getOPT_MSEQNO() {
            return OPT_MSEQNO;
        }

        public void setOPT_MSEQNO(String OPT_MSEQNO) {
            this.OPT_MSEQNO = OPT_MSEQNO;
        }
    }
}
