package com.project.ppusan.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ppusan.domain.Board;
import com.project.ppusan.security.UserInfo;
import com.project.ppusan.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Controller
public class TourApiController {
	
	final BoardService boardService;
	
	@GetMapping("/getApi")
	public String getApi(@AuthenticationPrincipal UserInfo user) {
		if(user==null) {
			return "redirect:/";
		}else if(!user.getMember().getRole().equals("ROLE_MANAGER")) {
			return "redirect:/";
		}
		return "api/getApi";
	}
	
	
	@GetMapping("/responseApi")
	public String responseApi() {
		String result = "";
		
		try {
			String numOfRows = "1500";
			URL url = new URL("http://apis.data.go.kr/B551011/KorService/areaBasedList?numOfRows=" 
					+ numOfRows + "&pageNo=&MobileOS=ETC&MobileApp=AppTest&ServiceKey="
							+ "XR5hyBkBHwkX3CFe3R0BIXVKLgGVbcua5FmQ1I27XP0mipS3EpACc9U%2FRnH2U19EH7nA5kIHkaYKu1MVSAPIxg%3D%3D"
							+ "&listYN=Y&arrange=A&contentTypeId=&areaCode=6&sigunguCode=&cat1=&cat2=&cat3=&_type=json");
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			
			JSONParser parser = new JSONParser();
			JSONObject objResult = (JSONObject)parser.parse(result);
			JSONObject objResponse = (JSONObject)objResult.get("response");
			JSONObject objBody = (JSONObject)objResponse.get("body");
			JSONObject objItems = (JSONObject)objBody.get("items");
			JSONArray arrItem = (JSONArray)objItems.get("item");
			
			if(arrItem.size() > 0) {
				for(int i = 0; i < arrItem.size(); i++){
					JSONObject tmp = (JSONObject)arrItem.get(i);
					String contentTypeId = (String)tmp.get("contenttypeid");
					String sigungu = (String)tmp.get("sigungucode");
					
					// 카테고리가 {식당, 관광, 레포츠, 숙박}에 포함되지 않는 경우, continue
					if(!(contentTypeId.equals("12")||contentTypeId.equals("39")||contentTypeId.equals("32")||contentTypeId.equals("28"))) {
						continue;
					}
					// 시군구가 널일 경우, continue
					if(sigungu==null||sigungu.equals("")) {
						continue;
					}
					String addr = (String)tmp.get("addr1") + (String)tmp.get("addr2");
					Board board = new Board((String)tmp.get("contentid"), (long)tmp.get("readcount"), 0L, contentTypeId,
							sigungu, addr, (String)tmp.get("firstimage"), (String)tmp.get("mapx"), (String)tmp.get("mapy"),
							(String)tmp.get("title"), "no");
					boardService.insertBoard(board);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
		
	}
}
