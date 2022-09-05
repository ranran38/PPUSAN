package com.project.ppusan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	String contentId;
	long hit;
	long like;
	String contentTypeId;
	String sigunguCode;
	String addr;
	String image;		//이미지 주소
	String x;
	String y;
	String title;
}
