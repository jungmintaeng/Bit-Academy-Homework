package com.cafe24.mvc.util;

import com.cafe24.mysite.dao.BoardDao;

public class Page {
	private final int items_per_page = 10;
	private final int pages_per_context = 5;
	private long itemCount;
	private long totalPage;
	private long curPage;
	private long startNo;
	private long endNo;
	private boolean left;
	private boolean right;

	public Page(long curPage, String kwd) {
		this.curPage = curPage;
		calculateMembers(kwd);
	}

	public void calculateMembers(String kwd) {
		itemCount = new BoardDao().getCount(kwd);
		totalPage = itemCount % items_per_page == 0 ? itemCount / items_per_page
																					  : itemCount / items_per_page + 1;
		startNo = pages_per_context * (int) ((curPage-1) / pages_per_context) + 1;
		endNo = pages_per_context * (int) ((curPage-1) / pages_per_context) + pages_per_context;
		left = startNo > pages_per_context;
		right = endNo < totalPage;
	}

	public boolean isCurPageValid() {
		return curPage > 0 && curPage <= totalPage;
	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getStartNo() {
		return startNo;
	}

	public void setStartNo(long startNo) {
		this.startNo = startNo;
	}

	public long getEndNo() {
		return endNo;
	}

	public void setEndNo(long endNo) {
		this.endNo = endNo;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public int getItems_per_page() {
		return items_per_page;
	}

	public int getPages_per_context() {
		return pages_per_context;
	}
}
