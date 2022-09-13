package com.project.ppusan.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ppusan.domain.Clothes;
import com.project.ppusan.service.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WeatherController {
	
	private final WeatherService weatherservice;
	
	@GetMapping("/forecast")
	public String weatherForecast(Model model) {
		List<Clothes> clothes = weatherservice.getList();
		log.info("{}",clothes);
        model.addAttribute("clothes", clothes);
		return "/weather/weatherForecast";
	}
	
	
}
