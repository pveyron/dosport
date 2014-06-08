package com.dosport.dao.activity;

import org.springframework.stereotype.Repository;

import com.dosport.domain.activity.Activity;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 发布的运动项目Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class ActivityDao extends HibernateDao<Activity, Long> {

}
