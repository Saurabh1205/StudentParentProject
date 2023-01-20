package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 06-06-2018.
 */

public class ProfileModel {

    private int status;
    private List<StudentDtlsBean> Student_Dtls;
    private List<AddressDtlsBean> Address_Dtls;
    private List<QualificationDtlsBean> Qualification_Dtls;
    private List<?> College_Dtls;
    private List<?> SSCSubject_Dtls;
    private List<?> Industral_Dtls;
    private List<?> Seminar_Dtls;
    private List<?> WorkShop_Dtls;
    private List<?> WorkActivity_Dtls;
    private List<?> Employemt_Dtls;
    private List<ParentDtlsBean> Parent_Dtls;
    private List<?> Guardian_Dtls;
    private List<DocumnetDtlsBean> Documnet_Dtls;
    private List<?> Payment_Dtls;
    private List<?> PatmentDate_Dtls;
    private List<?> HSC_Dtls;
    private List<EntExamDtlsBean> EntExam_Dtls;
    private List<SelfRegistionDtlsBean> SelfRegistion_Dtls;
    private List<?> Header_Dtls;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StudentDtlsBean> getStudent_Dtls() {
        return Student_Dtls;
    }

    public void setStudent_Dtls(List<StudentDtlsBean> Student_Dtls) {
        this.Student_Dtls = Student_Dtls;
    }

    public List<AddressDtlsBean> getAddress_Dtls() {
        return Address_Dtls;
    }

    public void setAddress_Dtls(List<AddressDtlsBean> Address_Dtls) {
        this.Address_Dtls = Address_Dtls;
    }

    public List<QualificationDtlsBean> getQualification_Dtls() {
        return Qualification_Dtls;
    }

    public void setQualification_Dtls(List<QualificationDtlsBean> Qualification_Dtls) {
        this.Qualification_Dtls = Qualification_Dtls;
    }

    public List<?> getCollege_Dtls() {
        return College_Dtls;
    }

    public void setCollege_Dtls(List<?> College_Dtls) {
        this.College_Dtls = College_Dtls;
    }

    public List<?> getSSCSubject_Dtls() {
        return SSCSubject_Dtls;
    }

    public void setSSCSubject_Dtls(List<?> SSCSubject_Dtls) {
        this.SSCSubject_Dtls = SSCSubject_Dtls;
    }

    public List<?> getIndustral_Dtls() {
        return Industral_Dtls;
    }

    public void setIndustral_Dtls(List<?> Industral_Dtls) {
        this.Industral_Dtls = Industral_Dtls;
    }

    public List<?> getSeminar_Dtls() {
        return Seminar_Dtls;
    }

    public void setSeminar_Dtls(List<?> Seminar_Dtls) {
        this.Seminar_Dtls = Seminar_Dtls;
    }

    public List<?> getWorkShop_Dtls() {
        return WorkShop_Dtls;
    }

    public void setWorkShop_Dtls(List<?> WorkShop_Dtls) {
        this.WorkShop_Dtls = WorkShop_Dtls;
    }

    public List<?> getWorkActivity_Dtls() {
        return WorkActivity_Dtls;
    }

    public void setWorkActivity_Dtls(List<?> WorkActivity_Dtls) {
        this.WorkActivity_Dtls = WorkActivity_Dtls;
    }

    public List<?> getEmployemt_Dtls() {
        return Employemt_Dtls;
    }

    public void setEmployemt_Dtls(List<?> Employemt_Dtls) {
        this.Employemt_Dtls = Employemt_Dtls;
    }

    public List<ParentDtlsBean> getParent_Dtls() {
        return Parent_Dtls;
    }

    public void setParent_Dtls(List<ParentDtlsBean> Parent_Dtls) {
        this.Parent_Dtls = Parent_Dtls;
    }

    public List<?> getGuardian_Dtls() {
        return Guardian_Dtls;
    }

    public void setGuardian_Dtls(List<?> Guardian_Dtls) {
        this.Guardian_Dtls = Guardian_Dtls;
    }

    public List<DocumnetDtlsBean> getDocumnet_Dtls() {
        return Documnet_Dtls;
    }

    public void setDocumnet_Dtls(List<DocumnetDtlsBean> Documnet_Dtls) {
        this.Documnet_Dtls = Documnet_Dtls;
    }

    public List<?> getPayment_Dtls() {
        return Payment_Dtls;
    }

    public void setPayment_Dtls(List<?> Payment_Dtls) {
        this.Payment_Dtls = Payment_Dtls;
    }

    public List<?> getPatmentDate_Dtls() {
        return PatmentDate_Dtls;
    }

    public void setPatmentDate_Dtls(List<?> PatmentDate_Dtls) {
        this.PatmentDate_Dtls = PatmentDate_Dtls;
    }

    public List<?> getHSC_Dtls() {
        return HSC_Dtls;
    }

    public void setHSC_Dtls(List<?> HSC_Dtls) {
        this.HSC_Dtls = HSC_Dtls;
    }

    public List<EntExamDtlsBean> getEntExam_Dtls() {
        return EntExam_Dtls;
    }

    public void setEntExam_Dtls(List<EntExamDtlsBean> EntExam_Dtls) {
        this.EntExam_Dtls = EntExam_Dtls;
    }

    public List<SelfRegistionDtlsBean> getSelfRegistion_Dtls() {
        return SelfRegistion_Dtls;
    }

    public void setSelfRegistion_Dtls(List<SelfRegistionDtlsBean> SelfRegistion_Dtls) {
        this.SelfRegistion_Dtls = SelfRegistion_Dtls;
    }

    public List<?> getHeader_Dtls() {
        return Header_Dtls;
    }

    public void setHeader_Dtls(List<?> Header_Dtls) {
        this.Header_Dtls = Header_Dtls;
    }

    public static class StudentDtlsBean {
        /**
         * ACADEMIC_YEAR : 2017-2018
         * ADMIT_ACADEMIC_YEAR : 2017-2018
         * STUDENT_CODE : 3517011329
         * DATE_OF_BIRTH : 26/03/2000
         * PLACE_OF_BIRTH : SATANA
         * GENDER : FEMALE
         * NATIONALITY_DESCRIPTION : INDIAN
         * RELIGION_DESC : HINDU
         * CASTE_CATEGORY_DESC : OBC
         * CATEGORY_TYPE : RESERVED
         * CASTE_DESCRIPTION : MALI
         * SUB_CASTE_DESCRIPTION : JIRE
         * LANGUAGE_NAME : MARATHI
         * STU_NAME_AS_PER_TC : JADHAV NEHA BALWANT
         * LAST_SCHOOL_COLLEGE_ATTEND : siidhi english medium school and junior college ,Taharabad
         * QUAL_EXAM_TYPE :
         * CETROLLNO :
         * CETSCOREMARKS :
         * TITLE : Ms.
         * FIRST_NAME : NEHA
         * MIDDLE_NAME : BALWANT
         * LAST_NAME : JADHAV
         * MOTHER_NAME : CHHAYA
         * STUD_SIGNATURE :
         * STATE_DESCRIPTION : MAHARASHTRA
         * CASTE_AS_PER_TC : HINDU JIRE MALI
         * STUDENT_MOBILE_NO : 9284708678
         * PARENT_MOBILE_NO : 9421562510
         * GUARDIAN_MOBILE_NO : 0
         * DTE_USER_ID : PH17178200
         * DTE_USER_PASSWORD : Neha@2603
         * ENROLLMENT_NUMBER :
         * MIGRATION_DATE : 31/08/2017
         * MIGRATION_NUMBER :
         * DOMICILE_COUNTRY_ID : 1
         * LANDLINE_NO :
         * STUDENT_EMAIL_ID : jadhavneha965@gmail.com
         * PREV_EDU_PREC_3YRS_THIS_STAT : Y
         * FATH_DOMICLE :
         * MOTH_DOMICLE :
         * NRI_POI : E
         * GUARDIAN_EMAIL_ID :
         * FATHER_EMAIL_ID :
         * MOTHER_EMAIL_ID :
         * COUNTRY_NAME : INDIA
         * BIRTH_STATE_CODE : MH-MAHARASHTRA
         * BLOODGROUP : B+
         * CATEGORY_CODE_B :
         * CATEGORY_CODE_C :
         * GOT_ANY_MEDAL :
         * GOT_ANY_SCHOLARSHIP :
         * HOBBY :
         * IDENTIFICATION_MARK :
         * MARITAL_STATUS : SINGLE
         * NUMBER_OF_CHILDRENS : 0
         * PASSPORT_EXPIRY_DATE :
         * PASSPORT_ISSUE_DATE :
         * PASSPORT_NUMBER :
         * VISA_EXPIRY_DATE :
         * VISA_ISSUE_DATE :
         * VISA_TYPE :
         * COURSE_NAME : B.Pharmacy
         * PREV_TC_NO : 24
         * ALLOT_ADM_THROUGH :
         * STATE_RANK :
         * SECTION_NAME : F. Y. B.Pharm
         * ADMISSION_PATTERN : REGULAR
         * ADMITTED_IN_SHIFT : FIRST
         * DIRECT_SEC_YR_ADMISSION_MODE :
         * OCCUPATION :
         * PARENT_INCOME :
         * SCHOLARSHIP_TYPE : Scholarship
         * DTE_MERIT_NO :
         * DTE_SERIAL_NO :
         * TOTAL_NO_OF_CHILD :
         * CHILD_NO_OF_OWN :
         * BANK_ACCT_NO :
         * BANK_BRANCH_NAME :
         * ADHAR_CARD_NO :
         * STU_ADM_QUALI_SHORT_NAME :
         * MARKS_OBTAINED :
         * MARKS_OUT_OF :
         * ROLL_NUMBER :
         */

        private String ACADEMIC_YEAR;
        private String ADMIT_ACADEMIC_YEAR;
        private String STUDENT_CODE;
        private String DATE_OF_BIRTH;
        private String PLACE_OF_BIRTH;
        private String GENDER;
        private String NATIONALITY_DESCRIPTION;
        private String RELIGION_DESC;
        private String CASTE_CATEGORY_DESC;
        private String CATEGORY_TYPE;
        private String CASTE_DESCRIPTION;
        private String SUB_CASTE_DESCRIPTION;
        private String LANGUAGE_NAME;
        private String STU_NAME_AS_PER_TC;
        private String LAST_SCHOOL_COLLEGE_ATTEND;
        private String QUAL_EXAM_TYPE;
        private String CETROLLNO;
        private String CETSCOREMARKS;
        private String TITLE;
        private String FIRST_NAME;
        private String MIDDLE_NAME;
        private String LAST_NAME;
        private String MOTHER_NAME;
        private String STUD_SIGNATURE;
        private String STATE_DESCRIPTION;
        private String CASTE_AS_PER_TC;
        private String STUDENT_MOBILE_NO;
        private String PARENT_MOBILE_NO;
        private String GUARDIAN_MOBILE_NO;
        private String DTE_USER_ID;
        private String DTE_USER_PASSWORD;
        private String ENROLLMENT_NUMBER;
        private String MIGRATION_DATE;
        private String MIGRATION_NUMBER;
        private String DOMICILE_COUNTRY_ID;
        private String LANDLINE_NO;
        private String STUDENT_EMAIL_ID;
        private String PREV_EDU_PREC_3YRS_THIS_STAT;
        private String FATH_DOMICLE;
        private String MOTH_DOMICLE;
        private String NRI_POI;
        private String GUARDIAN_EMAIL_ID;
        private String FATHER_EMAIL_ID;
        private String MOTHER_EMAIL_ID;
        private String COUNTRY_NAME;
        private String BIRTH_STATE_CODE;
        private String BLOODGROUP;
        private String CATEGORY_CODE_B;
        private String CATEGORY_CODE_C;
        private String GOT_ANY_MEDAL;
        private String GOT_ANY_SCHOLARSHIP;
        private String HOBBY;
        private String IDENTIFICATION_MARK;
        private String MARITAL_STATUS;
        private String NUMBER_OF_CHILDRENS;
        private String PASSPORT_EXPIRY_DATE;
        private String PASSPORT_ISSUE_DATE;
        private String PASSPORT_NUMBER;
        private String VISA_EXPIRY_DATE;
        private String VISA_ISSUE_DATE;
        private String VISA_TYPE;
        private String COURSE_NAME;
        private String PREV_TC_NO;
        private String ALLOT_ADM_THROUGH;
        private String STATE_RANK;
        private String SECTION_NAME;
        private String ADMISSION_PATTERN;
        private String ADMITTED_IN_SHIFT;
        private String DIRECT_SEC_YR_ADMISSION_MODE;
        private String OCCUPATION;
        private String PARENT_INCOME;
        private String SCHOLARSHIP_TYPE;
        private String DTE_MERIT_NO;
        private String DTE_SERIAL_NO;
        private String TOTAL_NO_OF_CHILD;
        private String CHILD_NO_OF_OWN;
        private String BANK_ACCT_NO;
        private String BANK_BRANCH_NAME;
        private String ADHAR_CARD_NO;
        private String STU_ADM_QUALI_SHORT_NAME;
        private String MARKS_OBTAINED;
        private String MARKS_OUT_OF;
        private String ROLL_NUMBER;
        private String Admisstion_status_new;
        private String admission_mode;
        private String DOMICILE_COUNTRY_CODE;


        public String getACADEMIC_YEAR() {
            return ACADEMIC_YEAR;
        }

        public void setACADEMIC_YEAR(String ACADEMIC_YEAR) {
            this.ACADEMIC_YEAR = ACADEMIC_YEAR;
        }


        public String getAdmission_mode() {
            return admission_mode;
        }

        public String getDOMICILE_COUNTRY_CODE() {
            return DOMICILE_COUNTRY_CODE;
        }

        public void setDOMICILE_COUNTRY_CODE(String DOMICILE_COUNTRY_CODE) {
            this.DOMICILE_COUNTRY_CODE = DOMICILE_COUNTRY_CODE;
        }

        public void setAdmission_mode(String admission_mode) {
            this.admission_mode = admission_mode;
        }

        public String getAdmisstion_status_new() {
            return Admisstion_status_new;
        }

        public void setAdmisstion_status_new(String admisstion_status_new) {
            Admisstion_status_new = admisstion_status_new;
        }

        public String getADMIT_ACADEMIC_YEAR() {
            return ADMIT_ACADEMIC_YEAR;
        }

        public void setADMIT_ACADEMIC_YEAR(String ADMIT_ACADEMIC_YEAR) {
            this.ADMIT_ACADEMIC_YEAR = ADMIT_ACADEMIC_YEAR;
        }

        public String getSTUDENT_CODE() {
            return STUDENT_CODE;
        }

        public void setSTUDENT_CODE(String STUDENT_CODE) {
            this.STUDENT_CODE = STUDENT_CODE;
        }

        public String getDATE_OF_BIRTH() {
            return DATE_OF_BIRTH;
        }

        public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
            this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        }

        public String getPLACE_OF_BIRTH() {
            return PLACE_OF_BIRTH;
        }

        public void setPLACE_OF_BIRTH(String PLACE_OF_BIRTH) {
            this.PLACE_OF_BIRTH = PLACE_OF_BIRTH;
        }

        public String getGENDER() {
            return GENDER;
        }

        public void setGENDER(String GENDER) {
            this.GENDER = GENDER;
        }

        public String getNATIONALITY_DESCRIPTION() {
            return NATIONALITY_DESCRIPTION;
        }

        public void setNATIONALITY_DESCRIPTION(String NATIONALITY_DESCRIPTION) {
            this.NATIONALITY_DESCRIPTION = NATIONALITY_DESCRIPTION;
        }

        public String getRELIGION_DESC() {
            return RELIGION_DESC;
        }

        public void setRELIGION_DESC(String RELIGION_DESC) {
            this.RELIGION_DESC = RELIGION_DESC;
        }

        public String getCASTE_CATEGORY_DESC() {
            return CASTE_CATEGORY_DESC;
        }

        public void setCASTE_CATEGORY_DESC(String CASTE_CATEGORY_DESC) {
            this.CASTE_CATEGORY_DESC = CASTE_CATEGORY_DESC;
        }

        public String getCATEGORY_TYPE() {
            return CATEGORY_TYPE;
        }

        public void setCATEGORY_TYPE(String CATEGORY_TYPE) {
            this.CATEGORY_TYPE = CATEGORY_TYPE;
        }

        public String getCASTE_DESCRIPTION() {
            return CASTE_DESCRIPTION;
        }

        public void setCASTE_DESCRIPTION(String CASTE_DESCRIPTION) {
            this.CASTE_DESCRIPTION = CASTE_DESCRIPTION;
        }

        public String getSUB_CASTE_DESCRIPTION() {
            return SUB_CASTE_DESCRIPTION;
        }

        public void setSUB_CASTE_DESCRIPTION(String SUB_CASTE_DESCRIPTION) {
            this.SUB_CASTE_DESCRIPTION = SUB_CASTE_DESCRIPTION;
        }

        public String getLANGUAGE_NAME() {
            return LANGUAGE_NAME;
        }

        public void setLANGUAGE_NAME(String LANGUAGE_NAME) {
            this.LANGUAGE_NAME = LANGUAGE_NAME;
        }

        public String getSTU_NAME_AS_PER_TC() {
            return STU_NAME_AS_PER_TC;
        }

        public void setSTU_NAME_AS_PER_TC(String STU_NAME_AS_PER_TC) {
            this.STU_NAME_AS_PER_TC = STU_NAME_AS_PER_TC;
        }

        public String getLAST_SCHOOL_COLLEGE_ATTEND() {
            return LAST_SCHOOL_COLLEGE_ATTEND;
        }

        public void setLAST_SCHOOL_COLLEGE_ATTEND(String LAST_SCHOOL_COLLEGE_ATTEND) {
            this.LAST_SCHOOL_COLLEGE_ATTEND = LAST_SCHOOL_COLLEGE_ATTEND;
        }

        public String getQUAL_EXAM_TYPE() {
            return QUAL_EXAM_TYPE;
        }

        public void setQUAL_EXAM_TYPE(String QUAL_EXAM_TYPE) {
            this.QUAL_EXAM_TYPE = QUAL_EXAM_TYPE;
        }

        public String getCETROLLNO() {
            return CETROLLNO;
        }

        public void setCETROLLNO(String CETROLLNO) {
            this.CETROLLNO = CETROLLNO;
        }

        public String getCETSCOREMARKS() {
            return CETSCOREMARKS;
        }

        public void setCETSCOREMARKS(String CETSCOREMARKS) {
            this.CETSCOREMARKS = CETSCOREMARKS;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getFIRST_NAME() {
            return FIRST_NAME;
        }

        public void setFIRST_NAME(String FIRST_NAME) {
            this.FIRST_NAME = FIRST_NAME;
        }

        public String getMIDDLE_NAME() {
            return MIDDLE_NAME;
        }

        public void setMIDDLE_NAME(String MIDDLE_NAME) {
            this.MIDDLE_NAME = MIDDLE_NAME;
        }

        public String getLAST_NAME() {
            return LAST_NAME;
        }

        public void setLAST_NAME(String LAST_NAME) {
            this.LAST_NAME = LAST_NAME;
        }

        public String getMOTHER_NAME() {
            return MOTHER_NAME;
        }

        public void setMOTHER_NAME(String MOTHER_NAME) {
            this.MOTHER_NAME = MOTHER_NAME;
        }

        public String getSTUD_SIGNATURE() {
            return STUD_SIGNATURE;
        }

        public void setSTUD_SIGNATURE(String STUD_SIGNATURE) {
            this.STUD_SIGNATURE = STUD_SIGNATURE;
        }

        public String getSTATE_DESCRIPTION() {
            return STATE_DESCRIPTION;
        }

        public void setSTATE_DESCRIPTION(String STATE_DESCRIPTION) {
            this.STATE_DESCRIPTION = STATE_DESCRIPTION;
        }

        public String getCASTE_AS_PER_TC() {
            return CASTE_AS_PER_TC;
        }

        public void setCASTE_AS_PER_TC(String CASTE_AS_PER_TC) {
            this.CASTE_AS_PER_TC = CASTE_AS_PER_TC;
        }

        public String getSTUDENT_MOBILE_NO() {
            return STUDENT_MOBILE_NO;
        }

        public void setSTUDENT_MOBILE_NO(String STUDENT_MOBILE_NO) {
            this.STUDENT_MOBILE_NO = STUDENT_MOBILE_NO;
        }

        public String getPARENT_MOBILE_NO() {
            return PARENT_MOBILE_NO;
        }

        public void setPARENT_MOBILE_NO(String PARENT_MOBILE_NO) {
            this.PARENT_MOBILE_NO = PARENT_MOBILE_NO;
        }

        public String getGUARDIAN_MOBILE_NO() {
            return GUARDIAN_MOBILE_NO;
        }

        public void setGUARDIAN_MOBILE_NO(String GUARDIAN_MOBILE_NO) {
            this.GUARDIAN_MOBILE_NO = GUARDIAN_MOBILE_NO;
        }

        public String getDTE_USER_ID() {
            return DTE_USER_ID;
        }

        public void setDTE_USER_ID(String DTE_USER_ID) {
            this.DTE_USER_ID = DTE_USER_ID;
        }

        public String getDTE_USER_PASSWORD() {
            return DTE_USER_PASSWORD;
        }

        public void setDTE_USER_PASSWORD(String DTE_USER_PASSWORD) {
            this.DTE_USER_PASSWORD = DTE_USER_PASSWORD;
        }

        public String getENROLLMENT_NUMBER() {
            return ENROLLMENT_NUMBER;
        }

        public void setENROLLMENT_NUMBER(String ENROLLMENT_NUMBER) {
            this.ENROLLMENT_NUMBER = ENROLLMENT_NUMBER;
        }

        public String getMIGRATION_DATE() {
            return MIGRATION_DATE;
        }

        public void setMIGRATION_DATE(String MIGRATION_DATE) {
            this.MIGRATION_DATE = MIGRATION_DATE;
        }

        public String getMIGRATION_NUMBER() {
            return MIGRATION_NUMBER;
        }

        public void setMIGRATION_NUMBER(String MIGRATION_NUMBER) {
            this.MIGRATION_NUMBER = MIGRATION_NUMBER;
        }

        public String getDOMICILE_COUNTRY_ID() {
            return DOMICILE_COUNTRY_ID;
        }

        public void setDOMICILE_COUNTRY_ID(String DOMICILE_COUNTRY_ID) {
            this.DOMICILE_COUNTRY_ID = DOMICILE_COUNTRY_ID;
        }

        public String getLANDLINE_NO() {
            return LANDLINE_NO;
        }

        public void setLANDLINE_NO(String LANDLINE_NO) {
            this.LANDLINE_NO = LANDLINE_NO;
        }

        public String getSTUDENT_EMAIL_ID() {
            return STUDENT_EMAIL_ID;
        }

        public void setSTUDENT_EMAIL_ID(String STUDENT_EMAIL_ID) {
            this.STUDENT_EMAIL_ID = STUDENT_EMAIL_ID;
        }

        public String getPREV_EDU_PREC_3YRS_THIS_STAT() {
            return PREV_EDU_PREC_3YRS_THIS_STAT;
        }

        public void setPREV_EDU_PREC_3YRS_THIS_STAT(String PREV_EDU_PREC_3YRS_THIS_STAT) {
            this.PREV_EDU_PREC_3YRS_THIS_STAT = PREV_EDU_PREC_3YRS_THIS_STAT;
        }

        public String getFATH_DOMICLE() {
            return FATH_DOMICLE;
        }

        public void setFATH_DOMICLE(String FATH_DOMICLE) {
            this.FATH_DOMICLE = FATH_DOMICLE;
        }

        public String getMOTH_DOMICLE() {
            return MOTH_DOMICLE;
        }

        public void setMOTH_DOMICLE(String MOTH_DOMICLE) {
            this.MOTH_DOMICLE = MOTH_DOMICLE;
        }

        public String getNRI_POI() {
            return NRI_POI;
        }

        public void setNRI_POI(String NRI_POI) {
            this.NRI_POI = NRI_POI;
        }

        public String getGUARDIAN_EMAIL_ID() {
            return GUARDIAN_EMAIL_ID;
        }

        public void setGUARDIAN_EMAIL_ID(String GUARDIAN_EMAIL_ID) {
            this.GUARDIAN_EMAIL_ID = GUARDIAN_EMAIL_ID;
        }

        public String getFATHER_EMAIL_ID() {
            return FATHER_EMAIL_ID;
        }

        public void setFATHER_EMAIL_ID(String FATHER_EMAIL_ID) {
            this.FATHER_EMAIL_ID = FATHER_EMAIL_ID;
        }

        public String getMOTHER_EMAIL_ID() {
            return MOTHER_EMAIL_ID;
        }

        public void setMOTHER_EMAIL_ID(String MOTHER_EMAIL_ID) {
            this.MOTHER_EMAIL_ID = MOTHER_EMAIL_ID;
        }

        public String getCOUNTRY_NAME() {
            return COUNTRY_NAME;
        }

        public void setCOUNTRY_NAME(String COUNTRY_NAME) {
            this.COUNTRY_NAME = COUNTRY_NAME;
        }

        public String getBIRTH_STATE_CODE() {
            return BIRTH_STATE_CODE;
        }

        public void setBIRTH_STATE_CODE(String BIRTH_STATE_CODE) {
            this.BIRTH_STATE_CODE = BIRTH_STATE_CODE;
        }

        public String getBLOODGROUP() {
            return BLOODGROUP;
        }

        public void setBLOODGROUP(String BLOODGROUP) {
            this.BLOODGROUP = BLOODGROUP;
        }

        public String getCATEGORY_CODE_B() {
            return CATEGORY_CODE_B;
        }

        public void setCATEGORY_CODE_B(String CATEGORY_CODE_B) {
            this.CATEGORY_CODE_B = CATEGORY_CODE_B;
        }

        public String getCATEGORY_CODE_C() {
            return CATEGORY_CODE_C;
        }

        public void setCATEGORY_CODE_C(String CATEGORY_CODE_C) {
            this.CATEGORY_CODE_C = CATEGORY_CODE_C;
        }

        public String getGOT_ANY_MEDAL() {
            return GOT_ANY_MEDAL;
        }

        public void setGOT_ANY_MEDAL(String GOT_ANY_MEDAL) {
            this.GOT_ANY_MEDAL = GOT_ANY_MEDAL;
        }

        public String getGOT_ANY_SCHOLARSHIP() {
            return GOT_ANY_SCHOLARSHIP;
        }

        public void setGOT_ANY_SCHOLARSHIP(String GOT_ANY_SCHOLARSHIP) {
            this.GOT_ANY_SCHOLARSHIP = GOT_ANY_SCHOLARSHIP;
        }

        public String getHOBBY() {
            return HOBBY;
        }

        public void setHOBBY(String HOBBY) {
            this.HOBBY = HOBBY;
        }

        public String getIDENTIFICATION_MARK() {
            return IDENTIFICATION_MARK;
        }

        public void setIDENTIFICATION_MARK(String IDENTIFICATION_MARK) {
            this.IDENTIFICATION_MARK = IDENTIFICATION_MARK;
        }

        public String getMARITAL_STATUS() {
            return MARITAL_STATUS;
        }

        public void setMARITAL_STATUS(String MARITAL_STATUS) {
            this.MARITAL_STATUS = MARITAL_STATUS;
        }

        public String getNUMBER_OF_CHILDRENS() {
            return NUMBER_OF_CHILDRENS;
        }

        public void setNUMBER_OF_CHILDRENS(String NUMBER_OF_CHILDRENS) {
            this.NUMBER_OF_CHILDRENS = NUMBER_OF_CHILDRENS;
        }

        public String getPASSPORT_EXPIRY_DATE() {
            return PASSPORT_EXPIRY_DATE;
        }

        public void setPASSPORT_EXPIRY_DATE(String PASSPORT_EXPIRY_DATE) {
            this.PASSPORT_EXPIRY_DATE = PASSPORT_EXPIRY_DATE;
        }

        public String getPASSPORT_ISSUE_DATE() {
            return PASSPORT_ISSUE_DATE;
        }

        public void setPASSPORT_ISSUE_DATE(String PASSPORT_ISSUE_DATE) {
            this.PASSPORT_ISSUE_DATE = PASSPORT_ISSUE_DATE;
        }

        public String getPASSPORT_NUMBER() {
            return PASSPORT_NUMBER;
        }

        public void setPASSPORT_NUMBER(String PASSPORT_NUMBER) {
            this.PASSPORT_NUMBER = PASSPORT_NUMBER;
        }

        public String getVISA_EXPIRY_DATE() {
            return VISA_EXPIRY_DATE;
        }

        public void setVISA_EXPIRY_DATE(String VISA_EXPIRY_DATE) {
            this.VISA_EXPIRY_DATE = VISA_EXPIRY_DATE;
        }

        public String getVISA_ISSUE_DATE() {
            return VISA_ISSUE_DATE;
        }

        public void setVISA_ISSUE_DATE(String VISA_ISSUE_DATE) {
            this.VISA_ISSUE_DATE = VISA_ISSUE_DATE;
        }

        public String getVISA_TYPE() {
            return VISA_TYPE;
        }

        public void setVISA_TYPE(String VISA_TYPE) {
            this.VISA_TYPE = VISA_TYPE;
        }

        public String getCOURSE_NAME() {
            return COURSE_NAME;
        }

        public void setCOURSE_NAME(String COURSE_NAME) {
            this.COURSE_NAME = COURSE_NAME;
        }

        public String getPREV_TC_NO() {
            return PREV_TC_NO;
        }

        public void setPREV_TC_NO(String PREV_TC_NO) {
            this.PREV_TC_NO = PREV_TC_NO;
        }

        public String getALLOT_ADM_THROUGH() {
            return ALLOT_ADM_THROUGH;
        }

        public void setALLOT_ADM_THROUGH(String ALLOT_ADM_THROUGH) {
            this.ALLOT_ADM_THROUGH = ALLOT_ADM_THROUGH;
        }

        public String getSTATE_RANK() {
            return STATE_RANK;
        }

        public void setSTATE_RANK(String STATE_RANK) {
            this.STATE_RANK = STATE_RANK;
        }

        public String getSECTION_NAME() {
            return SECTION_NAME;
        }

        public void setSECTION_NAME(String SECTION_NAME) {
            this.SECTION_NAME = SECTION_NAME;
        }

        public String getADMISSION_PATTERN() {
            return ADMISSION_PATTERN;
        }

        public void setADMISSION_PATTERN(String ADMISSION_PATTERN) {
            this.ADMISSION_PATTERN = ADMISSION_PATTERN;
        }

        public String getADMITTED_IN_SHIFT() {
            return ADMITTED_IN_SHIFT;
        }

        public void setADMITTED_IN_SHIFT(String ADMITTED_IN_SHIFT) {
            this.ADMITTED_IN_SHIFT = ADMITTED_IN_SHIFT;
        }

        public String getDIRECT_SEC_YR_ADMISSION_MODE() {
            return DIRECT_SEC_YR_ADMISSION_MODE;
        }

        public void setDIRECT_SEC_YR_ADMISSION_MODE(String DIRECT_SEC_YR_ADMISSION_MODE) {
            this.DIRECT_SEC_YR_ADMISSION_MODE = DIRECT_SEC_YR_ADMISSION_MODE;
        }

        public String getOCCUPATION() {
            return OCCUPATION;
        }

        public void setOCCUPATION(String OCCUPATION) {
            this.OCCUPATION = OCCUPATION;
        }

        public String getPARENT_INCOME() {
            return PARENT_INCOME;
        }

        public void setPARENT_INCOME(String PARENT_INCOME) {
            this.PARENT_INCOME = PARENT_INCOME;
        }

        public String getSCHOLARSHIP_TYPE() {
            return SCHOLARSHIP_TYPE;
        }

        public void setSCHOLARSHIP_TYPE(String SCHOLARSHIP_TYPE) {
            this.SCHOLARSHIP_TYPE = SCHOLARSHIP_TYPE;
        }

        public String getDTE_MERIT_NO() {
            return DTE_MERIT_NO;
        }

        public void setDTE_MERIT_NO(String DTE_MERIT_NO) {
            this.DTE_MERIT_NO = DTE_MERIT_NO;
        }

        public String getDTE_SERIAL_NO() {
            return DTE_SERIAL_NO;
        }

        public void setDTE_SERIAL_NO(String DTE_SERIAL_NO) {
            this.DTE_SERIAL_NO = DTE_SERIAL_NO;
        }

        public String getTOTAL_NO_OF_CHILD() {
            return TOTAL_NO_OF_CHILD;
        }

        public void setTOTAL_NO_OF_CHILD(String TOTAL_NO_OF_CHILD) {
            this.TOTAL_NO_OF_CHILD = TOTAL_NO_OF_CHILD;
        }

        public String getCHILD_NO_OF_OWN() {
            return CHILD_NO_OF_OWN;
        }

        public void setCHILD_NO_OF_OWN(String CHILD_NO_OF_OWN) {
            this.CHILD_NO_OF_OWN = CHILD_NO_OF_OWN;
        }

        public String getBANK_ACCT_NO() {
            return BANK_ACCT_NO;
        }

        public void setBANK_ACCT_NO(String BANK_ACCT_NO) {
            this.BANK_ACCT_NO = BANK_ACCT_NO;
        }

        public String getBANK_BRANCH_NAME() {
            return BANK_BRANCH_NAME;
        }

        public void setBANK_BRANCH_NAME(String BANK_BRANCH_NAME) {
            this.BANK_BRANCH_NAME = BANK_BRANCH_NAME;
        }

        public String getADHAR_CARD_NO() {
            return ADHAR_CARD_NO;
        }

        public void setADHAR_CARD_NO(String ADHAR_CARD_NO) {
            this.ADHAR_CARD_NO = ADHAR_CARD_NO;
        }

        public String getSTU_ADM_QUALI_SHORT_NAME() {
            return STU_ADM_QUALI_SHORT_NAME;
        }

        public void setSTU_ADM_QUALI_SHORT_NAME(String STU_ADM_QUALI_SHORT_NAME) {
            this.STU_ADM_QUALI_SHORT_NAME = STU_ADM_QUALI_SHORT_NAME;
        }

        public String getMARKS_OBTAINED() {
            return MARKS_OBTAINED;
        }

        public void setMARKS_OBTAINED(String MARKS_OBTAINED) {
            this.MARKS_OBTAINED = MARKS_OBTAINED;
        }

        public String getMARKS_OUT_OF() {
            return MARKS_OUT_OF;
        }

        public void setMARKS_OUT_OF(String MARKS_OUT_OF) {
            this.MARKS_OUT_OF = MARKS_OUT_OF;
        }

        public String getROLL_NUMBER() {
            return ROLL_NUMBER;
        }

        public void setROLL_NUMBER(String ROLL_NUMBER) {
            this.ROLL_NUMBER = ROLL_NUMBER;
        }
    }

    public static class AddressDtlsBean {
        /**
         * ADDRESS_TYPE_ID : 1
         * ADDRESS_DESCRIPTION : PERMANENT
         * SHORT_CODE : PA
         * STUDENT_ADDRESS1 : at post antapur,
         * STUDENT_ADDRESS2 :
         * PLOT_NUMBER :
         * STREET_NAME :
         * TAHSIL_DESCRIPTION : SATANA (M CL)
         * PINCODE : 423301
         * NEAR_POLIC_STATION : satana
         * CITY_PATTERN :
         */

        private String ADDRESS_TYPE_ID;
        private String ADDRESS_DESCRIPTION;
        private String SHORT_CODE;
        private String STUDENT_ADDRESS1;
        private String STUDENT_ADDRESS2;
        private String PLOT_NUMBER;
        private String STREET_NAME;
        private String TAHSIL_DESCRIPTION;
        private String PINCODE;
        private String NEAR_POLIC_STATION;
        private String CITY_PATTERN;

        public String getADDRESS_TYPE_ID() {
            return ADDRESS_TYPE_ID;
        }

        public void setADDRESS_TYPE_ID(String ADDRESS_TYPE_ID) {
            this.ADDRESS_TYPE_ID = ADDRESS_TYPE_ID;
        }

        public String getADDRESS_DESCRIPTION() {
            return ADDRESS_DESCRIPTION;
        }

        public void setADDRESS_DESCRIPTION(String ADDRESS_DESCRIPTION) {
            this.ADDRESS_DESCRIPTION = ADDRESS_DESCRIPTION;
        }

        public String getSHORT_CODE() {
            return SHORT_CODE;
        }

        public void setSHORT_CODE(String SHORT_CODE) {
            this.SHORT_CODE = SHORT_CODE;
        }

        public String getSTUDENT_ADDRESS1() {
            return STUDENT_ADDRESS1;
        }

        public void setSTUDENT_ADDRESS1(String STUDENT_ADDRESS1) {
            this.STUDENT_ADDRESS1 = STUDENT_ADDRESS1;
        }

        public String getSTUDENT_ADDRESS2() {
            return STUDENT_ADDRESS2;
        }

        public void setSTUDENT_ADDRESS2(String STUDENT_ADDRESS2) {
            this.STUDENT_ADDRESS2 = STUDENT_ADDRESS2;
        }

        public String getPLOT_NUMBER() {
            return PLOT_NUMBER;
        }

        public void setPLOT_NUMBER(String PLOT_NUMBER) {
            this.PLOT_NUMBER = PLOT_NUMBER;
        }

        public String getSTREET_NAME() {
            return STREET_NAME;
        }

        public void setSTREET_NAME(String STREET_NAME) {
            this.STREET_NAME = STREET_NAME;
        }

        public String getTAHSIL_DESCRIPTION() {
            return TAHSIL_DESCRIPTION;
        }

        public void setTAHSIL_DESCRIPTION(String TAHSIL_DESCRIPTION) {
            this.TAHSIL_DESCRIPTION = TAHSIL_DESCRIPTION;
        }

        public String getPINCODE() {
            return PINCODE;
        }

        public void setPINCODE(String PINCODE) {
            this.PINCODE = PINCODE;
        }

        public String getNEAR_POLIC_STATION() {
            return NEAR_POLIC_STATION;
        }

        public void setNEAR_POLIC_STATION(String NEAR_POLIC_STATION) {
            this.NEAR_POLIC_STATION = NEAR_POLIC_STATION;
        }

        public String getCITY_PATTERN() {
            return CITY_PATTERN;
        }

        public void setCITY_PATTERN(String CITY_PATTERN) {
            this.CITY_PATTERN = CITY_PATTERN;
        }
    }

    public static class QualificationDtlsBean {
        /**
         * STU_SCH_QUALI_DTLS_ID : 28326
         * STD_TYPE : HSC
         * STREAM_OPTED_FOR : SCIENCE
         * MEDIUM_OF_INSTRUCTION : ENGLISH
         * AGGREGATE_TOTAL_MARK : 442
         * TOTAL_MARKS_OUT_OF : 650
         * PERCENT_OF_MARKS : 68
         * SINGLE_ATTEMPT : Y
         * MONTH_OF_PASSING : 6
         * YEAR_OF_PASSING : 2017
         * INSTITUTION_NAME : SIDDHI ENGLISH MEDIUM SCHOOL AND JUNIOR COLLEGE
         * EDUC_BOARD_NAME : MAHARASHTRA STATE BOARD - NASHIK DIVISIONAL BOARD
         * IS_INTER_DRAW_EXAM_PAS : null
         * PCM_GROUP_PERCENT :
         * PBM_GROUP_PERCENT :
         * SSC_MATH_OBTAINED_MARKS :
         * SSC_MATH_OUTOF_MARKS :
         */

        private String STU_SCH_QUALI_DTLS_ID;
        private String STD_TYPE;
        private String STREAM_OPTED_FOR;
        private String MEDIUM_OF_INSTRUCTION;
        private String AGGREGATE_TOTAL_MARK;
        private String TOTAL_MARKS_OUT_OF;
        private String PERCENT_OF_MARKS;
        private String SINGLE_ATTEMPT;
        private String MONTH_OF_PASSING;
        private String YEAR_OF_PASSING;
        private String INSTITUTION_NAME;
        private String EDUC_BOARD_NAME;
        private Object IS_INTER_DRAW_EXAM_PAS;
        private String PCM_GROUP_PERCENT;
        private String PBM_GROUP_PERCENT;
        private String SSC_MATH_OBTAINED_MARKS;
        private String SSC_MATH_OUTOF_MARKS;

        public String getSTU_SCH_QUALI_DTLS_ID() {
            return STU_SCH_QUALI_DTLS_ID;
        }

        public void setSTU_SCH_QUALI_DTLS_ID(String STU_SCH_QUALI_DTLS_ID) {
            this.STU_SCH_QUALI_DTLS_ID = STU_SCH_QUALI_DTLS_ID;
        }

        public String getSTD_TYPE() {
            return STD_TYPE;
        }

        public void setSTD_TYPE(String STD_TYPE) {
            this.STD_TYPE = STD_TYPE;
        }

        public String getSTREAM_OPTED_FOR() {
            return STREAM_OPTED_FOR;
        }

        public void setSTREAM_OPTED_FOR(String STREAM_OPTED_FOR) {
            this.STREAM_OPTED_FOR = STREAM_OPTED_FOR;
        }

        public String getMEDIUM_OF_INSTRUCTION() {
            return MEDIUM_OF_INSTRUCTION;
        }

        public void setMEDIUM_OF_INSTRUCTION(String MEDIUM_OF_INSTRUCTION) {
            this.MEDIUM_OF_INSTRUCTION = MEDIUM_OF_INSTRUCTION;
        }

        public String getAGGREGATE_TOTAL_MARK() {
            return AGGREGATE_TOTAL_MARK;
        }

        public void setAGGREGATE_TOTAL_MARK(String AGGREGATE_TOTAL_MARK) {
            this.AGGREGATE_TOTAL_MARK = AGGREGATE_TOTAL_MARK;
        }

        public String getTOTAL_MARKS_OUT_OF() {
            return TOTAL_MARKS_OUT_OF;
        }

        public void setTOTAL_MARKS_OUT_OF(String TOTAL_MARKS_OUT_OF) {
            this.TOTAL_MARKS_OUT_OF = TOTAL_MARKS_OUT_OF;
        }

        public String getPERCENT_OF_MARKS() {
            return PERCENT_OF_MARKS;
        }

        public void setPERCENT_OF_MARKS(String PERCENT_OF_MARKS) {
            this.PERCENT_OF_MARKS = PERCENT_OF_MARKS;
        }

        public String getSINGLE_ATTEMPT() {
            return SINGLE_ATTEMPT;
        }

        public void setSINGLE_ATTEMPT(String SINGLE_ATTEMPT) {
            this.SINGLE_ATTEMPT = SINGLE_ATTEMPT;
        }

        public String getMONTH_OF_PASSING() {
            return MONTH_OF_PASSING;
        }

        public void setMONTH_OF_PASSING(String MONTH_OF_PASSING) {
            this.MONTH_OF_PASSING = MONTH_OF_PASSING;
        }

        public String getYEAR_OF_PASSING() {
            return YEAR_OF_PASSING;
        }

        public void setYEAR_OF_PASSING(String YEAR_OF_PASSING) {
            this.YEAR_OF_PASSING = YEAR_OF_PASSING;
        }

        public String getINSTITUTION_NAME() {
            return INSTITUTION_NAME;
        }

        public void setINSTITUTION_NAME(String INSTITUTION_NAME) {
            this.INSTITUTION_NAME = INSTITUTION_NAME;
        }

        public String getEDUC_BOARD_NAME() {
            return EDUC_BOARD_NAME;
        }

        public void setEDUC_BOARD_NAME(String EDUC_BOARD_NAME) {
            this.EDUC_BOARD_NAME = EDUC_BOARD_NAME;
        }

        public Object getIS_INTER_DRAW_EXAM_PAS() {
            return IS_INTER_DRAW_EXAM_PAS;
        }

        public void setIS_INTER_DRAW_EXAM_PAS(Object IS_INTER_DRAW_EXAM_PAS) {
            this.IS_INTER_DRAW_EXAM_PAS = IS_INTER_DRAW_EXAM_PAS;
        }

        public String getPCM_GROUP_PERCENT() {
            return PCM_GROUP_PERCENT;
        }

        public void setPCM_GROUP_PERCENT(String PCM_GROUP_PERCENT) {
            this.PCM_GROUP_PERCENT = PCM_GROUP_PERCENT;
        }

        public String getPBM_GROUP_PERCENT() {
            return PBM_GROUP_PERCENT;
        }

        public void setPBM_GROUP_PERCENT(String PBM_GROUP_PERCENT) {
            this.PBM_GROUP_PERCENT = PBM_GROUP_PERCENT;
        }

        public String getSSC_MATH_OBTAINED_MARKS() {
            return SSC_MATH_OBTAINED_MARKS;
        }

        public void setSSC_MATH_OBTAINED_MARKS(String SSC_MATH_OBTAINED_MARKS) {
            this.SSC_MATH_OBTAINED_MARKS = SSC_MATH_OBTAINED_MARKS;
        }

        public String getSSC_MATH_OUTOF_MARKS() {
            return SSC_MATH_OUTOF_MARKS;
        }

        public void setSSC_MATH_OUTOF_MARKS(String SSC_MATH_OUTOF_MARKS) {
            this.SSC_MATH_OUTOF_MARKS = SSC_MATH_OUTOF_MARKS;
        }
    }

    public static class ParentDtlsBean {
        /**
         * PARENTS_NAME : BALWANT
         * PARENTS_MIDDLE_NAME : VASANT
         * PARENTS_LAST_NAME : JADHAV
         * PARENT_RELATION : FATHER
         * GENDER : M
         * PARENTS_OCCUPATION : bank
         * PARENTS_ANNUAL_INCOME :
         * PARENT_DATE_OF_BIRTH :
         * WEDDING_ANNIVERSARY_DATE :
         * HOBBY :
         * HOME_STATUS :
         * AGRICUL_FLAG :
         * PARENT_EDUCATION :
         * INCOME_CODE : -
         * ORGANISATION_CODE : -
         * ORGANISATION_NAME : NDCC BANK
         * DESIGNATION_CODE : -
         * PRIM_BUIS_DPART_CODE : -
         * TYPE_OF_EMPLOYEMENT :
         * OFFICE_ADDRESS :
         * OFFICE_ADDR_TAHSIL_ID :
         * TAHSIL_DESCRIPTION :
         * OFFICE_PINCODE :
         * OFFICE_TEL_NO :
         * OFFICE_FAX_NO :
         */

        private String PARENTS_NAME;
        private String PARENTS_MIDDLE_NAME;
        private String PARENTS_LAST_NAME;
        private String PARENT_RELATION;
        private String GENDER;
        private String PARENTS_OCCUPATION;
        private String PARENTS_ANNUAL_INCOME;
        private String PARENT_DATE_OF_BIRTH;
        private String WEDDING_ANNIVERSARY_DATE;
        private String HOBBY;
        private String HOME_STATUS;
        private String AGRICUL_FLAG;
        private String PARENT_EDUCATION;
        private String INCOME_CODE;
        private String ORGANISATION_CODE;
        private String ORGANISATION_NAME;
        private String DESIGNATION_CODE;
        private String PRIM_BUIS_DPART_CODE;
        private String TYPE_OF_EMPLOYEMENT;
        private String OFFICE_ADDRESS;
        private String OFFICE_ADDR_TAHSIL_ID;
        private String TAHSIL_DESCRIPTION;
        private String OFFICE_PINCODE;
        private String OFFICE_TEL_NO;
        private String OFFICE_FAX_NO;

        public String getPARENTS_NAME() {
            return PARENTS_NAME;
        }

        public void setPARENTS_NAME(String PARENTS_NAME) {
            this.PARENTS_NAME = PARENTS_NAME;
        }

        public String getPARENTS_MIDDLE_NAME() {
            return PARENTS_MIDDLE_NAME;
        }

        public void setPARENTS_MIDDLE_NAME(String PARENTS_MIDDLE_NAME) {
            this.PARENTS_MIDDLE_NAME = PARENTS_MIDDLE_NAME;
        }

        public String getPARENTS_LAST_NAME() {
            return PARENTS_LAST_NAME;
        }

        public void setPARENTS_LAST_NAME(String PARENTS_LAST_NAME) {
            this.PARENTS_LAST_NAME = PARENTS_LAST_NAME;
        }

        public String getPARENT_RELATION() {
            return PARENT_RELATION;
        }

        public void setPARENT_RELATION(String PARENT_RELATION) {
            this.PARENT_RELATION = PARENT_RELATION;
        }

        public String getGENDER() {
            return GENDER;
        }

        public void setGENDER(String GENDER) {
            this.GENDER = GENDER;
        }

        public String getPARENTS_OCCUPATION() {
            return PARENTS_OCCUPATION;
        }

        public void setPARENTS_OCCUPATION(String PARENTS_OCCUPATION) {
            this.PARENTS_OCCUPATION = PARENTS_OCCUPATION;
        }

        public String getPARENTS_ANNUAL_INCOME() {
            return PARENTS_ANNUAL_INCOME;
        }

        public void setPARENTS_ANNUAL_INCOME(String PARENTS_ANNUAL_INCOME) {
            this.PARENTS_ANNUAL_INCOME = PARENTS_ANNUAL_INCOME;
        }

        public String getPARENT_DATE_OF_BIRTH() {
            return PARENT_DATE_OF_BIRTH;
        }

        public void setPARENT_DATE_OF_BIRTH(String PARENT_DATE_OF_BIRTH) {
            this.PARENT_DATE_OF_BIRTH = PARENT_DATE_OF_BIRTH;
        }

        public String getWEDDING_ANNIVERSARY_DATE() {
            return WEDDING_ANNIVERSARY_DATE;
        }

        public void setWEDDING_ANNIVERSARY_DATE(String WEDDING_ANNIVERSARY_DATE) {
            this.WEDDING_ANNIVERSARY_DATE = WEDDING_ANNIVERSARY_DATE;
        }

        public String getHOBBY() {
            return HOBBY;
        }

        public void setHOBBY(String HOBBY) {
            this.HOBBY = HOBBY;
        }

        public String getHOME_STATUS() {
            return HOME_STATUS;
        }

        public void setHOME_STATUS(String HOME_STATUS) {
            this.HOME_STATUS = HOME_STATUS;
        }

        public String getAGRICUL_FLAG() {
            return AGRICUL_FLAG;
        }

        public void setAGRICUL_FLAG(String AGRICUL_FLAG) {
            this.AGRICUL_FLAG = AGRICUL_FLAG;
        }

        public String getPARENT_EDUCATION() {
            return PARENT_EDUCATION;
        }

        public void setPARENT_EDUCATION(String PARENT_EDUCATION) {
            this.PARENT_EDUCATION = PARENT_EDUCATION;
        }

        public String getINCOME_CODE() {
            return INCOME_CODE;
        }

        public void setINCOME_CODE(String INCOME_CODE) {
            this.INCOME_CODE = INCOME_CODE;
        }

        public String getORGANISATION_CODE() {
            return ORGANISATION_CODE;
        }

        public void setORGANISATION_CODE(String ORGANISATION_CODE) {
            this.ORGANISATION_CODE = ORGANISATION_CODE;
        }

        public String getORGANISATION_NAME() {
            return ORGANISATION_NAME;
        }

        public void setORGANISATION_NAME(String ORGANISATION_NAME) {
            this.ORGANISATION_NAME = ORGANISATION_NAME;
        }

        public String getDESIGNATION_CODE() {
            return DESIGNATION_CODE;
        }

        public void setDESIGNATION_CODE(String DESIGNATION_CODE) {
            this.DESIGNATION_CODE = DESIGNATION_CODE;
        }

        public String getPRIM_BUIS_DPART_CODE() {
            return PRIM_BUIS_DPART_CODE;
        }

        public void setPRIM_BUIS_DPART_CODE(String PRIM_BUIS_DPART_CODE) {
            this.PRIM_BUIS_DPART_CODE = PRIM_BUIS_DPART_CODE;
        }

        public String getTYPE_OF_EMPLOYEMENT() {
            return TYPE_OF_EMPLOYEMENT;
        }

        public void setTYPE_OF_EMPLOYEMENT(String TYPE_OF_EMPLOYEMENT) {
            this.TYPE_OF_EMPLOYEMENT = TYPE_OF_EMPLOYEMENT;
        }

        public String getOFFICE_ADDRESS() {
            return OFFICE_ADDRESS;
        }

        public void setOFFICE_ADDRESS(String OFFICE_ADDRESS) {
            this.OFFICE_ADDRESS = OFFICE_ADDRESS;
        }

        public String getOFFICE_ADDR_TAHSIL_ID() {
            return OFFICE_ADDR_TAHSIL_ID;
        }

        public void setOFFICE_ADDR_TAHSIL_ID(String OFFICE_ADDR_TAHSIL_ID) {
            this.OFFICE_ADDR_TAHSIL_ID = OFFICE_ADDR_TAHSIL_ID;
        }

        public String getTAHSIL_DESCRIPTION() {
            return TAHSIL_DESCRIPTION;
        }

        public void setTAHSIL_DESCRIPTION(String TAHSIL_DESCRIPTION) {
            this.TAHSIL_DESCRIPTION = TAHSIL_DESCRIPTION;
        }

        public String getOFFICE_PINCODE() {
            return OFFICE_PINCODE;
        }

        public void setOFFICE_PINCODE(String OFFICE_PINCODE) {
            this.OFFICE_PINCODE = OFFICE_PINCODE;
        }

        public String getOFFICE_TEL_NO() {
            return OFFICE_TEL_NO;
        }

        public void setOFFICE_TEL_NO(String OFFICE_TEL_NO) {
            this.OFFICE_TEL_NO = OFFICE_TEL_NO;
        }

        public String getOFFICE_FAX_NO() {
            return OFFICE_FAX_NO;
        }

        public void setOFFICE_FAX_NO(String OFFICE_FAX_NO) {
            this.OFFICE_FAX_NO = OFFICE_FAX_NO;
        }
    }

    public static class DocumnetDtlsBean {
        /**
         * STU_DOCU_SUBMIT_ID : 86896
         * DOCU_ESSENTIAL_NEW_ID : 1021
         * STU_DOCUMENT_NAME : School / College Leaving Certificate
         * STU_DODUMENT_DESC : From Last School attended.
         * STU_FILE_NO :
         * DOCU_ORIG_SUBM :
         * DOCU_ORIG_SUBM_STATUS :
         * DOCU_ATTE_SUBM :
         * DOCU_ATTE_SUBM_STATUS :
         * SOFT_COPY_NAME : 141217005510212017141934LC.jpg
         * SOFT_COPY_PATH : \KKWPH_SC\1412170055\141217005510212017141934LC.jpg
         * SOFT_COPY_SIZE : 1167993
         * SUBMISION_DATE :
         * DOCUMENT_DETAILS :
         * ACTUAL_FILE_NAME :
         */

        private String STU_DOCU_SUBMIT_ID;
        private String DOCU_ESSENTIAL_NEW_ID;
        private String STU_DOCUMENT_NAME;
        private String STU_DODUMENT_DESC;
        private String STU_FILE_NO;
        private String DOCU_ORIG_SUBM;
        private String DOCU_ORIG_SUBM_STATUS;
        private String DOCU_ATTE_SUBM;
        private String DOCU_ATTE_SUBM_STATUS;
        private String SOFT_COPY_NAME;
        private String SOFT_COPY_PATH;
        private String SOFT_COPY_SIZE;
        private String SUBMISION_DATE;
        private String DOCUMENT_DETAILS;
        private String ACTUAL_FILE_NAME;

        public String getSTU_DOCU_SUBMIT_ID() {
            return STU_DOCU_SUBMIT_ID;
        }

        public void setSTU_DOCU_SUBMIT_ID(String STU_DOCU_SUBMIT_ID) {
            this.STU_DOCU_SUBMIT_ID = STU_DOCU_SUBMIT_ID;
        }

        public String getDOCU_ESSENTIAL_NEW_ID() {
            return DOCU_ESSENTIAL_NEW_ID;
        }

        public void setDOCU_ESSENTIAL_NEW_ID(String DOCU_ESSENTIAL_NEW_ID) {
            this.DOCU_ESSENTIAL_NEW_ID = DOCU_ESSENTIAL_NEW_ID;
        }

        public String getSTU_DOCUMENT_NAME() {
            return STU_DOCUMENT_NAME;
        }

        public void setSTU_DOCUMENT_NAME(String STU_DOCUMENT_NAME) {
            this.STU_DOCUMENT_NAME = STU_DOCUMENT_NAME;
        }

        public String getSTU_DODUMENT_DESC() {
            return STU_DODUMENT_DESC;
        }

        public void setSTU_DODUMENT_DESC(String STU_DODUMENT_DESC) {
            this.STU_DODUMENT_DESC = STU_DODUMENT_DESC;
        }

        public String getSTU_FILE_NO() {
            return STU_FILE_NO;
        }

        public void setSTU_FILE_NO(String STU_FILE_NO) {
            this.STU_FILE_NO = STU_FILE_NO;
        }

        public String getDOCU_ORIG_SUBM() {
            return DOCU_ORIG_SUBM;
        }

        public void setDOCU_ORIG_SUBM(String DOCU_ORIG_SUBM) {
            this.DOCU_ORIG_SUBM = DOCU_ORIG_SUBM;
        }

        public String getDOCU_ORIG_SUBM_STATUS() {
            return DOCU_ORIG_SUBM_STATUS;
        }

        public void setDOCU_ORIG_SUBM_STATUS(String DOCU_ORIG_SUBM_STATUS) {
            this.DOCU_ORIG_SUBM_STATUS = DOCU_ORIG_SUBM_STATUS;
        }

        public String getDOCU_ATTE_SUBM() {
            return DOCU_ATTE_SUBM;
        }

        public void setDOCU_ATTE_SUBM(String DOCU_ATTE_SUBM) {
            this.DOCU_ATTE_SUBM = DOCU_ATTE_SUBM;
        }

        public String getDOCU_ATTE_SUBM_STATUS() {
            return DOCU_ATTE_SUBM_STATUS;
        }

        public void setDOCU_ATTE_SUBM_STATUS(String DOCU_ATTE_SUBM_STATUS) {
            this.DOCU_ATTE_SUBM_STATUS = DOCU_ATTE_SUBM_STATUS;
        }

        public String getSOFT_COPY_NAME() {
            return SOFT_COPY_NAME;
        }

        public void setSOFT_COPY_NAME(String SOFT_COPY_NAME) {
            this.SOFT_COPY_NAME = SOFT_COPY_NAME;
        }

        public String getSOFT_COPY_PATH() {
            return SOFT_COPY_PATH;
        }

        public void setSOFT_COPY_PATH(String SOFT_COPY_PATH) {
            this.SOFT_COPY_PATH = SOFT_COPY_PATH;
        }

        public String getSOFT_COPY_SIZE() {
            return SOFT_COPY_SIZE;
        }

        public void setSOFT_COPY_SIZE(String SOFT_COPY_SIZE) {
            this.SOFT_COPY_SIZE = SOFT_COPY_SIZE;
        }

        public String getSUBMISION_DATE() {
            return SUBMISION_DATE;
        }

        public void setSUBMISION_DATE(String SUBMISION_DATE) {
            this.SUBMISION_DATE = SUBMISION_DATE;
        }

        public String getDOCUMENT_DETAILS() {
            return DOCUMENT_DETAILS;
        }

        public void setDOCUMENT_DETAILS(String DOCUMENT_DETAILS) {
            this.DOCUMENT_DETAILS = DOCUMENT_DETAILS;
        }

        public String getACTUAL_FILE_NAME() {
            return ACTUAL_FILE_NAME;
        }

        public void setACTUAL_FILE_NAME(String ACTUAL_FILE_NAME) {
            this.ACTUAL_FILE_NAME = ACTUAL_FILE_NAME;
        }
    }

    public static class EntExamDtlsBean {
        /**
         * STU_QUAL_ENTR_EXAM_DTL_ID : 50064
         * STU_ADM_QUALI_SHORT_NAME :
         * ROLL_NUMBER :
         * STATE_RANK :
         * TOTAL_MARKS_OBTAINED :
         * TOTAL_MARKS :
         * SUBJECT_NAME :
         * MARKS_OBTAINED :
         * MARKS_OUT_OF :
         */

        private String STU_QUAL_ENTR_EXAM_DTL_ID;
        private String STU_ADM_QUALI_SHORT_NAME;
        private String ROLL_NUMBER;
        private String STATE_RANK;
        private String TOTAL_MARKS_OBTAINED;
        private String TOTAL_MARKS;
        private String SUBJECT_NAME;
        private String MARKS_OBTAINED;
        private String MARKS_OUT_OF;

        public String getSTU_QUAL_ENTR_EXAM_DTL_ID() {
            return STU_QUAL_ENTR_EXAM_DTL_ID;
        }

        public void setSTU_QUAL_ENTR_EXAM_DTL_ID(String STU_QUAL_ENTR_EXAM_DTL_ID) {
            this.STU_QUAL_ENTR_EXAM_DTL_ID = STU_QUAL_ENTR_EXAM_DTL_ID;
        }

        public String getSTU_ADM_QUALI_SHORT_NAME() {
            return STU_ADM_QUALI_SHORT_NAME;
        }

        public void setSTU_ADM_QUALI_SHORT_NAME(String STU_ADM_QUALI_SHORT_NAME) {
            this.STU_ADM_QUALI_SHORT_NAME = STU_ADM_QUALI_SHORT_NAME;
        }

        public String getROLL_NUMBER() {
            return ROLL_NUMBER;
        }

        public void setROLL_NUMBER(String ROLL_NUMBER) {
            this.ROLL_NUMBER = ROLL_NUMBER;
        }

        public String getSTATE_RANK() {
            return STATE_RANK;
        }

        public void setSTATE_RANK(String STATE_RANK) {
            this.STATE_RANK = STATE_RANK;
        }

        public String getTOTAL_MARKS_OBTAINED() {
            return TOTAL_MARKS_OBTAINED;
        }

        public void setTOTAL_MARKS_OBTAINED(String TOTAL_MARKS_OBTAINED) {
            this.TOTAL_MARKS_OBTAINED = TOTAL_MARKS_OBTAINED;
        }

        public String getTOTAL_MARKS() {
            return TOTAL_MARKS;
        }

        public void setTOTAL_MARKS(String TOTAL_MARKS) {
            this.TOTAL_MARKS = TOTAL_MARKS;
        }

        public String getSUBJECT_NAME() {
            return SUBJECT_NAME;
        }

        public void setSUBJECT_NAME(String SUBJECT_NAME) {
            this.SUBJECT_NAME = SUBJECT_NAME;
        }

        public String getMARKS_OBTAINED() {
            return MARKS_OBTAINED;
        }

        public void setMARKS_OBTAINED(String MARKS_OBTAINED) {
            this.MARKS_OBTAINED = MARKS_OBTAINED;
        }

        public String getMARKS_OUT_OF() {
            return MARKS_OUT_OF;
        }

        public void setMARKS_OUT_OF(String MARKS_OUT_OF) {
            this.MARKS_OUT_OF = MARKS_OUT_OF;
        }
    }

    public static class SelfRegistionDtlsBean {
        /**
         * STU_SELF_REGISTRATION_CODE : 141179293
         * ADMISSION_PATTERN : REGULAR
         * COURSE_YEAR : F. Y. B.Pharm
         * REGISTRATION_DATE : 31/08/2017
         */

        private String STU_SELF_REGISTRATION_CODE;
        private String ADMISSION_PATTERN;
        private String COURSE_YEAR;
        private String REGISTRATION_DATE;

        public String getSTU_SELF_REGISTRATION_CODE() {
            return STU_SELF_REGISTRATION_CODE;
        }

        public void setSTU_SELF_REGISTRATION_CODE(String STU_SELF_REGISTRATION_CODE) {
            this.STU_SELF_REGISTRATION_CODE = STU_SELF_REGISTRATION_CODE;
        }

        public String getADMISSION_PATTERN() {
            return ADMISSION_PATTERN;
        }

        public void setADMISSION_PATTERN(String ADMISSION_PATTERN) {
            this.ADMISSION_PATTERN = ADMISSION_PATTERN;
        }

        public String getCOURSE_YEAR() {
            return COURSE_YEAR;
        }

        public void setCOURSE_YEAR(String COURSE_YEAR) {
            this.COURSE_YEAR = COURSE_YEAR;
        }

        public String getREGISTRATION_DATE() {
            return REGISTRATION_DATE;
        }

        public void setREGISTRATION_DATE(String REGISTRATION_DATE) {
            this.REGISTRATION_DATE = REGISTRATION_DATE;
        }
    }
}
