package id.co.iteacode.util;

import org.springframework.data.domain.Page;

public class ModelDataTable<T> {

	private Page<T> data;
	private PagerModel pagerModel;
	private int selectedPageSize;
	private int[] pageSize;
	private int evalPage;
	private int totalRow;
	private int resultCount;
	private String authName;

	public ModelDataTable() {
		// TODO Auto-generated constructor stub
	}

	public ModelDataTable(Page<T> data, PagerModel pagerModel, int selectedPageSize, int[] pageSize, int evalPage,
			int totalRow, int resultCount) {
		super();
		this.data = data;
		this.pagerModel = pagerModel;
		this.selectedPageSize = selectedPageSize;
		this.pageSize = pageSize;
		this.evalPage = evalPage;
		this.totalRow = totalRow;
		this.resultCount = resultCount;
	}

	
	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public Page<T> getData() {
		return data;
	}

	public void setData(Page<T> data) {
		this.data = data;
	}

	public PagerModel getPagerModel() {
		return pagerModel;
	}

	public void setPagerModel(PagerModel pagerModel) {
		this.pagerModel = pagerModel;
	}

	public int getSelectedPageSize() {
		return selectedPageSize;
	}

	public void setSelectedPageSize(int selectedPageSize) {
		this.selectedPageSize = selectedPageSize;
	}

	public int[] getPageSize() {
		return pageSize;
	}

	public void setPageSize(int[] pageSize) {
		this.pageSize = pageSize;
	}

	public int getEvalPage() {
		return evalPage;
	}

	public void setEvalPage(int evalPage) {
		this.evalPage = evalPage;
	}

}
