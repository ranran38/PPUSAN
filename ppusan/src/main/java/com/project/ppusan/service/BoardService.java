package com.project.ppusan.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ppusan.domain.Board;
import com.project.ppusan.domain.Likelist;
import com.project.ppusan.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public void insertBoard(Board board) {
		boardMapper.deleteAllBoards();
		boardMapper.insertBoard(board);
	}
	
	//카테고리별 총계
	public int getTotal(String contentTypeId) {
		return boardMapper.getTotal(contentTypeId);
	}
	
	public int getTotalBySigunguCode(HashMap<String,String> map) {
		return boardMapper.getTotalBySigunguCode(map);
	}
	
	public List<Board> findBoardsByContentTypeId(String contentTypeId, int startRecord, int countPerPage){
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return boardMapper.findBoardsByContentTypeId(contentTypeId, rb);
	}
	
	public List<Board> findBoardsBySigunguCode(HashMap<String,String> map, int startRecord, int countPerPage){
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return boardMapper.findBoardsBySigunguCode(map, rb);
	}
    
	public List<Board> getSpotlight(int page){
		int offset = (page-1)*3;
		int limit = 3;
		System.out.println("offset:"+offset+",limit:"+limit);
		RowBounds rb = new RowBounds(offset, limit);
		return boardMapper.findSpotlight(rb);
	}
	
	public int checkLike(HashMap<String,String> map) {
	      return boardMapper.checkLike(map);
	   }
	   
	   public void cancelLike(HashMap<String,String> map) {
	      Board board = boardMapper.findBoard(map.get("contentId"));
	      board.setLikeCount(board.getLikeCount()-1);
	      boardMapper.updateLikeCount(board);
	      boardMapper.cancelLike(map);
	   }
	   
	   public void addLike(HashMap<String,String> map) {
	      Board board = boardMapper.findBoard(map.get("contentId"));
	      board.setLikeCount(board.getLikeCount()+1);
	      boardMapper.updateLikeCount(board);
	      boardMapper.addLike(map);
	   }
	   
	   public List<Likelist> findLikeList(String memberId) {
		      return boardMapper.findLikeList(memberId);
	   }

}
