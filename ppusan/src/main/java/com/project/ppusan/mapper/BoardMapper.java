package com.project.ppusan.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.ppusan.domain.Board;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardMapper {
	public void insertBoard(Board board);
	
}
