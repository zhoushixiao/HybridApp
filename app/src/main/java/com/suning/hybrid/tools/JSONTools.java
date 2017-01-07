package com.suning.hybrid.tools;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONTools {


	/**
	 * json—>String
	 */

	public static String JsonToString(String jsonData,String key)
	{
		String result="";
		if(jsonData==null || "".equals(jsonData.trim()) || "null".equals(jsonData.trim()))
		{
			return "";
		}
		if(key==null || "".equals(key.trim()) || "null".equals(key.trim()))
		{
			return "";
		}

		try {
			JSONTokener jsonParser = new JSONTokener(jsonData);
			JSONObject obj = (JSONObject) jsonParser.nextValue(); 
			result=obj.getString(key);
		} catch (Exception e) {
			result="";
		}
		if(result==null || "null".equals(result))
		{
			result="";
		}
		return result;
	}

	/**
	 * JSON----->JSONArray    如果ֹkey不存在，防止报错
	 */

	public static JSONArray JsonToArray(String jsonData)
	{
		JSONArray array=new JSONArray();
		
		if(jsonData==null || "".equals(jsonData.trim()))
		{
			return array;
		}
		
		try {
			array=new JSONArray(jsonData);
		} catch (Exception e) {
			array=new JSONArray();
		}
		return array;
	}

	/**
	 *  从文本中读取字符
	 */
	public static  String readJSON(Context context,int rid)
	{
		InputStream inputStream = context.getResources().openRawResource(rid);
		 InputStreamReader inputStreamReader = null;  
		      try {  
		         inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
		     } catch (UnsupportedEncodingException e1) {  
		          e1.printStackTrace();  
		      }  
		     BufferedReader reader = new BufferedReader(inputStreamReader);  
		      StringBuffer sb = new StringBuffer("");  
		      String line;  
		      try {  
		          while ((line = reader.readLine()) != null) {  
		             sb.append(line);  
		             sb.append("\n");  
		          }  
		      } catch (IOException e) {  
		          e.printStackTrace();  
		      }  
		     return sb.toString();
	}



	/**
	 *json————>List
	 * @param json
	 * @return
	 */
	public static List<Map<String,String>> jsonToList(String json)
	{
		List<Map<String,String>> datalist=new ArrayList<Map<String,String>>();
		try {
			JSONArray array=JsonToArray(json);
			for(int i=0;i<array.length();i++)
			{
				Map<String,String> map=new HashMap<String,String>();
				String s=array.getString(i);

				JSONTokener jsonParser = new JSONTokener(s);
				JSONObject obj = (JSONObject) jsonParser.nextValue();

				Iterator it = obj.keys();
				while(it.hasNext()){
					String key=it.next().toString();
					String value=JsonToString(s,key);
					map.put(key,value);
				}
				datalist.add(map);
			}

		} catch (JSONException e) {
			datalist=new ArrayList<Map<String,String>>();
		}

		return datalist;
	}



	/**
	 *json————>Map
	 * @param json
	 * @return
	 */
	public static Map<String,String> jsonToMap(String json)
	{
		Map<String,String> map=new HashMap<String,String>();

		try{

			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject obj = (JSONObject) jsonParser.nextValue();

			Iterator it = obj.keys();
			while(it.hasNext()){
				String key=it.next().toString();
				String value=JsonToString(json,key);
				map.put(key,value);
			}

		}catch (Exception e)
		{
			map=new HashMap<String,String>();
			return map;
		}

		return map;
	}
	/**
	 *json————>Map
	 * @param json
	 * @return
	 */
	public static String parseJson(String json,String cateId,boolean isYuce)
	{
		String result = "";
		//每个指标指定的key
		String keyValue = "ZB_XS_0001_SJ_01";
		if (isYuce){
			keyValue = cateId + "_SJ_14";
		}else{
			keyValue = cateId + "_SJ_01";
		}
		try{
			JSONObject object = new JSONObject(json);
			result = object.optString(keyValue);
			if (TextUtils.isEmpty(result)){
				result = "";
			}
		}catch (Exception e)
		{
			result = "";
		}

		return result;
	}

}
