package com.project.ppusan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@GetMapping({"", "/"})
	public String home() {
		return "/main";
	}
	
	@GetMapping({"detail"})
	public String detail() {
		return "/detail";
	}	
}
