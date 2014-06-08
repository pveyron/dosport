package com.dosport.dao.activity;

import org.springframework.stereotype.Repository;

import com.dosport.domain.activity.ActivityFile;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 活动项目附件Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class ActivityFileDao extends HibernateDao<ActivityFile, Long> {

}
