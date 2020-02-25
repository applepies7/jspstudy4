package com.study.util;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;

public class PreMVCTest {

	public static void main(String[] args) throws Exception {

		String str = "com.study.member.vo.MemberVO";
		Class<?> voClass = Class.forName(str);
		MemberVO vo1 = (MemberVO) voClass.newInstance();
		vo1.setMemName("말자");
		vo1.setMemMail("malja1004@gmail.com");
		System.out.printf("객체 정보 : %s %s\n", vo1.getMemName(), vo1.getMemMail());
		
		Map<String, String> pMap = new HashMap<String, String>();
		
		
		String path = PreMVCTest.class.getResource("/config/test.properties").getPath();
	
		Properties prop = new Properties();
		try (InputStreamReader fis = new FileReader(path)) {
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}
		Iterator key = prop.keySet().iterator();
		while (key.hasNext()) {
			String a = (String) key.next();
			String b = prop.getProperty(a);
			pMap.put(a, b);
		}
		System.out.println(pMap.get("hate"));

		 Map<String, Object> paramMap = new HashMap<String, Object>();
  	   paramMap.put("boNum", 21);
  	   paramMap.put("boTitle", "사랑해요 밀키스");
  	   paramMap.put("boWriter", "말자");
  	   
  	   
  	   FreeBoardVO vo = new FreeBoardVO();
  	   
		
		convertMapToObject(paramMap, vo);
		System.out.printf("Info : %d %s %s\n", vo.getBoNum(), vo.getBoWriter(), vo.getBoTitle());
		
	}
     
     
     public static Object convertMapToObject(Map<String,Object> map,Object obj){
         String key = null;
         String methodString = null;
         Iterator itr = map.keySet().iterator();
         
         while(itr.hasNext()){
             key = (String) itr.next();
             methodString = "set"+key.substring(0,1).toUpperCase()+key.substring(1);            
             Method[] methods = obj.getClass().getDeclaredMethods();
             System.out.println(key + "=" + methodString);
             for(int i=0;i<methods.length;i++){
                 if(methodString.equals(methods[i].getName())){
                     try{
                         methods[i].invoke(obj, map.get(key));
                     }catch(Exception e){
                         e.printStackTrace();
                     }
                 }
             }
         }
         return obj;
     }

	}
			
