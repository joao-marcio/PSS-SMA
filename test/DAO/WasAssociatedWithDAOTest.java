package DAO;

import Model.ActivityPSS;
import Model.AgentPSS;
import PROV.DM.ProvWasAssociatedWith;
import java.time.Instant;
import java.util.Date;
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
public class WasAssociatedWithDAOTest {

    @Test
    public void test1deleteAll() throws InterruptedException {
        WasAssociatedWithDAO.getInstance().deleteAllWasAssociatedWith();

        List<ProvWasAssociatedWith> waws = WasAssociatedWithDAO.getInstance().getAll();

        Assert.assertTrue(waws.isEmpty());
    }

    @Test
    public void test2Save() throws InterruptedException {

        ActivityPSS act = new ActivityPSS();
        act.setDescription("Test");
        Date startTime = Date.from(Instant.now());
        act.setStartTime(startTime);
        Thread.sleep(5);
        Date endTime = Date.from(Instant.now());
        act.setEndTime(endTime);
        ActivityPSSDAO.getInstance().save(act);

        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        AgentPSSDAO.getInstance().save(agt);

        ProvWasAssociatedWith waw = new ProvWasAssociatedWith();
        waw.setActivity(act);
        waw.setAgent(agt);
        waw.setPlan("Test");

        WasAssociatedWithDAO.getInstance().save(waw);

        List<ProvWasAssociatedWith> waws = WasAssociatedWithDAO.getInstance().getAll();

        Assert.assertEquals(waw.getPlan(), waws.get(0).getPlan());

    }

    @Test
    public void test3GetWasAssociatedWith() {

        List<ProvWasAssociatedWith> waws = WasAssociatedWithDAO.getInstance().getAll();

        Assert.assertNotNull(waws);
    }

    @Test
    public void test4GetAll() {
        List<ProvWasAssociatedWith> waws = WasAssociatedWithDAO.getInstance().getAll();

        Assert.assertFalse(waws.isEmpty());
    }

    @Test
    public void test5DeleteWasAssociatedWith_int() {
        List<ProvWasAssociatedWith> waws = WasAssociatedWithDAO.getInstance().getAll();

        WasAssociatedWithDAO.getInstance().deleteWasAssociatedWith(waws.get(0).getIdWasAssociatedWith());

        Assert.assertTrue(WasAssociatedWithDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void test6DeleteWasAssociatedWith_WasAssociatedWith() throws InterruptedException {

        ActivityPSS act = new ActivityPSS();
        act.setDescription("Test");
        Date startTime = Date.from(Instant.now());
        act.setStartTime(startTime);
        Thread.sleep(5);
        Date endTime = Date.from(Instant.now());
        act.setEndTime(endTime);
        ActivityPSSDAO.getInstance().save(act);

        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        AgentPSSDAO.getInstance().save(agt);

        ProvWasAssociatedWith waw = new ProvWasAssociatedWith();
        waw.setActivity(act);
        waw.setAgent(agt);
        waw.setPlan("Test");

        WasAssociatedWithDAO.getInstance().save(waw);

        WasAssociatedWithDAO.getInstance().deleteWasAssociatedWith(waw);

        Assert.assertTrue(WasAssociatedWithDAO.getInstance().getAll().isEmpty());
    }

}
