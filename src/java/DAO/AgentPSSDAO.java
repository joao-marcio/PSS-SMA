package DAO;

import DB.PersistenceUtil;
import Model.AgentPSS;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class AgentPSSDAO {

    Logger logger = Logger.getLogger("DAO");
    public static AgentPSSDAO agentPSSDAO;

    public static AgentPSSDAO getInstance() {
        if (agentPSSDAO == null) {
            agentPSSDAO = new AgentPSSDAO();
        }
        return agentPSSDAO;
    }
    private Session session;

    public void save(AgentPSS agentPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(agentPSS);
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

    public AgentPSS getAgentPSS(int id) {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            AgentPSS agentPSS = (AgentPSS) session.get(AgentPSS.class, new Integer(id));
            session.close();
            return agentPSS;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
        logger.trace("Ended Method");
        return null;
    }

    public List<AgentPSS> getAll() {
        logger.trace("Start Method");
        try {
            session = (Session) PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvAgent");
            List<AgentPSS> list = query.list();
            return list;
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        } finally {
            logger.trace("Ended Method");
            session.close();
        }
        return null;
    }

    public AgentPSS deleteAgentPSS(int id) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            AgentPSS agentPSS = (AgentPSS) session.get(AgentPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(agentPSS);
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

    public void deleteAgentPSS(AgentPSS agentPSS) {
        logger.trace("Start Method");
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(agentPSS);
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

    public void deleteAllAgentPSS() {
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
