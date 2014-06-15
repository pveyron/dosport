package com.dosport.service.activity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dosport.dao.activity.ActivityDao;
import com.dosport.domain.activity.Activity;
import com.dosport.domain.activity.ActivityForm;
import com.dosport.hibernate.domain.Page;
import com.dosport.remoting.httpinvoker.Remote;
import com.dosport.security.utils.SecurityUtils;
import com.dosport.service.exception.ServiceException;
import com.dosport.service.psn.PersonManager;
import com.dosport.system.utils.CoordinateUtils;
import com.dosport.system.utils.JacksonUtils;
import com.dosport.system.utils.LogUtils;

/**
 * 发布运动ServiceImpl.
 * 
 * @author pwl
 * 
 */
@Service("activityService")
@Remote(remoteInterface = ActivityService.class)
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4494958730579642362L;

	private final static String[] FILTER_PROPERTIES_LIST = new String[] { "longitude", "latitude", "busStop", "bus",
			"createDate", "note", "status" };

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private PersonManager personManager;

	@Override
	public void addActivity(ActivityForm form) throws ServiceException {

		try {
			Activity activity = new Activity();
			activity.setTitle(form.getTitle());
			activity.setSportId(form.getSportId());
			activity.setSportName(form.getSportName());
			activity.setPsnId(SecurityUtils.getCurrentPsnId());
			activity.setStartDate(form.getStartDate());
			activity.setEndDate(form.getEndDate());
			activity.setAddress(form.getAddress());
			Map<String, Double> map = CoordinateUtils.addrToCoordinate(form.getAddress());
			activity.setLongitude(map.get("longtitude"));
			activity.setLatitude(map.get("latitude"));
			activity.setBusStop(form.getBusStop());
			activity.setBus(form.getBus());
			activity.setCreateDate(new Date());
			activity.setNote(form.getNote());
			activity.setStatus(1);
			activity.setParticipatorCount(1);
			this.activityDao.save(activity);
		} catch (Exception e) {
			LogUtils.error(e, "保存发布的运动项目出现异常");
			throw new ServiceException(e);
		}
	}

	@Override
	public String getActivity(ActivityForm form, Page<Activity> page) throws ServiceException {

		String activityJson = null;
		try {
			List<Activity> activityList = this.activityDao.queryActivityByPage(form, page.getFirst(),
					page.getPageSize());
			if (CollectionUtils.isNotEmpty(activityList)) {
				for (Activity activity : activityList) {
					activity.setPsnViewName(this.personManager.getPsnVieName(activity.getPsnId()));
					activity.setDistinct(CoordinateUtils.getDistance(form.getLongitude(), form.getLatitude(),
							activity.getLongitude(), activity.getLatitude()));
				}
				activityJson = JacksonUtils.filterObj2Json(activityList, FILTER_PROPERTIES_LIST);
			}
		} catch (Exception e) {
			LogUtils.error(e, "查询附近的运动出现异常");
			throw new ServiceException(e);
		}
		return activityJson;
	}
}
