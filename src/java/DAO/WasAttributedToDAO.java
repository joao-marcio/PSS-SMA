package DAO;

import DB.PersistenceUtil;
import PROV.DM.ProvWasAttributedTo;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class WasAttributedToDAO {

    Logger logger = Logger.getLogger("DAO");
    public static WasAttributedToDAO wasAttributedToPSSDAO;

    public static WasAttributedToDAO getInstance() {
        if (wasAttributedToPSSDAO == null) {
            wasAttributedToPSSDAO = new WasAttributedToDAO();
        }
        return wasAttributedToPSSDAO;
    }
    private Session session;

    public void save(ProvWasAttributedTo wasAttributedTo) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAttributedTo);
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

    public ProvWasAttributedTo getWasAttributedTo(int id) {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAttributedTo wasAttributedTo = (ProvWasAttributedTo) session.get(ProvWasAttributedTo.class, new Integer(id));
            session.close();
            return wasAttributedTo;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public List<ProvWasAttributedTo> getAll() {
        logger.trace("Start Method");
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvWasAttributedTo");
            List<ProvWasAttributedTo> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public ProvWasAttributedTo deleteWasAttributedTo(int id) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAttributedTo wasAttributedTo = (ProvWasAttributedTo) session.get(ProvWasAttributedTo.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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

    public void deleteWasAttributedTo(ProvWasAttributedTo wasAttributedTo) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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

    public void deleteAllWasAttributedTo() {
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
