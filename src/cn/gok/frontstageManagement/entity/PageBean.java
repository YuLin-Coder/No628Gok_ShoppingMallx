package cn.gok.frontstageManagement.entity;

import java.util.List;

public class PageBean {
	private int currentPage; // 当前第几页
	private int pageSize; // 每页条数
	private int totalPage; // 总页数
	private int totalCount; // 总条数
	
	/*
     * 初始化分页实体类构造函数， 传入每页显示数据量，和 总数据量，
     * 
     * 自动计算出 多少页
     */
    public PageBean(int pageSize, int totalCount,int currentPage) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        
        //三目运算符计算总页数
        this.totalPage = this.totalCount % this.pageSize == 0? this.totalCount / this.pageSize:this.totalCount / this.pageSize + 1;
      
    }
	
   	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Goods> getGoods() {
		return goods;
	}
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	private List<Goods> goods; // 每页数据
}
