package com.lostandfound.jdbc;

import java.sql.PreparedStatement;

/**
 * 输入值处理接口
 * 
 * @author lee
 *
 */
public interface PrepareSetter {
	public void setter(PreparedStatement preparedStatement) throws Exception;

}
