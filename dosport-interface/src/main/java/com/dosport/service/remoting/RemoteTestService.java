package com.dosport.service.remoting;

import java.io.Serializable;

import com.dosport.service.exception.ServiceException;

/**
 * 远程调用测试类.
 * 
 * @author pwl
 * 
 */
public interface RemoteTestService extends Serializable {

	public void testRemote() throws ServiceException;
}
