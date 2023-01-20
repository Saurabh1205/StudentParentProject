package com.wordpro.studentproject.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.activities.certificate.CertificateApplnActivity;
import com.wordpro.studentproject.activities.profile_menu.ProfileActivity;
import com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.ProfileModel;
import com.wordpro.studentproject.model.ProfilePicModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import android.support.v4.app.FragmentManager;


import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragAttendance;
import static com.wordpro.studentproject.activities.NavigationActivity.fragE_learning;
import static com.wordpro.studentproject.activities.NavigationActivity.fragPayables;
import static com.wordpro.studentproject.activities.NavigationActivity.fragSchedule;
import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.profileModel;
import static com.wordpro.studentproject.activities.NavigationActivity.profilePicArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.profilePicModel;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.studentDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.addressDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.parentDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.documnetDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.entExamDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.selfRegistionDtlsArrayList;


import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    public static String TAG=DashboardFragment.class.getSimpleName();
    RelativeLayout lytMainMenu;
    LinearLayout lytAttendance, lytSyllabus, lytE_Learning, lytFees, lytProfile, lytSchedule,lytLibrary,lytCertificate;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    ProgressDialog navg_progress;

    //Menu Name
    public static final String KEYSTUAPP_ATTND = "STUAPP_ATTND";
    public static final String KEYSTUAPP_SYLBUS = "STUAPP_SYLBUS";
    public static final String KEYSTUAPP_SCHDUL="STUAPP_SCHDUL";
    public static final String KEYSTUAPP_FEE="STUAPP_FEE";
    public static final String KEYSTUAPP_PRFL="STUAPP_PRFL";
    public static final String KEYSTUAPP_E_LEARN="STUAPP_E_LEARN";

    public static LibraryFragment libraryFragment=new LibraryFragment();

    public DashboardFragment() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        lytAttendance = (LinearLayout) view.findViewById(R.id.lytAttendance);
        lytAttendance.setVisibility(View.GONE);

        lytSyllabus = (LinearLayout) view.findViewById(R.id.lytSyllabus);
        lytSyllabus.setVisibility(View.GONE);

        lytE_Learning = (LinearLayout) view.findViewById(R.id.lytE_Learning);
        lytE_Learning.setVisibility(View.GONE);

        lytFees = (LinearLayout) view.findViewById(R.id.lytFees);
        lytFees.setVisibility(View.GONE);

        lytProfile = (LinearLayout) view.findViewById(R.id.lytProfile);
        lytProfile.setVisibility(View.GONE);

        lytSchedule = (LinearLayout) view.findViewById(R.id.lytSchedule);
        lytSchedule.setVisibility(View.GONE);

        lytLibrary=(LinearLayout)view.findViewById(R.id.lytLibrary);
        lytLibrary.setVisibility(View.GONE);

        lytCertificate=(LinearLayout)view.findViewById(R.id.lytCertificate);
       // lytCertificate.setVisibility(View.VISIBLE);

        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceContent);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        txt3=(TextView)view.findViewById(R.id.txt3);
        txt3.setTypeface(typefaceContent);
        txt4=(TextView)view.findViewById(R.id.txt4);
        txt4.setTypeface(typefaceContent);
        txt5=(TextView)view.findViewById(R.id.txt5);
        txt5.setTypeface(typefaceContent);
        txt6=(TextView)view.findViewById(R.id.txt6);
        txt6.setTypeface(typefaceContent);
        txt7=(TextView)view.findViewById(R.id.txt7);
        txt7.setTypeface(typefaceContent);
        txt8=(TextView)view.findViewById(R.id.txt8);
        txt8.setTypeface(typefaceContent);

        lytMainMenu = (RelativeLayout) view.findViewById(R.id.lytMainMenu);
        navg_progress =new ProgressDialog(getActivity());
        if (!isNetworkAvailable(getActivity())) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the Alert Dialog Message
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // Restart the Activity
                                    Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            funProfilePic("navigationPic");

        }


        if(studMainMenu!=null && studMainMenu.size()!=0){

            for (int i = 0; i < studMainMenu.size(); i++) {

                String value = studMainMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_ATTND)) {

                    lytAttendance.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS)) {

                    lytSyllabus.setVisibility(View.VISIBLE);

                }else if(value.equalsIgnoreCase(KEYSTUAPP_SCHDUL)){

                    lytSchedule.setVisibility(View.VISIBLE);

                }else if (value.equalsIgnoreCase(KEYSTUAPP_FEE)) {

                    lytFees.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_PRFL)) {

                    lytProfile.setVisibility(View.VISIBLE);

                }else if(value.equalsIgnoreCase(KEYSTUAPP_E_LEARN)){

                    lytE_Learning.setVisibility(View.VISIBLE);

                }


            }


        }


        lytAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragAttendance.setRetainInstance(true);
                fragAttendance.show(fragmentManager, "fragAttendance");
            }
        });

        lytSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragSyllabus.setRetainInstance(true);
                fragSyllabus.show(fragmentManager, "fragSyllabus");
            }
        });

        lytFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragPayables.setRetainInstance(true);
                fragPayables.show(fragmentManager, "fragPayables");
            }
        });

        lytSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                fragSchedule.setRetainInstance(true);
                fragSchedule.show(fragmentManager, "fragSchedule");

            }
        });

        lytE_Learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragE_learning.setRetainInstance(true);
                fragE_learning.show(fragmentManager, "fragE_learning");
            }
        });

        lytProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(getActivity())) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Set the Alert Dialog Message
                    builder.setMessage("Internet Connection Required")
                            .setCancelable(false)
                            .setPositiveButton("Retry",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            // Restart the Activity
                                            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    funProfileDetails();

                }
            }
        });


        lytLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                libraryFragment.setRetainInstance(true);
                libraryFragment.show(fragmentManager,"libraryfragmt");

            }
        });


        lytCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), CertificateApplnActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }


    private void funProfileDetails() {
        navg_progress.setMessage("Please wait loading!");
        navg_progress.setCanceledOnTouchOutside(false);
        navg_progress.show();

        String url = BASE_URL + URLEndPoints.getProfileDetails(student_id, studCenterCode);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject responseObj = new JSONObject(response);
                    Gson gson = new Gson();
                    profileModel = gson.fromJson(response, ProfileModel.class);

                    if (profileModel.getStatus() == 1) {

                        navg_progress.dismiss();
                        studentDtlsArrayList = (ArrayList<ProfileModel.StudentDtlsBean>) profileModel.getStudent_Dtls();
                        addressDtlsArrayList = (ArrayList<ProfileModel.AddressDtlsBean>) profileModel.getAddress_Dtls();
                        //collegeDtlsArrayList=(ArrayList<ProfileM>) profileModel.getCollege_Dtls();
                        parentDtlsArrayList = (ArrayList<ProfileModel.ParentDtlsBean>) profileModel.getParent_Dtls();
                        documnetDtlsArrayList = (ArrayList<ProfileModel.DocumnetDtlsBean>) profileModel.getDocumnet_Dtls();
                        entExamDtlsArrayList = (ArrayList<ProfileModel.EntExamDtlsBean>) profileModel.getEntExam_Dtls();
                        selfRegistionDtlsArrayList = (ArrayList<ProfileModel.SelfRegistionDtlsBean>) profileModel.getSelfRegistion_Dtls();

                        if (studentDtlsArrayList != null && studentDtlsArrayList.size()!=0) {

                           // funProfilePic("profilePic");
                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
                            intent.putExtra("imageString", NavigationActivity.imageString);
                            startActivity(intent);
                            getActivity().finish();


                        } else {
                            Snackbar.make(lytMainMenu, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (profileModel.getStatus() == 0) {
                        navg_progress.dismiss();
                        Snackbar.make(lytMainMenu, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error: " + e.getMessage());

                    Snackbar.make(lytMainMenu, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    navg_progress.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                lytMainMenu.setVisibility(View.GONE);
                navg_progress.hide();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        String message = null;
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            message = getResources().getString(R.string.error_timeout);
        } else if (error instanceof AuthFailureError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            message = getResources().getString(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            message = getResources().getString(R.string.error_parser);
            //TODO
        }
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    private void funProfilePic(final String picType) {

        navg_progress.setMessage("Please wait loading!");
        navg_progress.setCanceledOnTouchOutside(false);
        navg_progress.show();
        String url = BASE_URL + URLEndPoints.getProfilepic_URL(student_id);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    JSONObject responseObj = new JSONObject(response);
                    Gson gson = new Gson();
                    profilePicModel = gson.fromJson(response, ProfilePicModel.class);

                    if (profilePicModel.getStatus() == 1) {

                        navg_progress.dismiss();
                        profilePicArrayList = (ArrayList<ProfilePicModel.StudentIMGBean>) profilePicModel.getStudentIMG();
                        if (profilePicArrayList != null && profilePicArrayList.size() != 0) {

                            if (profilePicArrayList.get(0).getSTU_EMP_IMG() != null && !profilePicArrayList.get(0).getSTU_EMP_IMG().isEmpty()) {
                                String imageString = profilePicArrayList.get(0).getSTU_EMP_IMG();

                                if (picType.equals("navigationPic")) {
                                    byte[] imageAsBytes = Base64.decode(imageString.getBytes(), Base64.DEFAULT);
                                    //imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
                                } else if (picType.equals("profilePic")) {
                                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                    intent.putExtra("imageString", imageString);
                                    startActivity(intent);
                                    getActivity().finish();
                                }

                            } else {
                                if (picType.equals("navigationPic")) {
                                    //encode image to base64 string
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.studicon);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                    byte[] imageBytes = baos.toByteArray();
                                    String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                                    //decode base64 string to image
                                    imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                   // imageView.setImageBitmap(decodedImage);

                                } else if (picType.equals("profilePic")) {
                                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                    intent.putExtra("imageString", "no_image");
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }
                        }else{

                            Snackbar.make(lytMainMenu, "Record not found.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (profileModel.getStatus() == 0) {
                        navg_progress.dismiss();
                        Snackbar.make(lytMainMenu, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error: " + e.getMessage());

                    Snackbar.make(lytMainMenu, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    navg_progress.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                lytMainMenu.setVisibility(View.GONE);
                navg_progress.hide();
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
