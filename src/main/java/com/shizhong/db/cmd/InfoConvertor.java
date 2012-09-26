package com.shizhong.db.cmd;

import java.io.BufferedReader;
import java.io.IOException;

import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.User;

public interface InfoConvertor {
	PreTransaction persist(BufferedReader source, User curUser) throws IOException;
}
