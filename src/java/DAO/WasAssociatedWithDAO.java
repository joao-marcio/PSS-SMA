package DAO;

import DB.PersistenceUtil;
import PROV.DM.ProvWasAssociatedWith;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class WasAssociatedWithDAO {

    Logger logger = Logger.getLogger("DAO");
    public static WasAssociatedWithDAO wasAssociatedWithPSSDAO;

    public static WasAssociatedWithDAO getInstance() {
        if (wasAssociatedWithPSSDAO == null) {
            wasAssociatedWithPSSDAO = new WasAssociatedWithDAO();
        }
        return wasAssociatedWithPSSDAO;
    }

    private Session session;

    public void save(ProvWasAssociatedWith wasAssociatedWith) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAssociatedWith);
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

    public ProvWasAssociatedWith getWasAssociatedWith(int id) {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAssociatedWith wasAssociatedWith = (ProvWasAssociatedWith) session.get(ProvWasAssociatedWith.class, new Integer(id));
            session.close();
            return wasAssociatedWith;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public List<ProvWasAssociatedWith> getAll() {
        logger.trace("Start Method");
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvWasAssociatedWith");
            List<ProvWasAssociatedWith> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public ProvWasAssociatedWith deleteWasAssociatedWith(int id) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAssociatedWith wasAssociatedWith = (ProvWasAssociatedWith) session.get(ProvWasAssociatedWith.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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

    public void deleteWasAssociatedWith(ProvWasAssociatedWith wasAssociatedWith) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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

    public void deleteAllWasAssociatedWith() {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvAgent");
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
