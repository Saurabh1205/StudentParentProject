package com.wordpro.studentproject.webConfig;

/**
 * Created by wccs1980 on 05-10-2017.
 */

public class WebConfig {

    public String BASE_URL;
    public String URL;
    public String getBaseURL(String instituteCode) {

        String code = "fail";
        switch (instituteCode) {

            case "KKWNSK":
                BASE_URL = "http://210.212.188.198:90/api/";
                URLEndPoints.Constance_InstituteCode ="KKWNSK";
                code = BASE_URL;
                break;
            case "DMIMS":
                BASE_URL = "http://103.68.25.40:82/api/";
                URLEndPoints.Constance_InstituteCode ="DMIMS";
                code = BASE_URL;
                break;
            case "MITWPU":
                //BASE_URL = "http://123.252.195.249:80/api/";
               // BASE_URL = "http://117.220.228.12:80/api/";
               // BASE_URL = "http://103.96.41.134/api/"; //change url ip  date 7/8/2020 ask to  harshal
                BASE_URL = "http://103.96.41.144/api/";
                URLEndPoints.Constance_InstituteCode ="MITWPU";
                code = BASE_URL;
                break;
            case "MGMNAND":
                BASE_URL = "http://210.212.170.217:81/api/";
                URLEndPoints.Constance_InstituteCode ="MGMNAND";
                code = BASE_URL;
                break;
            case "JNECAUGB":
                BASE_URL = "http://117.232.127.218:500/api/";
                URLEndPoints.Constance_InstituteCode ="JNECAUGB";
                code = BASE_URL;
                break;
            case "WORDPRO":
                   //BASE_URL = "http://117.247.82.252:500/api/";   //(public ip  working on all mobile )
                 //BASE_URL ="http://192.168.10.72:500/api/";
                   //BASE_URL ="http://192.168.1.72:500/api/";   //(private ip  working on  System (only system lan ) )
                  //BASE_URL ="http://192.168.1.72:50001/api/";
                 //BASE_URL ="http://192.168.1.72:99/api/";
                 // BASE_URL ="http://192.168.1.72:80/api/";
                 BASE_URL ="http://103.73.215.154:500/api/";
                URLEndPoints.Constance_InstituteCode ="WORDPRO";
                code = BASE_URL;
                break;
            case "JDIET":
                // BASE_URL = "http://103.67.188.229:82/api/"; //25/06/2020
                //BASE_URL = "http://103.208.73.138:82/api/";
                //BASE_URL = "http://103.208.73.138:82/api/";
                 URLEndPoints.Constance_InstituteCode ="JDIET";
                // BASE_URL ="http://103.225.174.30:82/api/";
                 BASE_URL ="http://103.181.16.210:82/api/"; //02/02/2023

                code = BASE_URL;
                break;
            default:
                code = "fail";
        }

        return code;
    }

    /* Alias: AndroidDebugKey
                MD5: 4F:DD:06:AE:09:16:5A:D8:E7:E9:5A:ED:02:17:FD:CC
                SHA1: 52:29:C5:19:B6:76:8E:05:41:CC:6F:EA:AD:CB:88:BD:E8:B8:CB:7D
                Valid until: Saturday, 10 August, 2047*/
    //API_KEY_YOUTUBE AIzaSyDQBAyl_qeEqLyvyBWLeKnoeMyLTpbuHGg

    //SHA-1 : 52:29:C5:19:B6:76:8E:05:41:CC:6F:EA:AD:CB:88:BD:E8:B8:CB:7D


}
