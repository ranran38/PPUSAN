package com.project.ppusan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ppusan.domain.Board;
import com.project.ppusan.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}
    
}
