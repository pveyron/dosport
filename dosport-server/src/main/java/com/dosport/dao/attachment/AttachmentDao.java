package com.dosport.dao.attachment;

import org.springframework.stereotype.Repository;

import com.dosport.domain.attachment.Attachment;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 附件Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class AttachmentDao extends HibernateDao<Attachment, Long> {

}
