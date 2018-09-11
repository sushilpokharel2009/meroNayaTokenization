package com.meroTokenization.DAOImpl;

import java.sql.PreparedStatement;
import java.util.logging.Logger;

import com.meroTokenization.DAO.List;
import com.meroTokenization.DAO.MeroAcntDAO;
import com.meroTokenization.DAO.MeroAcntVO;

@Repository
public class MeroAcntDAOImpl implements MeroAcntDAO{
	final Logger logger = LoggerFactory.getLogger(MeroAcntDAOImpl.class);
	@Inject
	private JdbcTemplate sageJdbcTemplate;
	private String salUpdateStmt;
	
	public void updateAcntDetails(List<MeroAcntVO> meroAcntDetails) {
	
		try {
			sageJdbcTemplate.batchUpdate(sqlupdateStmt, new BatchPrepartedStaementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement, int i) throws SQLException{
					setPreparedStmt(preparedStatement, i, meroAcntDetails);
				}
				
				@Override
				public int getBatchSize() {
					return meroAcntDetails.size();
				}
				
			});
		}catch(Exception e) {
			logger.error("BankAccount updation failed for ::" + e.getMessage());
		}
	}
	
	private void setPreparedStmt(PreparedStatement preparedStatement, int i, List<MeroAcntVO> meroAcntVOList) throws SQLException{
		MeroAcntVo vo = meroAcntVOList.get(i);
		preparedStatement.setString(1, meroAcntVO.getTokenAccountNo());
		preparedStatement.setString(2, meroAcntVO.getRoutingNo());
		preparedStatement.setString(3, meroAcntVO.getBankAccountNo());
	
		}
	
	
	public JdbcTemplate getSageJdbcTemplate() {
		return sageJdbcTemplate;
	}

	public void setSageJdbcTemplate(JdbcTemplate sageJdbcTemplate) {
		this.sageJdbcTemplate = sageJdbcTemplate;
	}

	public String getSalUpdateStmt() {
		return salUpdateStmt;
	}

	public void setSalUpdateStmt(String salUpdateStmt) {
		this.salUpdateStmt = salUpdateStmt;
	}
	
	
}
