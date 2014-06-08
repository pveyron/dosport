package com.dosport.dao.psn;

import org.springframework.stereotype.Repository;

import com.dosport.domain.psn.Person;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 人员信息Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class PersonDao extends HibernateDao<Person, Long> {

}
