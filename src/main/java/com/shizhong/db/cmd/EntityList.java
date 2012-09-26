package com.shizhong.db.cmd;

import java.util.ArrayList;
import java.util.List;



public class EntityList {
	private String total;
	private String page;
	private String numberCount; // ²»Í¬ÃúÅÆºÅ
	private List<EntityRow> rows = new ArrayList<EntityRow>();

	public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    public String getPage() {
		return page;
	}

	public List<EntityRow> getRows() {
		return rows;
	}

	public String getTotal() {
		return total;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setRows(List<EntityRow> rows) {
		this.rows = rows;
	}

	public void addRow(EntityRow row) {
		this.rows.add(row);
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "LiGanList [total=" + total + ", page=" + page + ", rows="
				+ rows + "]";
	}

}