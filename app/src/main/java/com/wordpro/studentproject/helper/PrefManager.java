package com.wordpro.studentproject.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.wordpro.studentproject.activities.LoginActivity;
import com.wordpro.studentproject.app.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wccs1980 on 07-04-2018.
 */

public class PrefManager {

    private static String TAG = PrefManager.class.getSimpleName();
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;

    private static final String KEY_FIREBASE_TOKEN="FirebaseRegisteredID";


    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "Wordpro";

    // All Shared Preferences Keys
    private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_PIN="security_pin";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_LOGINID = "loginId";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_URL_INSTITUTEWISE="URLInstituteWise";

    //Student Details
    private static final String KEY_ACAD_CURRENT_SESSION_FLAG="ACAD_CURRENT_SESSION_FLAG";
    private static final String KEY_ACAD_SESSION_ID="ACAD_SESSION_ID";
    private static final String KEY_ACAD_SESS_NAME="ACAD_SESS_NAME";
    private static final String KEY_ADMISSION_ACTIVE_FLAG="ADMISSION_ACTIVE_FLAG";
    private static final String KEY_BRANCH_ID="BRANCH_ID";
    private static final String KEY_BRANCH_SEMESTER_MST_ID="BRANCH_SEMESTER_MST_ID";
    private static final String KEY_BRANCH_SEMESTER_NAME="BRANCH_SEMESTER_NAME";
    private static final String KEY_BRANCH_STANDARD_CODE="BRANCH_STANDARD_CODE";
    private static final String KEY_BRANCH_STANDARD_DESCRIPTION="BRANCH_STANDARD_DESCRIPTION";
    private static final String KEY_BRANCH_STANDARD_GRP_ID="BRANCH_STANDARD_GRP_ID";
    private static final String KEY_BRANCH_STANDARD_ID="BRANCH_STANDARD_ID";
    private static final String KEY_CENTRE_CODE="CENTRE_CODE";
    private static final String KEY_CENTRE_GROUP_CODE="CENTRE_GROUP_CODE";
    private static final String KEY_DEPARTMENT_NUMBER="DEPARTMENT_NUMBER";
    private static final String KEY_MAIN_SEMESTER_MST_ID="MAIN_SEMESTER_MST_ID";
    private static final String KEY_MAIN_SEMESTER_NAME="MAIN_SEMESTER_NAME";
    private static final String KEY_MAIN_SEMESTER_TYPE="MAIN_SEMESTER_TYPE";
    private static final String KEY_STANDARD_ID="STANDARD_ID";
    private static final String KEY_STREAM_ID="STREAM_ID";
    private static final String KEY_STUDENT_CODE="STUDENT_CODE";
    private static final String KEY_STUDENT_EMAIL_ID="STUDENT_EMAIL_ID";
    private static final String KEY_STUDENT_ID="STUDENT_ID";
    private static final String KEY_YEARLY_ROLL_NUMBER="YEARLY_ROLL_NUMBER";


    //Student Personal Details
    private static final String KEY_DATE_OF_BIRTH="DATE_OF_BIRTH";
    private static final String KEY_FATHER_NAME="FATHER_NAME";
    private static final String KEY_FIRST_NAME="FIRST_NAME";
    private static final String KEY_GENDER="GENDER";
    private static final String KEY_GUARDIAN_MOBILE_NO="GUARDIAN_MOBILE_NO";
    private static final String KEY_LAST_NAME="LAST_NAME";
    private static final String KEY_MIDDLE_NAME="MIDDLE_NAME";
    private static final String KEY_NICK_NAME="NICK_NAME";
    private static final String KEY_PARENT_MOBILE_NO="PARENT_MOBILE_NO";
    private static final String KEY_STUDENT_EMAIL_ID_P="STUDENT_EMAIL_ID_P";
    private static final String KEY_STUDENT_ID_P="STUDENT_ID_P";
    private static final String KEY_STUDENT_MOBILE_NO="STUDENT_MOBILE_NO";
    private static final String KEY_STUD_FNAMEWISE="STUD_FNAMEWISE";
    private static final String KEY_STUD_FN_MN_SHORT="STUD_FN_MN_SHORT";
    private static final String KEY_STUD_FULL_NAME="STUD_FULL_NAME";
    private static final String KEY_STUD_INITIAL="STUD_INITIAL";
    private static final String KEY_STUD_LNAMEWISE="STUD_LNAMEWISE";
    private static final String KEY_STUD_ML_NAME_SHORT="STUD_ML_NAME_SHORT";
    private static final String KEY_TITLE="TITLE";

//Menu ArrayList

    public static final String KEY_MENU_ARRAYLIST="menuArrayList";
    public static final String KEY_MENU="MenuList";
    public static final String KEY_SUB_OTHER_MENU="SubOtherMenuList";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLogin(String loginId, String password, String mobile) {
        editor.putString(KEY_LOGINID, loginId);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_MOBILE, mobile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put("loginId", pref.getString(KEY_LOGINID, null));
        profile.put("password", pref.getString(KEY_PASSWORD, null));
        profile.put("mobile", pref.getString(KEY_MOBILE, null));
        return profile;
    }

    public void setURL(String finalInstituteCode) {

        editor.putString(KEY_URL_INSTITUTEWISE,finalInstituteCode);
        editor.commit();
    }

    public String getURL(){

        return pref.getString(KEY_URL_INSTITUTEWISE, null);
    }

    public void setPin(String pin) {
        editor.putString(KEY_PIN, pin);
        editor.commit();
    }

    public String getPin() {

        return pref.getString(KEY_PIN, null);
    }

    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public String getMobileNumber() {

        return pref.getString(KEY_MOBILE_NUMBER, null);
    }
    public String getString(String key, String defaultValue){
        return pref.getString(key, defaultValue);
    }

    public void setStudentDetail(String acad_current_session_flag, String acad_session_id, String acad_sess_name, String admission_active_flag, String branch_id, String branch_semester_mst_id, String branch_semester_name, String branch_standard_code, String branch_standard_description, String branch_standard_grp_id, String branch_standard_id, String centre_code, String centre_group_code, String department_number, String main_semester_mst_id, String main_semester_name, String main_semester_type, String standard_id, String stream_id, String student_code, String student_email_id, String student_id, String yearly_roll_number) {
        editor.putString(KEY_ACAD_CURRENT_SESSION_FLAG, acad_current_session_flag);
        editor.putString(KEY_ACAD_SESSION_ID, acad_session_id);
        editor.putString(KEY_ACAD_SESS_NAME, acad_sess_name);
        editor.putString(KEY_ADMISSION_ACTIVE_FLAG, admission_active_flag);
        editor.putString(KEY_BRANCH_ID, branch_id);
        editor.putString(KEY_BRANCH_SEMESTER_MST_ID, branch_semester_mst_id);
        editor.putString(KEY_BRANCH_SEMESTER_NAME, branch_semester_name);
        editor.putString(KEY_BRANCH_STANDARD_CODE, branch_standard_code);
        editor.putString(KEY_BRANCH_STANDARD_DESCRIPTION, branch_standard_description);
        editor.putString(KEY_BRANCH_STANDARD_GRP_ID, branch_standard_grp_id);
        editor.putString(KEY_BRANCH_STANDARD_ID, branch_standard_id);
        editor.putString(KEY_CENTRE_CODE, centre_code);
        editor.putString(KEY_CENTRE_GROUP_CODE,centre_group_code);
        editor.putString(KEY_DEPARTMENT_NUMBER, department_number);
        editor.putString(KEY_MAIN_SEMESTER_MST_ID, main_semester_mst_id);
        editor.putString(KEY_MAIN_SEMESTER_NAME, main_semester_name);
        editor.putString(KEY_MAIN_SEMESTER_TYPE, main_semester_type);
        editor.putString(KEY_STANDARD_ID, standard_id);
        editor.putString(KEY_STREAM_ID,stream_id);
        editor.putString(KEY_STUDENT_CODE, student_code);
        editor.putString(KEY_STUDENT_EMAIL_ID, student_email_id);
        editor.putString(KEY_STUDENT_ID, student_id);
        editor.putString(KEY_YEARLY_ROLL_NUMBER,yearly_roll_number);


        editor.commit();
    }

    public HashMap<String, String> getStudentDetails() {
        HashMap<String, String> studentDetails = new HashMap<>();
        studentDetails.put("acad_current_session_flag", pref.getString(KEY_ACAD_CURRENT_SESSION_FLAG, null));
        studentDetails.put("acad_session_id", pref.getString(KEY_ACAD_SESSION_ID, null));
        studentDetails.put("acad_sess_name", pref.getString(KEY_ACAD_SESS_NAME, null));
        studentDetails.put("admission_active_flag", pref.getString(KEY_ADMISSION_ACTIVE_FLAG, null));
        studentDetails.put("branch_id", pref.getString(KEY_BRANCH_ID, null));
        studentDetails.put("branch_semester_mst_id", pref.getString(KEY_BRANCH_SEMESTER_MST_ID, null));
        studentDetails.put("branch_semester_name", pref.getString(KEY_BRANCH_SEMESTER_NAME, null));
        studentDetails.put("branch_standard_code", pref.getString(KEY_BRANCH_STANDARD_CODE, null));
        studentDetails.put("branch_standard_description", pref.getString(KEY_BRANCH_STANDARD_DESCRIPTION, null));
        studentDetails.put("branch_standard_grp_id", pref.getString(KEY_BRANCH_STANDARD_GRP_ID, null));
        studentDetails.put("branch_standard_id", pref.getString(KEY_BRANCH_STANDARD_ID, null));
        studentDetails.put("centre_code",pref.getString(KEY_CENTRE_CODE,null));
        studentDetails.put("centre_group_code", pref.getString(KEY_CENTRE_GROUP_CODE, null));
        studentDetails.put("department_number", pref.getString(KEY_DEPARTMENT_NUMBER, null));
        studentDetails.put("main_semester_mst_id", pref.getString(KEY_MAIN_SEMESTER_MST_ID, null));
        studentDetails.put("main_semester_name", pref.getString(KEY_MAIN_SEMESTER_NAME, null));
        studentDetails.put("main_semester_type", pref.getString(KEY_MAIN_SEMESTER_TYPE, null));
        studentDetails.put("standard_id", pref.getString(KEY_STANDARD_ID, null));
        studentDetails.put("stream_id", pref.getString(KEY_STREAM_ID, null));
        studentDetails.put("student_code", pref.getString(KEY_STUDENT_CODE, null));
        studentDetails.put("student_email_id",pref.getString(KEY_STUDENT_EMAIL_ID,null));
        studentDetails.put("student_id", pref.getString(KEY_STUDENT_ID, null));
        studentDetails.put("yearly_roll_number",pref.getString(KEY_YEARLY_ROLL_NUMBER,null));


        return studentDetails;
    }


    public void setStudPersonalDetails(String date_of_birth, String father_name, String first_name, String gender, String guardian_mobile_no, String last_name, String middle_name, String nick_name, String parent_mobile_no, String student_email_id_p, String student_id_p, String student_mobile_no, String stud_fnamewise, String stud_fn_mn_short, String stud_full_name, String stud_initial, String stud_lnamewise, String stud_ml_name_short, String title) {

        editor.putString(KEY_DATE_OF_BIRTH, date_of_birth);
        editor.putString(KEY_FATHER_NAME, father_name);
        editor.putString(KEY_FIRST_NAME, first_name);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_GUARDIAN_MOBILE_NO, guardian_mobile_no);
        editor.putString(KEY_LAST_NAME, last_name);
        editor.putString(KEY_MIDDLE_NAME, middle_name);
        editor.putString(KEY_NICK_NAME, nick_name);
        editor.putString(KEY_PARENT_MOBILE_NO, parent_mobile_no);
        editor.putString(KEY_STUDENT_EMAIL_ID_P, student_email_id_p);
        editor.putString(KEY_STUDENT_ID_P, student_id_p);
        editor.putString(KEY_STUDENT_MOBILE_NO, student_mobile_no);
        editor.putString(KEY_STUD_FNAMEWISE,stud_fnamewise);
        editor.putString(KEY_STUD_FN_MN_SHORT, stud_fn_mn_short);
        editor.putString(KEY_STUD_FULL_NAME, stud_full_name);
        editor.putString(KEY_STUD_INITIAL, stud_initial);
        editor.putString(KEY_STUD_LNAMEWISE, stud_lnamewise);
        editor.putString(KEY_STUD_ML_NAME_SHORT, stud_ml_name_short);
        editor.putString(KEY_TITLE,title);

        editor.commit();
    }

    public HashMap<String, String> getStudentPersonalDetails() {
        HashMap<String, String> studentPersonalDetails = new HashMap<>();
        studentPersonalDetails.put("date_of_birth", pref.getString(KEY_DATE_OF_BIRTH, null));
        studentPersonalDetails.put("father_name", pref.getString(KEY_FATHER_NAME, null));
        studentPersonalDetails.put("first_name", pref.getString(KEY_FIRST_NAME, null));
        studentPersonalDetails.put("gender", pref.getString(KEY_GENDER, null));
        studentPersonalDetails.put("guardian_mobile_no", pref.getString(KEY_GUARDIAN_MOBILE_NO, null));
        studentPersonalDetails.put("last_name", pref.getString(KEY_LAST_NAME, null));
        studentPersonalDetails.put("middle_name", pref.getString(KEY_MIDDLE_NAME, null));
        studentPersonalDetails.put("nick_name", pref.getString(KEY_NICK_NAME, null));
        studentPersonalDetails.put("parent_mobile_no", pref.getString(KEY_PARENT_MOBILE_NO, null));
        studentPersonalDetails.put("student_email_id_p", pref.getString(KEY_STUDENT_EMAIL_ID_P, null));
        studentPersonalDetails.put("student_id_p", pref.getString(KEY_STUDENT_ID_P, null));
        studentPersonalDetails.put("student_mobile_no",pref.getString(KEY_STUDENT_MOBILE_NO,null));
        studentPersonalDetails.put("stud_fnamewise", pref.getString(KEY_STUD_FNAMEWISE, null));
        studentPersonalDetails.put("stud_fn_mn_short", pref.getString(KEY_STUD_FN_MN_SHORT, null));
        studentPersonalDetails.put("stud_full_name", pref.getString(KEY_STUD_FULL_NAME, null));
        studentPersonalDetails.put("stud_initial", pref.getString(KEY_STUD_INITIAL, null));
        studentPersonalDetails.put("stud_lnamewise", pref.getString(KEY_STUD_LNAMEWISE, null));
        studentPersonalDetails.put("stud_ml_name_short", pref.getString(KEY_STUD_ML_NAME_SHORT, null));
        studentPersonalDetails.put("title", pref.getString(KEY_TITLE, null));
        return studentPersonalDetails;
    }

    public void setFirebaseRegisteredUserId(String refreshedToken) {

        editor.putString(KEY_FIREBASE_TOKEN,refreshedToken);
        editor.commit();
    }

    public String getFirebaseRegisteredUserId(){

        return pref.getString(KEY_FIREBASE_TOKEN, null);
    }

    public void setMenuArrayList(Set<String> set) {

        editor.putStringSet(KEY_MENU_ARRAYLIST,set);
        editor.commit();

    }

    public Set<String> getMenuArrayList(){
        Set<String> set =new HashSet<String>();
        set= pref.getStringSet(KEY_MENU_ARRAYLIST, null);

        return set;
    }



    public void setSubMenuArray(ArrayList<ArrayList<String>> subMenuGroup) {

        for(int i=0;i<subMenuGroup.size();i++){

            ArrayList<String> submenu=new ArrayList<>();
            submenu = subMenuGroup.get(i);

            Log.d(TAG, String.valueOf(submenu));

        }
    }


    public void setMenuStringBuilder(String allMenu) {

        editor.putString(KEY_MENU,allMenu);
        editor.commit();

    }


    public String getMenuStringBuilder() {

        return pref.getString(KEY_MENU, null);
    }

    public void setSubOtherMenu(String allSubOtherMenu) {

        Log.d(TAG, String.valueOf(allSubOtherMenu));

        editor.putString(KEY_SUB_OTHER_MENU,allSubOtherMenu);
        editor.commit();
    }

    public String getSubOtherMenu(){
        return pref.getString(KEY_SUB_OTHER_MENU, null);
    }

}
