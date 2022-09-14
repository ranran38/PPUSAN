package com.project.ppusan.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ppusan.domain.Board;
import com.project.ppusan.domain.Code;
import com.project.ppusan.service.BoardService;
import com.project.ppusan.util.PageNavigator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/board")
@Controller
public class BoardController {

	Code code = new Code();
	private final BoardService boardService;
	
	final int countPerPage = 9;    // 페이지 당 글 수
    final int pagePerGroup = 3;     // 페이지 이동 그룹 당 표시할 페이지 수
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
	
	/**
	 * 카테고리,시군구를 파라미터로 받아 리스트 출력
	 * @param model
	 * @param category "spot", "stay", "food", "leports"
	 * @param sigungu "jin", "jung", "dong", "haeundae", "gijang"
	 * @param page
	 * @return
	 */
	@GetMapping("/list/{category}")
	public String list(@PathVariable String category,
			@RequestParam(value = "sigungu", defaultValue = "all") String sigungu,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		
		String contentTypeId = code.toContentTypeId(category);
		//sigungu 전체 지역 볼 경우
		if(sigungu.equals("all")) {
			int total = boardService.getTotal(contentTypeId);
			PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
			List<Board> boards = boardService.findBoardsByContentTypeId(contentTypeId, navi.getStartRecord(), navi.getCountPerPage());
			model.addAttribute(model.addAttribute("navi", navi));
			model.addAttribute("boards", boards);
		}else {
			HashMap<String,String> map = new HashMap<String,String>();
			String sigunguCode = code.toSigunguCode(sigungu);
			map.put("contentTypeId", contentTypeId);
			map.put("sigunguCode", sigunguCode);
			int total = boardService.getTotalBySigunguCode(map);
			PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
			List<Board> boards = boardService.findBoardsBySigunguCode(map, navi.getStartRecord(), navi.getCountPerPage());
			model.addAttribute(model.addAttribute("navi", navi));
			model.addAttribute("boards", boards);
		}
		model.addAttribute("korCategory",code.intoKor(category));
		model.addAttribute("korSigungu",code.intoKor(sigungu));
		model.addAttribute("category",category);
		model.addAttribute("sigungu",sigungu);
		return "/detail";
	}
}
