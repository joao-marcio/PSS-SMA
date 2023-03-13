package DAO;

import Model.AgentPSS;
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
public class AgentPSSDAOTest {
    
    @Test
    public void test1deleteAll() throws InterruptedException {
        AgentPSSDAO.getInstance().deleteAllAgentPSS();
        
        List<AgentPSS> agts = AgentPSSDAO.getInstance().getAll();
        
        Assert.assertTrue(agts.isEmpty());
    }
    
    @Test
    public void test2Save() throws InterruptedException {
        
        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        
        AgentPSSDAO.getInstance().save(agt);
        
        List<AgentPSS> agts = AgentPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(agt.getName(), agts.get(0).getName());
        Assert.assertEquals(agt.getTypeAgent(), agts.get(0).getTypeAgent());
        
    }
    
    @Test
    public void test3GetAgentPSS() {
        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        
        List<AgentPSS> agts = AgentPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(agt.getName(), agts.get(0).getName());
    }
    
    @Test
    public void test4GetAll() {
        List<AgentPSS> agts = AgentPSSDAO.getInstance().getAll();
        
        Assert.assertFalse(agts.isEmpty());
    }
    
    @Test
    public void test5DeleteAgentPSS_int() {
        List<AgentPSS> agts = AgentPSSDAO.getInstance().getAll();
        
        AgentPSSDAO.getInstance().deleteAgentPSS(agts.get(0).getIdAgent());
        
        Assert.assertTrue(AgentPSSDAO.getInstance().getAll().isEmpty());
    }
    
    @Test
    public void test6DeleteAgentPSS_AgentPSS() throws InterruptedException {
        
        AgentPSS agt = new AgentPSS();
        agt.setName("Test");
        agt.setTypeAgent("Agent");
        
        AgentPSSDAO.getInstance().save(agt);
        
        AgentPSSDAO.getInstance().deleteAgentPSS(agt);
        
        Assert.assertTrue(AgentPSSDAO.getInstance().getAll().isEmpty());
    }
    
}
