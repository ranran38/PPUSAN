package com.project.ppusan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@GetMapping({"", "/"})
	public String home(@RequestParam(defaultValue = "1") int page,
			Model model) {
		if(page<1) {
			page=1;
		}
		model.addAttribute("page",page);
		return "/main";
	}
	
	
}
