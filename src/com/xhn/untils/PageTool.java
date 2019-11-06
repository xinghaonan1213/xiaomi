package com.xhn.untils;

public class PageTool {
	private int pageSize;//��ҳ�� 
	private int totalCount;//��������
	private int currentPage;//��ǰҳ��
	private int lastPage;//��һҳ
	private int nextPage;//��һҳ
	private int pageCount;//ÿҳ��������
	private int startIndex;//��ʼ�±�
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
    public PageTool(int totalCount, String currentPage) {
		super();
		//��ʼ����ǰ��������
		this.totalCount=totalCount;
		//��ʼ��ÿҳ��������
		pageCount=5;
		//��ʼ����ҳ��
	     initialPageSize();
		//��ʼ����ǰҳ��
		initialCurrentPage(currentPage);
		
		//��ʼ����һҳ
		initialLastPage();
		//��ʼ�����һҳ
		initialNextPage();
		//��ʼ����ʼ�±�
		initialStartIndex();
	}

	private void initialPageSize() {
	pageSize = totalCount / pageCount + (totalCount % pageCount == 0 ? 0 : 1);
		
	}
	private void initialStartIndex() {
		this.startIndex=(currentPage-1)*pageCount; 
		
	}
	private void initialNextPage() {
		if (currentPage==pageSize) {
			this.nextPage=pageSize;
		}else {
			this.nextPage=currentPage+1;
		}
		
	}
	private void initialLastPage() {
		if (currentPage==1) {
			this.lastPage=1;
		}else {
			this.lastPage=currentPage-1;
		}
		
	}
	private void initialCurrentPage(String currentPage) {
		/*
		 * ��Ϊ��ǰҳ���Ǵ�ǰ��ҳ�洫�����ģ���ô�ս���user_list.jspʱ��û�����뵱ǰҳ��ֵ����ô��ǰҳ����ǵ�һҳ ��������£����ǵڼ�ҳ���ǵڼ�ҳ
		 */
		if (currentPage == null || "".equals(currentPage)) {
			this.currentPage = 1;
		} else {
			this.currentPage = Integer.valueOf(currentPage);
		}

	}
}
