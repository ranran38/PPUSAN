package com.project.ppusan.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.ppusan.domain.Board;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardMapper {
	public void insertBoard(Board board);
	public List<Board> findAllBoards();
	public Board readBoard(long boardnum);
	public void removeBoard(long boardnum);
	public void soldOut(Board board);
	public List<Board> searchByKeyword(String keyword);
	public List<Board> searchByCategoryAndKeyword(Map<String,String> map);
}
