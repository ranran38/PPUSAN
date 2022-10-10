package com.project.ppusan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.ppusan.domain.Board;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardMapper {
	public void insertBoard(Board board);
	public int getTotal(String contentTypeId);
	public int getTotalBySigunguCode(HashMap<String,String> map);
	public List<Board> findBoardsByContentTypeId(String contentTypeId, RowBounds rb);
	public List<Board> findBoardsBySigunguCode(HashMap<String,String> map, RowBounds rb);
	public List<Board> findSpotlight(RowBounds rb);
	public int checkLike(HashMap<String,String> map);
	public void cancelLike(HashMap<String,String> map);
	public void addLike(HashMap<String,String> map);
	public void updateLikeCount(Board board);
	public Board findBoard(String contentId);
	public void deleteAllBoards();
}
