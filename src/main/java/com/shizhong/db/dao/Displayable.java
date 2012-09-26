package com.shizhong.db.dao;

import java.sql.Blob;

public interface Displayable {
	void setImage(Blob img);
	Blob getImage();
}
