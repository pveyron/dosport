package com.dosport.dao.psn;

import org.springframework.stereotype.Repository;

import com.dosport.domain.psn.PsnActivity;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 人员参与的活动.
 * 
 * @author pwl
 * 
 */
@Repository
public class PsnActivityDao extends HibernateDao<PsnActivity, Long> {

}
