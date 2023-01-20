package com.wordpro.studentproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.Library.RulesDescrptnActivity;
import com.wordpro.studentproject.adapter.RulesAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.RulesModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

public class New_Rules_Fragment extends Fragment {

    private PrefManager pref;
    public static String TAG = New_Rules_Fragment.class.getSimpleName();
    private TextView  txt_view;
    private UtilityClass utilityClassObj;


    public static RulesModel rulesModel;
    public static ArrayList<RulesModel.CirculationRuleDtlsBean> circulationRuleDtlsArraylist;
    public static RulesAdpter rulesAdpter;
    public static ArrayList<String> data;
    public New_Rules_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.new_arrivals_fragment, container, false);
        pref = new PrefManager(getActivity());
        utilityClassObj =new UtilityClass(getActivity());

        String value = getArguments().getString("Rules");
        txt_view =(TextView)view.findViewById(R.id.txt_view);
        txt_view.setText("Rules");
        getRules();
        Log.d(TAG, value);
        return view;

    }


    private void getRules(){
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.GetCirculationRule_url(URLEndPoints.Constance_StudentCenterCode,"9");
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d(TAG, "RESPONSE : " + response);
                try {

                    Gson gson = new Gson();
                    rulesModel = gson.fromJson(response, RulesModel.class);

                    if (rulesModel.getStatus() == 1) {
                        //  mProgressDialog.dismiss();

                        circulationRuleDtlsArraylist = (ArrayList<RulesModel.CirculationRuleDtlsBean>) rulesModel.getCirculationRuleDtls();
                        if (circulationRuleDtlsArraylist.size() != 0 && circulationRuleDtlsArraylist != null) {

                            data = new ArrayList<>();

                            for (int i = 0; i < circulationRuleDtlsArraylist.size(); i++) {

                                if (i == 0) {
                                    String LIBRARY_NAME = circulationRuleDtlsArraylist.get(i).getLIBRARY_NAME();
                                    String MEMBER_TYPE = circulationRuleDtlsArraylist.get(i).getMEMBER_TYPE();
                                    String BOOK_RULE_DESC = circulationRuleDtlsArraylist.get(i).getBOOK_RULE_DESC();
                                    String TYPEWISE_BOOK_ALLOWED = circulationRuleDtlsArraylist.get(i).getBoKTyp_BokAllowed();
                                    String BOOK_TYPE_DESCRIPTION = circulationRuleDtlsArraylist.get(i).getBOOK_TYPE_DESCRIPTION();
                                    String MAX_BOOK_ALLOWED = circulationRuleDtlsArraylist.get(i).getConsol_Tot_Books();
                                    String MAX_DAYS_ALLOWED = circulationRuleDtlsArraylist.get(i).getMAX_DAYS_ALLOWED();
                                    String SLAB_DESC = circulationRuleDtlsArraylist.get(i).getSLAB_DESC();
                                    String SLAB_MASTER_ID = circulationRuleDtlsArraylist.get(i).getSLAB_MASTER_ID();

                                    String rule1 = "The circulation rules of " + LIBRARY_NAME + " are described under rule " + BOOK_RULE_DESC;
                                    data.add(rule1);
                                    String rule2 = "Maximum " + MAX_BOOK_ALLOWED + "  books can be issued from the " + LIBRARY_NAME;
                                    data.add(rule2);
                                    String rule3 = "Its mandatory to return the issued book upto the expected return date.If a person fails to return the issued book on the expected return date ,then the fine will be applicable based on the Fine Slab";
                                    data.add(rule3);


                                }
                            }


                            for (int i = 0; i < circulationRuleDtlsArraylist.size(); i++) {

                                String BoKTyp_BokAllowed = circulationRuleDtlsArraylist.get(i).getBoKTyp_BokAllowed();
                                String MAX_DAYS_ALLOWED = circulationRuleDtlsArraylist.get(i).getMAX_DAYS_ALLOWED();
                                String BOOK_TYPE_DESCRIPTION = circulationRuleDtlsArraylist.get(i).getBOOK_TYPE_DESCRIPTION();
                                String SLAB_DESC = circulationRuleDtlsArraylist.get(i).getSLAB_DESC();

                                String rule = "Maximum " + BoKTyp_BokAllowed + " " + BOOK_TYPE_DESCRIPTION + " type books are allowed for maximum " + MAX_DAYS_ALLOWED + " days. If the issued books are not returned within the expected return date to the Library, then Fine Slab for " + BOOK_TYPE_DESCRIPTION + " book type is " + SLAB_DESC + ".";
                                data.add(rule);


                            }

                            Object[] st1 = data.toArray();
                            for (Object s : st1) {
                                if (data.indexOf(s) != data.lastIndexOf(s)) {
                                    data.remove(data.lastIndexOf(s));
                                }
                            }

                            rulesAdpter = new RulesAdpter(getActivity(), data);
                            Intent intent = new Intent(getActivity(), RulesDescrptnActivity.class);
                            startActivity(intent);
                            getActivity().finish();

                        } else {
                            utilityClassObj.stopLoader();
                          /*  Snackbar.make(show_error_record, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();*/
                        }

                    } else if (rulesModel.getStatus() == 0) {
                        utilityClassObj.stopLoader();
                     /*   Snackbar.make(show_error_record, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }


                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Log.d(TAG, "Error : " + e.getMessage());
                    /*Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
               /* Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

}
