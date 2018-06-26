package com.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronScheduler {
	public static void main(String[] args) throws SchedulerException {
		// jobDetail实例， 将该实例与 HelloJob class绑定
		JobDetail jobDetail = JobBuilder.newJob(CronJob.class).withIdentity("myJob")
				.build();
		// 创建一个Trigger实例，定义该job立即执行，并且每隔两秒钟重复执行一次，直到永远
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))//秒 分 时 日 月 星期 年 ，每隔5秒执行一次
				.build();
		//创建Scheduler实例
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail , trigger);	
	}
}
