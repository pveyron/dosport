package com.dosport.service.remoting;

import org.springframework.stereotype.Service;

import com.dosport.remoting.httpinvoker.Remote;
import com.dosport.system.exception.ServiceException;

/**
 * 远程调用测试Impl.
 * 
 * @author pwl
 * 
 */
@Service("remoteTestService")
@Remote(remoteInterface = RemoteTestService.class)
public class RemoteTestServiceImpl implements RemoteTestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2554109782119943369L;

	@Override
	public void testRemote() throws ServiceException {

		System.out.println("remote success");
	}

}
