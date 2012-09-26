package com.shizhong.db.cmd;

import java.util.Arrays;

public class EntityRow {
	private long id;
	private String[] cell;

	public String[] getCells() {
		return cell;
	}

	public long getId() {
		return id;
	}

	public void setCells(String[] cells) {
		this.cell = cells;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LiGanRow [id=" + id + ", cells=" + Arrays.toString(cell)
				+ "]";
	}
}