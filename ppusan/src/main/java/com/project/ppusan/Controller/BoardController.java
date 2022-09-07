package com.project.ppusan.Controller;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/board")
@Controller
public class BoardController {

	@GetMapping({"/list"})
	public String test(Model model) {
		String key = "XR5hyBkBHwkX3CFe3R0BIXVKLgGVbcua5FmQ1I27XP0mipS3EpACc9U%2FRnH2U19EH7nA5kIHkaYKu1MVSAPIxg%3D%3D";
		String url = "http://apis.data.go.kr/B551011/KorService/areaBasedList?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey="
				+key+"&listYN=Y&arrange=A&contentTypeId=&areaCode=6&sigunguCode=&cat1=&cat2=&cat3=";
		model.addAttribute("url",url);
		return "/board/result";  
	}
}
