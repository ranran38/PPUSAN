package com.project.ppusan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ppusan.domain.Clothes;

@Mapper
public interface WeatherMapper {
	/*
	 * public List<Clothes> findAllClothes(); public Clothes findClothesById(double
	 * maxT, double minT); public List<Image> findAllImage(); public Image
	 * findImageById(int clothesId);
	 */
	public List<Clothes> getList();
}
