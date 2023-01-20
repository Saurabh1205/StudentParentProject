package com.wordpro.studentproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.CertificateAdapter;

import static com.wordpro.studentproject.activities.NavigationActivity.documnetDtlsArrayList;

/**
 * Created by wccs1980 on 05-06-2018.
 */

public class CertificateInfoFrag extends Fragment{

    Context context;
    CertificateAdapter certificateAdapter;

    public CertificateInfoFrag() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.frag_certificate_info, container, false);

        RecyclerView recyVwCertificate=(RecyclerView)rootView.findViewById(R.id.recyVwCertificate);
        certificateAdapter=new CertificateAdapter(getActivity(),documnetDtlsArrayList);
        recyVwCertificate.setLayoutManager(new LinearLayoutManager(context));
        recyVwCertificate.setAdapter(certificateAdapter);


        return rootView;
    }
}
