package com.dosport.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dosport.remoting.httpinvoker.BaseRemotingServiceFactory;
import com.dosport.service.remoting.RemoteTestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class RemoteTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private BaseRemotingServiceFactory remotingServiceFactory;

	@Test
	public void mainTest() {
		try {
			this.remotingServiceFactory.getMainSiteService(RemoteTestService.class).testRemote();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
