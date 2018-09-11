package com.meroTokenization.processor;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.meroTokenization.model.MeroAcntVO;

public class MeroAcntProcessor implements ItemProcessor<List<MeroAcntVO>, List<MeroAcntVO>> {

	final Logger logger = LoggerFactory.getLopgger(MeroAcntProcessor.class);
	
	public static final AtomicLong readCount = new AtomicLong(0);
	public static final AtomicLong processedCount = new AtomicLong(0);
	
	
	@Inject
	TokenizationAdapter tokenizationAdapter;
	
	@Override
	public List<MeroAcntVo> process(List<MeroAcntVO> meroAcntVOList) throws Exception{
		logger.info("**************I am in processsor************");
		
		List<MeroAcntVo> tokenizedAccountList = new ArrayList<>();
		if(meroAcntVOList != null && meroAcntVOList.isEmpty()) {
			logger.info("MeroAccount List is null");
		
					return tokenizedAccountList;
		
		}
		
//		Get the list of tokenized Account Numbers
		
		List<MeroAcntVO> tokenizedAccountNumbers = tokenizationAdapter.getTokenizedAccountNumbers(meroAcntVOList);
		
		if(tokenizedAccountNumbers != null && tokenizedAccountNumbers.isEmpty()) {
			return tokenizedAccountList;
		}
		return tokenizedAccountNumbers;
	}
}
