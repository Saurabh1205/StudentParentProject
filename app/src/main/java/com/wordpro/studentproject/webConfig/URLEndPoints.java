package com.wordpro.studentproject.webConfig;

import com.wordpro.studentproject.model.DynamicArrayList;
import com.wordpro.studentproject.model.DynamicNoticArray;
import com.wordpro.studentproject.model.FineModel;
import com.wordpro.studentproject.model.NoticPattenChaild;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.StudTimeTableModel;

import java.util.ArrayList;
import java.util.List;

import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranch_Standard_GRP_ID;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studDepartmentNumber;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.stud_main_semester_mst_id;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;

public class URLEndPoints {
    public static final String PendingJonDtls_URL = "PendingJonDtls";
    public static final String EMPLOYEE_MAIN_LOGIN_URL = "Employee_Main_Login/Main_Login";
    public static final String PendingJon_URL = "PendingJon";
    public static final String PenCategory_URL = "PenCategory";
    public static final String RESEND_OTP_URL = "Employee_Main_Login_Resend_Otp/Resend_Otp";
    public static final String GETDAYWISEATTEENDACE_URL = "Student/GetSelfAttendanceDayWise";
    public static final String SubDataInsert_URL = "SubDataInsert";
    public static final String PostDownloadCopyTo_URL = "PostDownloadCopyTo";
    public static final String BOOK_SEARCHING_URL ="Library/GetBookSearch";
    public static final String SAVE_BOOK_SUGGESTION_URL = "SaveBookSuggestion/Save";
    public static final String GetSubjectWiseFaculty_URL = "Student/GetSubjectWiseFaculty";
    public static final String GetNetSelfAttendance_URL = "Student/GetNetSelfAttendance";
    public static final String GetLibBokNewArrAbst_URL = "Library/GetLibBokNewArrAbst?PI_CENTRE_CODE=";
    public static final String AssStudSubmit_URL = "AssStudSubmit";
    public static final String GetUploadFileP_URL = "GetUploadFileP";
    public static final String GetStudentDetails_URL = "GetStudentDetails";
    public static final String Getnoticedata_URL = "Getnoticedata";
    public static final String PostDownloadCopyTo = "PostDownloadCopyTo";
    public static final String POSTGetNotice_Details ="Get_notice_details";
    public static final String POSTSAVEDOCUMENT ="SaveDwallDocument/PostData";

    public static final String getProfilepic_URL(String student_id){
        String url = "Student/GetStudentPhoto?PI_PERSON_TYPE=S&PI_PERSON_ID=" + student_id + "&PI_STU_EMP_IMG_TYPE=P";
        return url;
    }
   // get Upload path
    public static final String getUpload_Doc_URL(String studCenterCode ){
        String url = "Employee/GetUploadFileP?P_DOCUMENT_SHORT_CODE=ASG&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode;
        return url;
    }
    public static final String getUpload_Doc_URL_DWallet(String studCenterCode ){
        String url = "Employee/GetUploadFileP?P_DOCUMENT_SHORT_CODE=DIGITALDOCUMENT&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode;
        return url;
    }
    public static final String  getProfileDetails(String student_id, String studCenterCode){
        String url = "Student/GetProfile?P_STUDENT_ID=" + student_id + "&P_CENTRE_CODE=" + studCenterCode;
        return url;
    }

    public static final String getNoticesURL(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId){
        String url =  "NoticeDownloadMultiApi?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA&PI_NOTICE_TYPE=HOME&PI_NOTICE_SPAN=Y";

        return url;
    }

    // new api 5-08-2020 (create Harshal)

    public static final String getNoticesAllURL(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId,String TopFilter){
        String url =  "NoticeDownloadMultiApiNew?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA&TOP_FILTER="+TopFilter;

        return url;
    }
    public static final String AlertMultiApiAllURLThreeList(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId,String TopFilter){
        String url =  "AlertMultiApi?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA&TOP_FILTER="+TopFilter;

        return url;
    }
    // create api  for new design harshal date -11-08-2020
    public static final String AlertMultiApThreeList(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId,String TopFilter){
        String url =  "AlertMultiApi?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA&TOP_FILTER="+TopFilter
                +"&PI_OS=ANDROID&PI_PAGE_MODE=HOME"
                +"&PI_NOTC_GROUP_MASTER_ID=0"+"&PI_NOTICE_PATTERN=null"
                +"&PI_SEARCH=null"+"&PI_PAGE_NO=1";

        return url;
    }

    public static final String AlertMultiApThreeListViewAll(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId,String TopFilter,String NotificationPatten,int PageView,String SearchFilter,int FilterSID){
        String url =  "AlertMultiApi?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA&TOP_FILTER="+TopFilter
                +"&PI_OS=ANDROID&PI_PAGE_MODE=VIEW_ALL"
                +"&PI_NOTC_GROUP_MASTER_ID="+FilterSID
                +"&PI_NOTICE_PATTERN="+NotificationPatten
                +"&PI_SEARCH="+SearchFilter
                +"&PI_PAGE_NO="+PageView;

        return url;
    }

    public static final String AlertGroupFilterList(String studCenterCode,String studDepartmentNumber, String student_id, String studBranchStandardId,String NotificationPatten){
        String url =  "AlertGroupFilter?PI_CENTRE_CODE=" + studCenterCode
                + "&PI_DEPARTMENT_NUMBER=" + studDepartmentNumber
                + "&PI_JOB_PROFILE_ID=0&PI_PERSON_ID=" + student_id
                + "&PI_PERSON_TYPE=S&PI_BRANCH_STANDARD_ID="+studBranchStandardId
                +"&PI_WORK_DESIG_CODE=NA"
                +"&PI_NOTICE_PATTERN="+NotificationPatten;
        return url;
    }


    public static final String getStudentDetaiURL(String userId){
        String url =  "Student/GetDetails?User_Type=STUDENT&User_Id=" + userId;
        return url;
    }

    public static final String getMenuList_URL(String centre_code){
        String url = "GetStudentMenuList/GetData?p_Module_Code=STUAPPANRD&p_Centre_Code="+centre_code+"&p_User_Type=STUDENT";
        return url;
    }

    public static final String getSubjectList_URL(String studSessionId, String semester_type_code, String studBranchStandardId){

        String url = "GetSubjectListSectionWise/GetData?PI_SESSION_ID=" + studSessionId + "&AcadSemisterType=" + semester_type_code + "&P_BRANCH_STANDARD_ID=" + studBranchStandardId;
        return url;
    }

    public static final String getStudNoteDetails_URL(String studBranch_Standard_GRP_ID, String studDepartmentNumber, String semester_type_code, String studCenterCode, String student_id){
        String url = "Student/GetStudNotesDtls?P_BR_STD_GRP_ID=" + studBranch_Standard_GRP_ID + "&P_DEPT_NUMBER=" + studDepartmentNumber + "&P_AcadSemisterType=" + semester_type_code + "&P_CENTRE_CODE=" + studCenterCode + "&P_Student_ID=" + student_id;
        return url;
    }

    public static final String getIssuedBookData_URL(String fromDate,String currentDt, String student_id){
        String url = "Library/GetBookCirculationReg?PI_LIB_LIBRARY_MST_ID=1&PI_MEMBER_TYPE=EMPLOYEE&PI_From_date="+fromDate+"&PI_Upto_date="+currentDt+"&PI_REPORT_TYPE=CIRCULATION_REGISTER&PI_Book_Ret_Status=ALL&pi_ISSU_REISSU_RET=ALL&PI_Member_Id="+student_id; //1032151366
        return url;
    }



    public static final String getNewArrivalsDetails_URL(String studCenterCode, String date1, String date2){

        String url = "Library/GetLibBokNewArrDtls?PI_CENTRE_CODE="+studCenterCode+"&PI_From_date="+date1+"&PI_Upto_date="+date2;
        return url;
    }

    public static final String GetLateChargeDtls_URL(String studCenterCode){

        String url = "Library/GetLateChargeDtls?PI_Cente_Code="+studCenterCode+"&PI_SLAB_MASTER_ID=0";
        return url;
    }

    public static final String getTeachingPlanDataURL(String studSessionId, String studSemesterType, String employee_id, String subject_detail_id, String studBranchStandardId, String studCenterCode){

        String url = "Student/GetActionPlanSyllabusCov?Session_Id="+studSessionId+"&main_Semester_Type="+studSemesterType+"&EMPLOYEE_ID="+employee_id+"&Subject_Detail_Id="+subject_detail_id+"&BRANCH_STANDARD_ID="+studBranchStandardId+"&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code="+studCenterCode+"&StrBatchId=0&StrAPPId=1";
        return url;
    }
    public static final String newgetTeachingPlanDataURL(String studSessionId, String studSemesterType, String employee_id, String subject_detail_id, String studBranchStandardId, String studCenterCode,String stu_Branch_del_Id,String appliedId){

        String url = "Student/GetActionPlanSyllabusCov?Session_Id="+studSessionId+"&main_Semester_Type="+studSemesterType+"&EMPLOYEE_ID="+employee_id+"&Subject_Detail_Id="+subject_detail_id+"&BRANCH_STANDARD_ID="+studBranchStandardId+"&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code="+studCenterCode+"&StrBatchId="+
                stu_Branch_del_Id + "&StrAPPId="+appliedId;
        return url;

    }
    public static final String getUniversitySyllabus_URL(String subject_group_id, String applicable_number){
        String url = "Student/GetUniversitySyllabusSubjectWise?SUBJECT_GROUP_ID="+subject_group_id+"&_iPI_APPLICABLE_NUMBER="+applicable_number;
        return url;
    }
    public static final String getNewUniversitySyllabus_URL(String subject_group_id, String applicable_number){
        String url = "Student/NewUniversitySyllabusSubjectWise?SUBJECT_GROUP_ID="+subject_group_id+"&_iPI_APPLICABLE_NUMBER="+applicable_number;
        return url;
    }
    public static final String getPeriodDates_URL(String studBranchStandardId, String stud_main_semester_mst_id, String studSessionId){

        String url = "GetPeriodDates?P_BRANCH_STANDARD_ID=" + studBranchStandardId + "&MAINSEMESTERMSTID=" + stud_main_semester_mst_id + "&PI_SESSION_ID=" + studSessionId;
        return url;
    }

    public static final String getNOTES_Details_URL(String studSessionId,String studCenterCode){

        String url = "Student/GetNotesDtls?P_SEMESTER_IDENTITY=BISEMESTER&P_AcadsessionId=" + studSessionId + "&P_CENTRE_CODE=" + studCenterCode;
        return url;
    }

    public static final String GetStudAssignment_URL(String student_id,String studSessionId, String studBranchStandardId, String studSemesterType, String studCenterCode){
        String url = "Student/GetStudAssignment?P_STUDENT_ID=" + student_id + "&P_AcadsessionId=" + studSessionId + "&P_BRANCH_STANDARD_ID=" + studBranchStandardId + "&P_AcadSemisterType=" + studSemesterType + "&P_CENTRE_CODE=" + studCenterCode + "&P_REPORT_PATTERN=ALL";
        return url;
    }

    public static final  String GetStudDtls_URL(String student_id, String feeType){
        String url = "Student/GetStudDtls?P_STUDENT_ID=" + student_id + "&FeeType=" + feeType;
        return url;
    }



    public static final String GetStudentExtraLecture_URL(String studCenterCode, String studSessionId, String studBranchStandardId, String studSemesterType, String student_id){
        String url = "Student/GetStudentExtraLecture?_centercode=" + studCenterCode + "&PI_SESSION_ID=" + studSessionId + "&P_BRANCH_STANDARD_ID=" + studBranchStandardId + "&AcadSemisterType=" + studSemesterType + "&p_Subject_Detail_Id=0&_iPI_APPLICABLE_NUMBER=0&P_STUDENT_ID=" + student_id;
        return url;
    }

    public static final String GetSelfTimetable_URL(String studSessionId, String studSemesterType, String studBranchStandardId, String studCenterCode){
        String url = "Student/GetSelfTimetable?SessionId=" + studSessionId + "&SemesterType=" + studSemesterType + "&BranchStdId=" + studBranchStandardId + "&DeptNo=0&EmpId=0&BuldingRoomId=0&RptPattern=COURSEYEARWISE&CentreCode=" + studCenterCode;
        return url;
    }
    public static final String newGetSelfTimetable_URL(String studSessionId, String studSemesterType, String studBranchStandardId, String studCenterCode,String studId){
        String url = "Student/GetSelfTimetable?SessionId=" + studSessionId + "&SemesterType=" + studSemesterType + "&BranchStdId=" + studBranchStandardId + "&DeptNo=0&EmpId=0&BuldingRoomId=0&RptPattern=STUDENTWISE&CentreCode=" + studCenterCode+"&student_id=" + studId;
        return url;
    }
    public static final String GetActionfacultyDetails_URL(String studSessionId, String studSemesterType, String studBranchStandardId, String studCenterCode){
        String url = "Student/GetActionfacultyDetails?Session_Id=" + studSessionId + "&main_Semester_Type=" + studSemesterType + "&EMPLOYEE_ID=0&Subject_Detail_Id=0&BRANCH_STANDARD_ID=" + studBranchStandardId + "&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=" + studCenterCode+"&STU_BATCH_DET_ID=0&applid=1";
        return url;
    }

    public static final  String GetUniversitySyllabusList_URL(String studSessionId, String studBranch_Standard_GRP_ID, String studSemesterType){
        String url = "Student/GetUniversitySyllabusList?PI_SESSION_ID=" + studSessionId + "&BRANCH_STANDARD_GRP_ID=" + studBranch_Standard_GRP_ID + "&MAINSEMESTERTYPE=" + studSemesterType;
        return url;
    }

    public static final  String NewGetUniversitySyllabusList_URL(String StudentBranchID,String CenterCode,String  studSessionId, String StudentID, String studSemesterType){
        String url = "Student/GetUniversitySyllabusList?BRANCH_STANDARD_ID=" + StudentBranchID + "&CENTRE_CODE=" + CenterCode + "&SESSION_ID=" + studSessionId +
                 "&STUDENT_ID=" + StudentID +  "&MAINSEMESTERTYPE=" + studSemesterType;
        return url;

    }
    public static final String GetSyllabusComplStatus_URL(String studSessionId, String studSemesterType, String studBranchStandardId, String studCenterCode){
        String url = "Student/GetSyllabusComplStatus?PI_SESSION_ID=" + studSessionId + "&AcadSemisterType=" + studSemesterType + "&_EmployeeId=0&p_Subject_Detail_Id=0&P_BRANCH_STANDARD_ID=" + studBranchStandardId + "&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=" + studCenterCode;
        return url;
    }
    public static final String newGetSyllabusComplStatus_URL(String studSessionId, String studSemesterType, String EmpId,String  SubDetailId,String studBranchStandardId, String studCenterCode) {
        String url = "Student/GetSyllabusComplStatus?PI_SESSION_ID=" + studSessionId + "&AcadSemisterType=" + studSemesterType + "&_EmployeeId=" + EmpId + "&p_Subject_Detail_Id=" + SubDetailId + "&P_BRANCH_STANDARD_ID=" + studBranchStandardId + "&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=" + studCenterCode;
        return url;
    }

        public static  final String GetCirculationRule_URL(String studCenterCode){
        String url = "Library/GetCirculationRule?PI_Cente_Code="+studCenterCode+"&PI_LIBRARY_MST_ID=4&PI_MEMBER_TYPE=STUDENT";
        return url;
    }

    public static final String GetLibBokReserStatus_URL(String student_id, String studSessionId, String studSemesterType){
        String url = "Library/GetLibBokReserStatus?PI_PERSON_ID="+student_id+"&PI_PERSON_TYPE=STUDENT&PI_SESSION_ID="+studSessionId+"&PI_SEMESTER_TYPE="+studSemesterType;
        return url;
    }

    public static final String GetLibFineDetails_URL(String student_id){
        String url = "Library/GetLibFineDetails?PI_LIBRARY_MST_ID=4&PI_Member_Type=STUDENT&PI_Member_Id="+student_id+"&PI_Acc_Session_Id=20";
        return  url;
    }
 //http://103.225.174.30:82/api/Library/GetLibFineDetails?PI_LIBRARY_MST_ID=4&PI_Member_Type=EMPLOYEE&PI_Member_Id=1039180120&PI_Acc_Session_Id=30

    public static final  String GetStudFees_URL(String student_id, String feeType){
        String url = "Student/GetStudFeeDetails?P_PERSON_ID=" + student_id + "&P_TYPE=" + feeType;
        return url;
    }

    public static String GetCirculationRule_url(String empCenterCode, String MainDesinationIDtoSend){
        String url = "Library/GetCirculationRule?PI_Cente_Code=" + empCenterCode + "&PI_LIBRARY_MST_ID=1&PI_MEMBER_TYPE=STUDENT&BRANCH_STANDARD_DESIG_ID="+MainDesinationIDtoSend;
        return url;
    }

    public static String GetDocumentListURL(String profileId,String userType, String userId){
        String URL = "Dwallet/GetDocumentList?PI_JOB_PROFILE_ID="+profileId+"&PI_USER_TYPE="+userType+"&PI_USER_ID="+userId;
        return URL;
    }

    public static String GetDocumentdetailsURL(String DocumentMST_ID,String userId){
        String URL ="Dwallet/GetSubmittedDocList?PI_DOCUMENT_MST_ID="+DocumentMST_ID+"&PI_EMPLOYEE_ID=0&PI_DOCUMENT_SUBMITTED_ID=0&PI_USER_TYPE=S&PI_STUDENT_ID="+userId;
        return URL;
    }


    //Emp app  d-  wallet  api  use

    public static String getEmployeeDocumentURL(String employeeId,String empDeptNo, String empCenterCode){
        String URL = "Employee/GetDownloadDoc?PI_EMPLOYEE_ID="+employeeId+"&PI_DEPARTMENT_STRING="+empDeptNo+"&PI_CENTRE_CODE="+empCenterCode;
        return URL;
    }


    //http://192.168.10.72:500/api/Dwallet/GetDocumentList?PI_JOB_PROFILE_ID=0&PI_USER_TYPE='S'&PI_USER_ID=1032191158
    public static final String GetLibBokNewArrAbst = "Library/GetLibBokNewArrAbst?PI_CENTRE_CODE=";


    public static  String Constance_StudentID;
    public static  String Constance_StudentCenterCode;
    public static  String Constance_StudentDepartmentNumber;
    public static  String Constance_StudBranchStandardId;
    public static  String Constance_StudSemesterType;
    public static  String Constance_StudSessionId;
    public static  String Constance_InfoVale;
    public static  String Constance_AlertVale;
    public static  String Constance_ApprovalVale;
    public static  String Constance_WARNINGVale;
    public static  String Constance_WellDoneVale;
    public static  String Constance_stud_main_semester_mst_id;
    public static  String Constance_studBranch_Standard_GRP_ID;
    public static List<NoticPattenParents> ConstanceNoticPattenParents;
    public static  String Constance_SUB_ID;
    public static  String Constance_SUB_NAME;
    public static  String Constance_SUB_SHORT;
    public static  String Constance_SELECTSTART_DATE;
    public static  String Constance_SELECTEND_DATE;
    public static List<NoticPattenChaild> ConstanceNoticPattenChaild;
    public static ArrayList<NoticesModel.DataBean> ConstanceArrayNews = new ArrayList<NoticesModel.DataBean>();
    public static List<NoticPattenChaild> ConstanceNoticPattenChaildNOTES;
    public static  String ConstanceGroupId,ConstanceGroupName;
    public static  String ConstanceSTAETDATE,ConstanceENDDATE;
    public static  String ConstanceSUB_GROUPID,ConstanceSUBAPPLICABLE_NO,ConstanceSUB_DES,ConstanceSUB_TH,ConstanceTeacherNa;
    public static  String ConstanceSUB_EMPID,ConstanceSUB_profName,ConstanceSUBJECT_DETAIL_ID,ConstanceSUB_NAME;
    public static  String Constance_EMPId,Constance_Sub_Details_ID,Constance_Sub_Batch_Des_ID;
    public static String Constance_JOB_CODE,Constance_Work,Constance_NoOFJob,Constance_JobName;
    public static String Constance_InstituteCode,Constance_stander_code;
    public static  ArrayList<FineModel.BookFineDetailsBean> ConstancebookFineDetailsArrayList = new ArrayList<>();

    public static ArrayList<StudTimeTableModel.DataBean> ConstanceTimeTableList;
    public static ArrayList<String> ConstanceData =new ArrayList<>();
    public static String ConstanceLoginUser;



}
