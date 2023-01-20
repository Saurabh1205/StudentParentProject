package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewGetNoticDetails {
    @SerializedName("NOTICE_TYPE")
    @Expose
    private String nOTICETYPE;
    @SerializedName("NOTICE_HEADER")
    @Expose
    private String nOTICEHEADER;
    @SerializedName("NOTICE_DESCRIPTION")
    @Expose
    private String nOTICEDESCRIPTION;
    @SerializedName("attachment")
    @Expose
    private List<NewGetNoticAttachment> attachment = null;

    public String getnOTICETYPE() {
        return nOTICETYPE;
    }

    public void setnOTICETYPE(String nOTICETYPE) {
        this.nOTICETYPE = nOTICETYPE;
    }

    public String getnOTICEHEADER() {
        return nOTICEHEADER;
    }

    public void setnOTICEHEADER(String nOTICEHEADER) {
        this.nOTICEHEADER = nOTICEHEADER;
    }

    public String getnOTICEDESCRIPTION() {
        return nOTICEDESCRIPTION;
    }

    public void setnOTICEDESCRIPTION(String nOTICEDESCRIPTION) {
        this.nOTICEDESCRIPTION = nOTICEDESCRIPTION;
    }

    public List<NewGetNoticAttachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<NewGetNoticAttachment> attachment) {
        this.attachment = attachment;
    }
}
