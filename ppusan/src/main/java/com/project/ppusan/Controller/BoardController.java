package com.project.ppusan.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ppusan.domain.Board;
import com.project.ppusan.domain.Code;
import com.project.ppusan.security.UserInfo;
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
			@AuthenticationPrincipal UserInfo user,
			Model model) {
		
		String contentTypeId = code.toContentTypeId(category);
		//sigungu 전체 지역 볼 경우
		if(sigungu.equals("all")) {
			int total = boardService.getTotal(contentTypeId);
			PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
			List<Board> boards = boardService.findBoardsByContentTypeId(contentTypeId, navi.getStartRecord(), navi.getCountPerPage());
			model.addAttribute(model.addAttribute("navi", navi));
			if(user!=null) {
				for(Board board : boards) {
					HashMap<String,String> map = new HashMap<>();
					map.put("contentId", board.getContentId());
					map.put("memberId", user.getUsername());
					int result = boardService.checkLike(map);
					System.out.println(result);
					if(result>0) {
						board.setCheckLike("yes");
					}else{
						board.setCheckLike("no");
					}
				}
			}else {
				for(Board board : boards) {
					board.setCheckLike("no");
				}
			}
			System.out.println(boards);
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
			if(user!=null) {
				for(Board board : boards) {
					map = new HashMap<String,String>();
					map.put("contentId", board.getContentId());
					map.put("memberId", user.getUsername());
					int result = boardService.checkLike(map);
					System.out.println(result);
					if(result>0) {
						board.setCheckLike("yes");
					}else{
						board.setCheckLike("no");
					}
				}
			}else {
				for(Board board : boards) {
					board.setCheckLike("no");
				}
			}
			model.addAttribute("boards", boards);
		}
		
		model.addAttribute("korCategory",code.intoKor(category));
		model.addAttribute("korSigungu",code.intoKor(sigungu));
		model.addAttribute("category",category);
		model.addAttribute("sigungu",sigungu);
		return "/detail";
	}
	
	@GetMapping({"/getSpotlight"})
	public ResponseEntity<List<Board>> getSpotlight(@RequestParam("page") int page,
			Model model, @AuthenticationPrincipal UserInfo user) {
		List<Board> boards = boardService.getSpotlight(page);
//		System.out.println(user);
		if(user!=null) {
			for(Board board : boards) {
				HashMap<String,String> map = new HashMap<>();
				map.put("contentId", board.getContentId());
				map.put("memberId", user.getUsername());
				int result = boardService.checkLike(map);
				System.out.println(result);
				if(result>0) {
					board.setCheckLike("yes");
				}else{
					board.setCheckLike("no");
				}
			}
		}else {
			for(Board board : boards) {
				board.setCheckLike("no");
			}
		}
//		System.out.println(boards);
		return new ResponseEntity<>(boards, HttpStatus.OK);
	}
	
	@GetMapping("/like")
	public String like(String contentId, @AuthenticationPrincipal UserInfo user) {
		HashMap<String,String> map = new HashMap<>();
		map.put("contentId",contentId);
		map.put("memberId", user.getUsername());
		int result = boardService.checkLike(map);
		if(result>0) {
			boardService.cancelLike(map);
		}else {
			boardService.addLike(map);
		}
		return "redirect:/";
	}
	
	@GetMapping("/boardLike")
	public String boardLike(String contentId, String category, String sigungu, 
							@RequestParam(value = "page", defaultValue = "1")int page, 
							@AuthenticationPrincipal UserInfo user) {
		HashMap<String,String> map = new HashMap<>();
		map.put("contentId",contentId);
		map.put("memberId", user.getUsername());
		int result = boardService.checkLike(map);
		if(result>0) {
			boardService.cancelLike(map);
		}else {
			boardService.addLike(map);
		}
		
		return "redirect:/board/list/"+category+"?sigungu="+sigungu+"&page="+page;
	}
}
