package com.project.ppusan.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ppusan.domain.Board;
import com.project.ppusan.domain.Code;
import com.project.ppusan.domain.Likelist;
import com.project.ppusan.domain.Member;
import com.project.ppusan.domain.UpdateMemberForm;
import com.project.ppusan.mapper.BoardMapper;
import com.project.ppusan.security.UserInfo;
import com.project.ppusan.service.BoardService;
import com.project.ppusan.service.MemberService;
import com.project.ppusan.util.PageNavigator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/mypage")
@Controller
public class MypageController {
	
	private final MemberService memberService;
	private final BoardService boardService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	Code code = new Code();
	final int countPerPage = 9;    // 페이지 당 글 수
    final int pagePerGroup = 3;     // 페이지 이동 그룹 당 표시할 페이지 수
	
	/*
	 * @GetMapping("/update") public String update() { return "mypage/updateForm"; }
	 */
	
	@GetMapping("/update")
	public String getMember(@AuthenticationPrincipal UserInfo user, Model model) {
		String memberId = user.getUsername();
		Member member = memberService.findMember(memberId);
		model.addAttribute("updateForm", member.toUpdateMemberForm());
		return "/mypage/updateForm";
	}
	
	@PostMapping("/update/{memberId}")
	public String updateMember(@PathVariable String memberId, 
							   @Validated @ModelAttribute UpdateMemberForm updateMemberForm, 
							   BindingResult bindingResult, 
							   Model model) {
		log.info("upateForm : {}", updateMemberForm);
		// Form Validation
		if (bindingResult.hasErrors()) {
			return "/mypage/updateForm";
		}
	
		if (updateMemberForm.getPassword() == null || updateMemberForm.getPassword() == "") {
			updateMemberForm.setPassword(null);
		}
		updateMemberForm.setPassword(bCryptPasswordEncoder.encode(updateMemberForm.getPassword()));
		memberService.updateMember(updateMemberForm.toMember());
		return "redirect:/";
	}
	
	/*
	 * @GetMapping(value = "/update/{memberId}") public String
	 * updateForm(@PathVariable String memberId, Model model) { Member findMember =
	 * memberService.findMember(memberId); model.addAttribute("member",
	 * findMember.toUpdateMemberForm()); return "mypage/updateForm"; }
	 * 
	 * // 회원정보 수정
	 * 
	 * @PostMapping(value = "/update") public String
	 * update(@Validated @ModelAttribute("updateMemberForm") UpdateMemberForm
	 * updateMemberForm, BindingResult bindingResult) { return "redirect:/"; }
	 */
	
	@GetMapping("/planner")
	public String planner() {
		return "/mypage/planner";
	}
	
	@GetMapping("/plannerwrite")
	public String plannerwrite() {
		return "/mypage/plannerwrite";
	}
	
	@GetMapping("/plannerDetail")
	public String plannerDetail() {
		return "/mypage/plannerDetail";
	}	
	
	//@GetMapping("/like")
	/*
	 * public String liket(@AuthenticationPrincipal UserInfo user, String
	 * category, @RequestParam(value = "page", defaultValue = "1") int page, Model
	 * model) { String contentTypeId = code.toContentTypeId(category); int total =
	 * boardService.getTotal(contentTypeId); PageNavigator navi = new
	 * PageNavigator(countPerPage, pagePerGroup, page, total); String memberId =
	 * user.getUsername(); model.addAttribute("memberId", memberId); List<Board>
	 * board = boardService.findLikeList(memberId, navi.getStartRecord(),
	 * navi.getCountPerPage()); return "/mypage/like"; }
	 */	
	
	@GetMapping("/like")
	public String like(@AuthenticationPrincipal UserInfo user,Model model) {
		String memberId = user.getUsername();
		List<Likelist> list = boardService.findLikeList(memberId);
		log.info("boarffff : {}", list);
		model.addAttribute("list", list);
		return "/mypage/like";
	}
}
