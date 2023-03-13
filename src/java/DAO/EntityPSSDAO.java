package DAO;

import DB.PersistenceUtil;
import Model.EntityPSS;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class EntityPSSDAO {

    Logger logger = Logger.getLogger("DAO");
    public static EntityPSSDAO entityPSSDAO;

    public static EntityPSSDAO getInstance() {
        if (entityPSSDAO == null) {
            entityPSSDAO = new EntityPSSDAO();
        }
        return entityPSSDAO;
    }

    private Session session;

    public void save(EntityPSS entityPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(entityPSS);
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

    public EntityPSS getEntityPSS(int id) {
        logger.trace("Start Method");
        try {
            session = PersistenceUtil.getSession();
            EntityPSS entityPSS = (EntityPSS) session.get(EntityPSS.class, new Integer(id));
            session.close();
            return entityPSS;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public List<EntityPSS> getAll() {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvEntity");
            List<EntityPSS> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public EntityPSS deleteEntityPSS(int id) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            EntityPSS entityPSS = (EntityPSS) session.get(EntityPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(entityPSS);
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

    public void deleteEntityPSS(EntityPSS entityPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(entityPSS);
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

    public void deleteAllEntityPSS() {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvEntity");
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
