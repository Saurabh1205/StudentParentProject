package com.wordpro.studentproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.helper.PrefManager;

public class New_Issuedbook_Fragment extends Fragment {

    private PrefManager pref;
    public static String TAG = New_Issuedbook_Fragment.class.getSimpleName();
    private TextView  txt_view;
    public New_Issuedbook_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.new_arrivals_fragment, container, false);
        pref = new PrefManager(getActivity());
        String value = getArguments().getString("Issued Books");
        txt_view =(TextView)view.findViewById(R.id.txt_view);
        txt_view.setText("Issued Books");
        Log.d(TAG, value);
        return view;

    }


}
