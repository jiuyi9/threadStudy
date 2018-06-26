package com.quartz;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException {
		// jobDetail实例， 将该实例与 HelloJob class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1")
				.usingJobData("message", "hello my job1")
				.usingJobData("doubleKey", 10d)
				.usingJobData("floatKey", 3.14f)
				.usingJobData("msg", "set 实力")
				.usingJobData("dd", 66.6d)
				.build();
		System.out.println(jobDetail.getKey().getGroup());
		System.out.println(jobDetail.getKey().getName());
		System.out.println(jobDetail.getKey().getClass());
		
		Date date = new Date();
		date.setTime(date.getTime()+3000);
		Date edate = new Date();
		edate.setTime(date.getTime()+6000);
		
		// 创建一个Trigger实例，定义该job立即执行，并且每隔两秒钟重复执行一次，直到永远
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.usingJobData("message", "hello my trigger")
				.usingJobData("doubleKey",12d)
				.startAt(date)
				.endAt(edate)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();

		//创建Scheduler实例
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail , trigger);
	}
}
