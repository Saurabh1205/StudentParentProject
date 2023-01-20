package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticPattenSub {
    @SerializedName("NOTICE_PATTERN")
    @Expose
    private String nOTICEPATTERN;

    @SerializedName("Parent")
    @Expose
    private List<NoticPattenParents> parent = null;

    public String getnOTICEPATTERN() {
        return nOTICEPATTERN;
    }

    public void setnOTICEPATTERN(String nOTICEPATTERN) {
        this.nOTICEPATTERN = nOTICEPATTERN;
    }

    public List<NoticPattenParents> getParent() {
        return parent;
    }

    public void setParent(List<NoticPattenParents> parent) {
        this.parent = parent;
    }
}
