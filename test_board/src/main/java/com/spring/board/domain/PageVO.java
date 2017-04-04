package com.spring.board.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageVO {
	
	private int totalCount;
	private int totalPage;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private Criteria cri;
	
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType()).queryParam("keyword", ((SearchCriteria)cri).getKeyword()).build();
		return uriComponents.toUriString();
	}
	
	
	public void calculator() {
		totalPage = totalCount / cri.getPerPageNum();
		if(totalCount % cri.getPerPageNum() > 0) {
			totalPage++;
		}
		if(totalPage < cri.getPage()) {
			cri.setPage(totalPage);
		}
		startPage = ((cri.getPage() - 1) / 10) * 10 + 1;
		endPage = startPage + displayPageNum - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage < totalPage ? true : false;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calculator();
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

}
