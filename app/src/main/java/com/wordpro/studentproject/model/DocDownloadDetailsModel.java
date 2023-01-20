package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocDownloadDetailsModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("DocumentListDtls")
    @Expose
    private List<ListDocDownloadModel> documentListDtls = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ListDocDownloadModel> getDocumentListDtls() {
        return documentListDtls;
    }

    public void setDocumentListDtls(List<ListDocDownloadModel> documentListDtls) {
        this.documentListDtls = documentListDtls;
    }
}
