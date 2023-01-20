package com.wordpro.studentproject.network;

import com.wordpro.studentproject.model.TeachingPlanData;
import com.wordpro.studentproject.model.Teachingplan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("api/Student/GetActionfacultyDetails?Session_Id=29&main_Semester_Type=E&EMPLOYEE_ID=0&Subject_Detail_Id=0&BRANCH_STANDARD_ID=14&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=JDIET_SC&STU_BATCH_DET_ID=0&applid=1")
    Call<List<Teachingplan>> getAllPhotos();
}
