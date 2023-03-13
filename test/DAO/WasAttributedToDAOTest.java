package DAO;

import Model.AgentPSS;
import Model.EntityPSS;
import PROV.DM.ProvWasAttributedTo;
import java.util.List;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author tassio
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WasAttributedToDAOTest {

    @Test
    public void test1deleteAll() throws InterruptedException {
        WasAttributedToDAO.getInstance().deleteAllWasAttributedTo();

        List<ProvWasAttributedTo> wats = WasAttributedToDAO.getInstance().getAll();

        Assert.assertTrue(wats.isEmpty());
    }

    @Test
    public void test2Save() throws InterruptedException {

        EntityPSS ent = new EntityPSS();
        ent.setTitle("Test");
        ent.setPrice(50);
        EntityPSSDAO.getInstance().save(ent);

        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        AgentPSSDAO.getInstance().save(agt);

        ProvWasAttributedTo wat = new ProvWasAttributedTo();
        wat.setEntity(ent);
        wat.setAgent(agt);

        WasAttributedToDAO.getInstance().save(wat);

        List<ProvWasAttributedTo> wats = WasAttributedToDAO.getInstance().getAll();

        Assert.assertNotNull(wats);

    }

    @Test
    public void test3GetWasAttributedTo() {

        List<ProvWasAttributedTo> wats = WasAttributedToDAO.getInstance().getAll();

        Assert.assertNotNull(wats);
    }

    @Test
    public void test4GetAll() {
        List<ProvWasAttributedTo> wats = WasAttributedToDAO.getInstance().getAll();

        Assert.assertFalse(wats.isEmpty());
    }

    @Test
    public void test5DeleteWasAttributedTo_int() {
        List<ProvWasAttributedTo> wats = WasAttributedToDAO.getInstance().getAll();

        WasAttributedToDAO.getInstance().deleteWasAttributedTo(wats.get(0).getIdWasAttributedTo());

        Assert.assertTrue(WasAttributedToDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void test6DeleteWasAttributedTo_WasAttributedTo() throws InterruptedException {

        EntityPSS ent = new EntityPSS();
        ent.setTitle("Test");
        ent.setPrice(50);
        EntityPSSDAO.getInstance().save(ent);

        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        AgentPSSDAO.getInstance().save(agt);

        ProvWasAttributedTo wat = new ProvWasAttributedTo();
        wat.setEntity(ent);
        wat.setAgent(agt);

        WasAttributedToDAO.getInstance().save(wat);

        WasAttributedToDAO.getInstance().deleteWasAttributedTo(wat);

        Assert.assertTrue(WasAttributedToDAO.getInstance().getAll().isEmpty());
    }

}
