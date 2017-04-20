package com.lostandfound.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lostandfound.beans.Data;
import com.lostandfound.beans.User;

/**
 * JSON解析工具类
 * 
 * @author Administrator
 *
 */
public class JsonUtils {
	/**
	 * 将JSON对象转化为User对象
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static User jsonToUser(JSONObject json) throws JSONException {
		String phone = json.getString("phone");
		String password = json.getString("password");
		String name = json.getString("name");
		String sex = json.getString("sex");
		String address = json.getString("address");
		return new User(phone, password, name, sex, address);
	}

	/**
	 * 将User对象转化为JSON对象
	 * 
	 * @param user
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject userToJson(User user) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("phone", user.getPhone());
		json.put("password", user.getPassword());
		json.put("name", user.getName());
		json.put("sex", user.getSex());
		json.put("address", user.getAddress());
		return json;
	}

	/**
	 * 将JSON对象解析为整型数
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static int jsonToInt(JSONObject json, String key) {
		int v = 0;
		try {
			v = json.getInt(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}

	/**
	 * 将整型数转化为JSON对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static JSONObject intToJson(String key, int value) {
		JSONObject json = new JSONObject();
		try {
			json.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * JSON对象解析为字符串
	 * 
	 * @param json
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static String jsonToStr(JSONObject json, String key) {
		String str = "";
		try {
			str = json.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 字符串转化为JSON对象
	 * 
	 * @param str
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject strToJson(String str, String key) {
		JSONObject json = new JSONObject();
		try {
			json.put(key, str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将JSON对象转化为Data对象
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static Data jsonToData(JSONObject json) throws JSONException {
		Data data = new Data();
		data.setId(json.getInt("id"));
		data.setType(json.getString("type"));
		data.setName(json.getString("name"));
		data.setDetail(json.getString("detail"));
		data.setRemark(json.getString("remark"));
		data.setPlace(json.getString("place"));

		String incident1 = (String) json.get("incidentdate1");
		data.setIncidentDate1(strToSql(incident1));
		data.setIncidentTime1(json.getString("incidenttime1"));

		String incident2 = (String) json.get("incidentdate2");
		data.setIncidentDate2(strToSql(incident2));
		data.setIncidentTime2(json.getString("incidenttime2"));

		String publish = json.getString("publishdate");
		data.setPublishDate(strToSql(publish));
		data.setPublishTime(json.getString("publishtime"));

		data.setPicName(json.getString("picname"));
		data.setIsFinish(json.getString("isfinish"));
		data.setUserPhone(json.getString("userphone"));
		return data;
	}

	/**
	 * 将Data对象转化为JSON对象
	 * 
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject dataToJson(Data data) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", data.getId());
		json.put("type", data.getType());
		json.put("name", data.getName());
		json.put("detail", data.getDetail());
		json.put("remark", data.getRemark());
		json.put("place", data.getPlace());
		json.put("incidentdate1", data.getIncidentDate1());
		json.put("incidenttime1", data.getIncidentTime1());
		json.put("incidentdate2", data.getIncidentDate2());
		json.put("incidenttime2", data.getIncidentTime2());
		json.put("publishdate", data.getPublishDate());
		json.put("publishtime", data.getPublishTime());
		json.put("picname", data.getPicName());
		json.put("isfinish", data.getIsFinish());
		json.put("userphone", data.getUserPhone());
		return json;
	}

	/**
	 * 将字符串转化为Date
	 * 
	 * @param time
	 * @return
	 */
	public static Date strToSql(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = new Date(sdf.parse(time).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将JSON对象转化为Data集合
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static List<Data> jsonTodatas(JSONObject json) throws JSONException {
		List<Data> datas = new ArrayList<Data>();
		JSONArray array = json.getJSONArray("datas");
		for (int i = 0; i < array.length(); i++) {
			datas.add(jsonToData(array.getJSONObject(i)));
		}
		return datas;
	}

	/**
	 * 将Data集合转化为JSON对象
	 * 
	 * @param datas
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject datasToJson(List<Data> datas) throws JSONException {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for (Data data : datas) {
			array.put(dataToJson(data));
		}
		json.put("datas", array);
		return json;
	}
}
