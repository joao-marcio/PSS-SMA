package DAO;

import Model.EntityPSS;
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
public class EntityPSSDAOTest {
    
    @Test
    public void test1deleteAll() throws InterruptedException {
        EntityPSSDAO.getInstance().deleteAllEntityPSS();
        
        List<EntityPSS> acts = EntityPSSDAO.getInstance().getAll();
        
        Assert.assertTrue(acts.isEmpty());
    }
    
    @Test
    public void test2Save() throws InterruptedException {
        
        EntityPSS ent = new EntityPSS();
        ent.setTitle("Test");
        ent.setPrice(50);
        
        EntityPSSDAO.getInstance().save(ent);
        
        List<EntityPSS> ents = EntityPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(ent.getTitle(), ents.get(0).getTitle());
        Assert.assertEquals(ent.getPrice(), ents.get(0).getPrice());
        
    }
    
    @Test
    public void test3GetEntityPSS() {
        EntityPSS ent = new EntityPSS();
        ent.setTitle("Test");
        
        List<EntityPSS> ents = EntityPSSDAO.getInstance().getAll();
        
        Assert.assertEquals(ent.getTitle(), ents.get(0).getTitle());
    }
    
    @Test
    public void test4GetAll() {
        List<EntityPSS> acts = EntityPSSDAO.getInstance().getAll();
        
        Assert.assertFalse(acts.isEmpty());
    }
    
    @Test
    public void test5DeleteEntityPSS_int() {
        List<EntityPSS> ents = EntityPSSDAO.getInstance().getAll();
        
        EntityPSSDAO.getInstance().deleteEntityPSS(ents.get(0).getIdEntity());
        
        Assert.assertTrue(EntityPSSDAO.getInstance().getAll().isEmpty());
    }
    
    @Test
    public void test6DeleteEntityPSS_EntityPSS() throws InterruptedException {
        
        EntityPSS ent = new EntityPSS();
        ent.setTitle("Test");
        ent.setPrice(50);
                
        EntityPSSDAO.getInstance().save(ent);
        
        EntityPSSDAO.getInstance().deleteEntityPSS(ent);
        
        Assert.assertTrue(EntityPSSDAO.getInstance().getAll().isEmpty());
    }
    
}
