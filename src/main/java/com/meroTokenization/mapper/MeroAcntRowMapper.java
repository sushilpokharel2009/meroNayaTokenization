package com.meroTokenization.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.meroTokenization.model.MeroAcntVO;

public class MeroAcntRowMapper implements RowMapper{

	private static final Logger logger = LoggerFactory.getLoghger(MeroAcntRowMapper.class);
	
	public static final String BANK_ACCOUNT_NO = "BankAccountNo";
	public static final String ROUTING_NO = "RoutingNo";
	
	
	public List<MeroAcntVO> mapRow(ResultSet resultSet, int fetchsize) throws SQLException{
		
		List<MeroAcntVO> dataList = new ArrayList();
		for(int i = 0; i < fetchsize; i++) {

			MeroAcntVO merovo = new MeroAcntVO();
			merovo.setMeroAcntNo(resultSet.getString(BANK_ACCOUNT_NO));
			merovo.setRoutingNo(resultSet.getString(ROUTING_NO));
			dataList.add(merovo);
				
	}
		
		for(MeroAcntVO origVo:dataList) {
			logger.debug("RoutingNo:" + origVo.getRoutingNo() + "BankAccountNo:" + origVo.getMeroAcntNo());
		}
		return dataList;
	}
}