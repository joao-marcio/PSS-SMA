package DAO;

import Model.ActivityPSS;
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
public class ActivityPSSDAOTest {
    
    @Test
    public void test1deleteAll() throws InterruptedException {
        ActivityPSSDAO.getInstance().deleteAllActivityPSS();
        
        List<ActivityPSS> acts = ActivityPSSDAO.getInstance().getAll();
        
        Assert.assertTrue(acts.isEmpty());
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
        
        List<ActivityPSS> acts = ActivityPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(act.getDescription(), acts.get(0).getDescription());
        
    }
    
    @Test
    public void test3GetActivityPSS() {
        ActivityPSS act = new ActivityPSS();
        act.setDescription("Test");
        
        List<ActivityPSS> acts = ActivityPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(act.getDescription(), acts.get(0).getDescription());
    }
    
    @Test
    public void test4GetAll() {
        List<ActivityPSS> acts = ActivityPSSDAO.getInstance().getAll();
        
        Assert.assertFalse(acts.isEmpty());
    }
    
    @Test
    public void test5DeleteActivityPSS_int() {
        List<ActivityPSS> acts = ActivityPSSDAO.getInstance().getAll();
        
        ActivityPSSDAO.getInstance().deleteActivityPSS(acts.get(0).getIdActivity());
        
        Assert.assertTrue(ActivityPSSDAO.getInstance().getAll().isEmpty());
    }
    
    @Test
    public void test6DeleteActivityPSS_ActivityPSS() throws InterruptedException {
        
        ActivityPSS act = new ActivityPSS();
        act.setDescription("Test");
        Date startTime = Date.from(Instant.now());
        act.setStartTime(startTime);
        Thread.sleep(5);
        Date endTime = Date.from(Instant.now());
        act.setEndTime(endTime);
        
        ActivityPSSDAO.getInstance().save(act);
        
        ActivityPSSDAO.getInstance().deleteActivityPSS(act);
        
        Assert.assertTrue(ActivityPSSDAO.getInstance().getAll().isEmpty());
    }
    
}
