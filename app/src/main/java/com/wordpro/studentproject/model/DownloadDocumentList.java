package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DownloadDocumentList {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("DocumentListDtls")
    @Expose
    private List<DocumentListDetails> documentListDtls = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DocumentListDetails> getDocumentListDtls() {
        return documentListDtls;
    }

    public void setDocumentListDtls(List<DocumentListDetails> documentListDtls) {
        this.documentListDtls = documentListDtls;
    }
}
