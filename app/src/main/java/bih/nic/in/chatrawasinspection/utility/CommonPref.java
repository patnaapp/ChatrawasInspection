package bih.nic.in.chatrawasinspection.utility;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import bih.nic.in.chatrawasinspection.entity.UserDetails;


public class CommonPref {

	static Context context;

	CommonPref() {

	}

	CommonPref(Context context) {
		CommonPref.context = context;
	}



	public static void setUserDetails(Context context, UserDetails userInfo) {

		String key = "_USER_DETAILS";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();

		editor.putString("UserID", userInfo.getUserID());
		editor.putString("UserName", userInfo.getUserName());
		editor.putString("UserRole", userInfo.getUserRole());
		editor.putString("DeptId", userInfo.getDeptId());
		editor.putString("DeptCode", userInfo.getDeptCode());
		editor.putString("DeptName", userInfo.getDeptName());
		editor.putString("DistrictCode", userInfo.getDistrictCode());
		editor.putString("DistrictName", userInfo.getDistrictName());
		editor.putString("HospitalCode", userInfo.getHospitalCode());
		editor.putString("PanchayatCode", userInfo.getPanchayatCode());
		editor.putString("Hospital", userInfo.getHospital());
		editor.putString("HopitalType", userInfo.getHopitalType());
//		editor.putString("RoleDescription", userInfo.getRoleDescription());
//		editor.putString("DeptId", userInfo.getDeptId());
//		editor.putString("DistrictCode", userInfo.getDistrictCode());
//
//		editor.putString("DistrictName", userInfo.getDistrictName());
//		editor.putString("BlockCode", userInfo.getBlockCode());
//		editor.putString("BlockName", userInfo.getBlockName());
//
//		editor.putString("PanchayatCode", userInfo.getPanchayatCode());
//		editor.putString("GrpTypeID", userInfo.getGrpTypeID());
//		editor.putString("UserID", userInfo.getUserID());
//		editor.putString("Password", userInfo.getPassword());
//		editor.putString("UserName", userInfo.getUserName());



		editor.commit();

	}

	public static UserDetails getUserDetails(Context context) {

		String key = "_USER_DETAILS";
		UserDetails userInfo = new UserDetails();
		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		userInfo.setUserID(prefs.getString("UserID", ""));
		userInfo.setUserName(prefs.getString("UserName", ""));
		userInfo.setUserRole(prefs.getString("UserRole", ""));
		userInfo.setDeptId(prefs.getString("DeptId", ""));
		userInfo.setDeptCode(prefs.getString("DeptCode", ""));
		userInfo.setDeptName(prefs.getString("DeptName", ""));
		userInfo.setDistrictCode(prefs.getString("DistrictCode", ""));
		userInfo.setDistrictName(prefs.getString("DistrictName", ""));
		userInfo.setHospitalCode(prefs.getString("HospitalCode", ""));
		userInfo.setPanchayatCode(prefs.getString("PanchayatCode", ""));
		userInfo.setHospital(prefs.getString("Hospital", ""));
		userInfo.setHopitalType(prefs.getString("HopitalType", ""));



//		userInfo.setRoleDescription(prefs.getString("RoleDescription", ""));
//		userInfo.setDeptId(prefs.getString("DeptId", ""));
//		userInfo.setDistrictCode(prefs.getString("DistrictCode", ""));
//		userInfo.setDistrictName(prefs.getString("DistrictName", ""));
//		userInfo.setBlockCode(prefs.getString("BlockCode", ""));
//		userInfo.setBlockName(prefs.getString("BlockName", ""));
//		userInfo.setPanchayatCode(prefs.getString("PanchayatCode", ""));
//		userInfo.setGrpTypeID(prefs.getString("GrpTypeID", ""));
//		userInfo.setUserID(prefs.getString("UserID", ""));
//		userInfo.setPassword(prefs.getString("Password", ""));
//		userInfo.setUserName(prefs.getString("UserName", ""));

		return userInfo;
	}



	public static void setCheckUpdate(Context context, long dateTime) {

		String key = "_CheckUpdate";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();


		dateTime=dateTime+1*3600000;
		editor.putLong("LastVisitedDate", dateTime);

		editor.commit();

	}

	public static int getCheckUpdate(Context context) {

		String key = "_CheckUpdate";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		long a = prefs.getLong("LastVisitedDate", 0);


		if(System.currentTimeMillis()>a)
			return 1;
		else
			return 0;
	}

	public static void setAwcId(Activity activity, String awcid){
		String key = "_Awcid";
		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("code2", awcid);
		editor.commit();
	}


}
