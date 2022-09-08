package com.project.ppusan.Controller;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ppusan.domain.Code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/board")
@Controller
public class BoardController {

	Code code = new Code();
	/**
	 * 카테고리,시군구를 파라미터로 받아 URL생성
	 * @param model
	 * @param category "spot", "stay", "food", "leports"
	 * @param sigungu "jin", "jung", "dong", "haeundae", "gijang"
	 * @param page
	 * @return
	 */
//	@GetMapping({"/list/{category}"})
	public String test(Model model, 
			@PathVariable String category, 
			@RequestParam(required = false, defaultValue = "") String sigungu,
			@RequestParam(required = false, defaultValue = "1") String page) {
		
		String key = "XR5hyBkBHwkX3CFe3R0BIXVKLgGVbcua5FmQ1I27XP0mipS3EpACc9U%2FRnH2U19EH7nA5kIHkaYKu1MVSAPIxg%3D%3D";
		String contentTypeId = code.toContentTypeId(category);
		if(!sigungu.equals("")) {
			sigungu = code.toSigunguCode(sigungu);
		}
		String url = "http://apis.data.go.kr/B551011/KorService/areaBasedList?numOfRows=12&pageNo="
				+ page +"&MobileOS=ETC&MobileApp=AppTest&ServiceKey="
				+ key +"&listYN=Y&arrange=A&contentTypeId="
				+ contentTypeId + "&areaCode=6&sigunguCode="
				+ sigungu +"&cat1=&cat2=&cat3=";
		model.addAttribute("url",url);
		return "/board/result";  
	}
}
