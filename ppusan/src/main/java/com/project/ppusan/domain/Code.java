package com.project.ppusan.domain;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

/**
 * 카테고리, 시군구를 contentTypeId, sigunguCode로 변환
 * @author miran
 *
 */
@Data
public class Code {
	
	private HashMap<String,String> category;
	private HashMap<String,String> sigungu;
	
	public Code() {
		category = new HashMap<String,String>();
		category.put("spot", "12");
		category.put("food", "39");
		category.put("stay", "32");
		category.put("leports", "28");
		
		sigungu = new HashMap<String,String>();
		sigungu.put("haeundae", "16");
		sigungu.put("dong", "5");
		sigungu.put("jin", "7");
		sigungu.put("jung", "15");
		sigungu.put("gijang", "3");
	}
	
	public String toContentTypeId(String c) {
		return category.get(c);
	}
	
	public String toSigunguCode(String s) {
		return sigungu.get(s);
	}
	
	public String intoKor(String string) {
		String kor = null;
		
		if(string.equals("spot")) {
			kor = "관광지";
		}else if(string.equals("food")) {
			kor = "맛집";
		}else if(string.equals("stay")) {
			kor = "숙박";
		}else if(string.equals("leports")) {
			kor = "레포츠";
		}else if(string.equals("jin")) {
			kor = "부산진구";
		}else if(string.equals("gijang")) {
			kor = "기장군";
		}else if(string.equals("dong")) {
			kor = "동구";
		}else if(string.equals("jung")) {
			kor = "중구";
		}else if(string.equals("haeundae")) {
			kor = "해운대구";
		}else if(string.equals("all")) {
			kor = "전체";
		}
		return kor;
	}
}
