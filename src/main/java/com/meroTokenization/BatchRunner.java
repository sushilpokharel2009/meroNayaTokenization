package com.meroTokenization;

@PropertySource("classdpath:application.properties")
@ImportREsource("batch-job.xml")
public class BatchRunner extends SpringBatchRunner{

	public static void main(String[] args) throws JobExecuionException{
		
		executeJob(BatchRunner.class);
	}

}
