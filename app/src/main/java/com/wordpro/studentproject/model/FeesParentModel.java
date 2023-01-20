package com.wordpro.studentproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeesParentModel {

    @SerializedName("FEE_INSTALLMENT_NO")
    @Expose
    private String fEEINSTALLMENTNO;
    @SerializedName("Child")
    @Expose
    private List<FeesChildModel> child = null;

    public String getfEEINSTALLMENTNO() {
        return fEEINSTALLMENTNO;
    }

    public void setfEEINSTALLMENTNO(String fEEINSTALLMENTNO) {
        this.fEEINSTALLMENTNO = fEEINSTALLMENTNO;
    }

    public List<FeesChildModel> getChild() {
        return child;
    }

    public void setChild(List<FeesChildModel> child) {
        this.child = child;
    }
}
