package DAO;

import DB.PersistenceUtil;
import Model.ActivityPSS;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class ActivityPSSDAO implements Serializable {

    Logger logger = Logger.getLogger("DAO");
    public static ActivityPSSDAO activityPSSDAO;

    public static ActivityPSSDAO getInstance() {
        if (activityPSSDAO == null) {
            activityPSSDAO = new ActivityPSSDAO();
        }
        return activityPSSDAO;
    }

    private Session session;

    public void save(ActivityPSS activityPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            tx.rollback();
        } finally {
            logger.trace("Ended Method");
            session.close();
        }
    }

    public ActivityPSS getActivityPSS(int id) {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            ActivityPSS activityPSS = (ActivityPSS) session.get(ActivityPSS.class, new Integer(id));
            session.close();
            return activityPSS;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public List<ActivityPSS> getAll() {
        logger.trace("Start Method");
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvActivity");
            List<ActivityPSS> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public ActivityPSS deleteActivityPSS(int id) {
logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ActivityPSS activityPSS = (ActivityPSS) session.get(ActivityPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            tx.rollback();
        } finally {
            logger.trace("Ended Method");
            session.close();
        }
        return null;
    }

    public void deleteActivityPSS(ActivityPSS activityPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            tx.rollback();
        } finally {
            logger.trace("Ended Method");
            session.close();
        }
    }

    public void deleteAllActivityPSS() {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvActivity");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            tx.rollback();
        } finally {
            logger.trace("Ended Method");
            session.close();
        }
    }

}
