package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DynamicArrayList {

    @SerializedName("NOTICE_PATTERN")
    @Expose
    private String nOTICEPATTERN;

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    @SerializedName("GROUP_NAME")
    @Expose
    private String GROUP_NAME;
    @SerializedName("notice")
    @Expose
    private List<DynamicNoticArray> notice = null;


    public String getnOTICEPATTERN() {
        return nOTICEPATTERN;
    }

    public void setnOTICEPATTERN(String nOTICEPATTERN) {
        this.nOTICEPATTERN = nOTICEPATTERN;
    }

    public List<DynamicNoticArray> getNotice() {
        return notice;
    }

    public void setNotice(List<DynamicNoticArray> notice) {
        this.notice = notice;
    }
}
