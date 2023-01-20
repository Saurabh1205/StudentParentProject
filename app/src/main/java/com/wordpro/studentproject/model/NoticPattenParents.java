package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticPattenParents {
    @SerializedName("NOTICE_HEADER")
    @Expose
    private String nOTICEHEADER;
    @SerializedName("Child")
    @Expose
    private List<NoticPattenChaild> child = null;

    public String getnOTICEHEADER() {
        return nOTICEHEADER;
    }

    public void setnOTICEHEADER(String nOTICEHEADER) {
        this.nOTICEHEADER = nOTICEHEADER;
    }

    public List<NoticPattenChaild> getChild() {
        return child;
    }

    public void setChild(List<NoticPattenChaild> child) {
        this.child = child;
    }
}
