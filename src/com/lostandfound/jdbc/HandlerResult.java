package com.lostandfound.jdbc;

import java.sql.ResultSet;
/**
 * 结果集处理接口
 * @author lee
 *
 */
public interface HandlerResult {
	public void handle(ResultSet resultSet) throws Exception;
}
