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
	
	String contentId;		//contentid
	long hit;				//readcount
	long likeCount;
	String contentTypeId;	//contenttypeid
	String sigunguCode;		//sigungucode
	String addr;			//addr1 + addr2
	String image;			//firstimage(null)
	String x;				//mapx
	String y;				//mapy
	String title;			//title
}
