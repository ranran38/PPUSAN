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
	
	public String[] getContentTypeIdList(){
		String[] contentTypeIdLists = {"12","39","32","28"};
		return contentTypeIdLists;
	}
}
