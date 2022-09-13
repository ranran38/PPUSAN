package com.project.ppusan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.ppusan.domain.Clothes;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface WeatherMapper {
	public List<Clothes> getList();
}
