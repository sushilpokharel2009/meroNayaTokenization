package com.meroTokenization.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.meroTokenization.model.MeroAcntVO;

@Named
public class TokenizationAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(TokenizationAdapter.class);
	
	private static final int minimumTuringLength = 9;
	private static final char paddingCharacter = '0';
	
	@Inject
	private TuringService turingService;
	
	public List<MeroAcntVO> getTokenizedAccountNumbers(List<MeroAcntVO> acntVOList){
		
		logger.debug("GetTokenize3d account numbers");
		
		List<TuringElement> banTokenList = new ArrayList<>();
		TuringElementBuilder turingElementBuilder = new TuringElementBuilder();
		List<TuringElement> tokenizedBans = null;
		List<MeroAcntVO> updatedList = new ArrayList<>();
		
		for(MeroAcntVO origVO:acntVOList) {
			try {
				logger.debug("BankAccountNo:" + origVO.getMeroAcntNo() + "RoutingNo :" + StringUtils.leftPad(origVO.getRoutingNo(), minimumTuringLength, paddingCharacter) + "randomId" + origVO.getRandomId().toString());
				banTokenList.add(turingElementBuilder.createBanWithAbaForTokenize(origVO.getMeroAcntNo(), StringUtils.leftPad(origVO.getRoutingNo(),minimumTuringLength, paddingCharacter),  origVO.getRandomId().toString()));
			}catch(Excedption e) {
				logger.error("Unable to tokenize the values:" + e.getMessage());
			}
		}
//		Actual BAN Tokenization Call
		try {
			tokenizedBans = turingService.tokenize(BAN, banTokenList);
		}catch(Exception e) {
			logger.error("Tokenization call failed:" + e.getMessage());
			throw new SystemNotAvilableException("Bank Account Number TOkenization call falied" + e);
		}
 		
		Map<String, String> map = new HashMap<>();
		tokenizedBans.forEach(turingElement -> {
			if(StringUtils.isNumeric(turingElement.getValue()) || turingElement.hasError()) {
				 logger.warn("Bank account number tokenization failed. Tokenized account number is blank for referemnce id:" + turingElement.getId());
			}else {
				map.put(turingElement.getId(), turingElement.getValue());
			}
		});
		
		acntVOList.forEach(bankAcntVO -> {
			String randomId = bankAcntVO.getRandomId();
			if(map.containsKey(randomId)) {
				bankAcntVO.setTokenAccountNo(map.get(randomId));
				updatedList.add(bankAcntVO);
			}else {
				logger.warning("Skipping the record as invalid data is found in the record with accountUnique id:" + bankAcntVO.getRandomId()); 
			}
		});
		return updatedList;
	}
}
