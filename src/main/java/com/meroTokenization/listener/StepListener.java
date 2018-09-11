package com.meroTokenization.listener;

import java.util.logging.Logger;

public class StepListener extends ItemListenerSupport implements StepExecutionLisatener{
	
	private static Logger logger = LoggerFactory.getLogger(StepListener.class);
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
	
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution){
	
		logger.info("**************************");
		logger.info("TOPtal commit count:  {}", stepExecution.getCommitCount());
		logger.info("**********************");
		
		return stepExecution.getExitStatus();
	}
	
	@Override
	public void onReadError(Exception ex) {
		
		logger.error("Error while fetching from file", ex);
		super.onReadError(ex);
	}
	
	@Override
	public void onWriteError(Exception ex, List item) {
		
		logger.error("Error while processing record", ex);
		super.onWriteError(ex,item);
	}

}
