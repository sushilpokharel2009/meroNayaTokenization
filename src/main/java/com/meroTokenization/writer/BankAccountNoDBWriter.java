package com.meroTokenization.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.meroTokenization.DAO.MeroAcntDAO;
import com.meroTokenization.model.MeroAcntVO;

public class BankAccountNoDBWriter implements ItemWriter<List<MeroAcntVO>>{
	private static final Logger logger = LoggerFactory.getLogger(BankAccountNoDBWriter.class);
	
	private MeroAcntDAO meroAcntDao;

	public MeroAcntDAO getMeroAcntDao() {
		return meroAcntDao;
	}

	public void setMeroAcntDao(MeroAcntDAO meroAcntDao) {
		this.meroAcntDao = meroAcntDao;
	}

	@Override
	public void write(List<? extends List<MeroAcntVO>> items) throws Exception{
		
		List<MeroAcntVO> bankAccountListToBeUpdated = new ArrayList<>();
		
		for(int i = 0; i < items.size(); i++) {
			bankAccountListToBeUpdated.addAll(items.get(i));
		}
		
		try {
			meroAcntDao.updateAcntDetails(bankAccountListToBeUpdated);
		}(Exception e){
			logger.info("Error occured while inserrting the record to PAMS" + e);
		}
		
	}
	
	
}
