package com.meroTokenization.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.meroTokenization.model.MeroAcntVO;

public class AccountReader implements ItemReader<List<MeroAcntVO>>{

	private Logger logger = LoggerFactBlockingQueue<E>(AccountReader.class);
	
	private BlockingQueue<MeroAcntVO> bankAcntQueue;
	
	private int timeOutSeconds = DEFAULT_TIMEOUT;
	
	private static final int DEFAULT_TIMEOUT = 10;
	
	int maxCount;
	
	public List<MeroAcntVO> read() throws InterruptedException{
		int counter = 0;
		
		List<MeroAcntVO> items = null;
		
		while(counter < maxCount) {
			MeroAcntVO meroAcntVo = bankAcntQueue.poll(timeoutSeconds, TimeUnit.SECONDS);
			
			if(meroAcntVo == null) {
				return items;
			}
			if(items == null) {
				items = new ArrayList<>();
			}
			items.add(meroAcntVo);
			counter++;
		}
		return items;
	}

	
	public BlockingQueue<MeroAcntVO> getBankAcntQueue() {
		return bankAcntQueue;
	}

	public void setBankAcntQueue(BlockingQueue<MeroAcntVO> bankAcntQueue) {
		this.bankAcntQueue = bankAcntQueue;
	}

	public int getTimeOutSeconds() {
		return timeOutSeconds;
	}

	public void setTimeOutSeconds(int timeOutSeconds) {
		this.timeOutSeconds = timeOutSeconds;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public static int getDefaultTimeout() {
		return DEFAULT_TIMEOUT;
	}
	
	
	
}
