package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob implements Job {
	
	private String msg ;
	private Double dd ;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Double getDd() {
		return dd;
	}

	public void setDd(Double dd) {
		this.dd = dd;
	}
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("not time is :"+ sf.format(date));
		Trigger currentTriger = context.getTrigger();
		System.out.println("start time:"+currentTriger.getStartTime());
		System.out.println("end time:"+currentTriger.getEndTime());
		
		JobKey key = context.getJobDetail().getKey();
		System.out.println("group:"+key.getGroup()  +"name:"+  key.getName());
		TriggerKey tKey = context.getTrigger().getKey();
		System.out.println("trigger group:"+tKey.getGroup() + "trigger name : " + tKey.getName() );
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		JobDataMap tDataMap = context.getTrigger().getJobDataMap();
		
		String message = dataMap.getString("message");
		Double doubleValue = dataMap.getDouble("doubleKey");
		Float floatValue = dataMap.getFloat("floatKey");
		System.out.println(message);
		System.out.println(doubleValue);
		System.out.println(floatValue);
		
		String trMsg = tDataMap.getString("message");
		Double trDouble = tDataMap.getDouble("doubleKey");
		
		System.out.println(trMsg);
		System.out.println(trDouble);
		
		System.out.println("set方法获取："+msg);
		System.out.println("set方法获取："+dd);
	}

}
