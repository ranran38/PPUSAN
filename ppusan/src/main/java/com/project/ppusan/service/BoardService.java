package com.project.ppusan.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.project.ppusan.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
private final BoardMapper boardMapper;
	
    
}
