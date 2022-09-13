package com.project.ppusan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ppusan.domain.Clothes;
import com.project.ppusan.mapper.WeatherMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {
	
	private final WeatherMapper weatherMapper;
	
	public List<Clothes> getList() {
        List<Clothes> clothes = weatherMapper.getList();
        log.info("{}",clothes);
        return clothes;
    }

}
