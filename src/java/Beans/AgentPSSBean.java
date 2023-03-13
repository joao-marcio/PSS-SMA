package Beans;

import DAO.AgentPSSDAO;
import Model.AgentPSS;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "agentBean")
@ViewScoped
public class AgentPSSBean {

    AgentPSS agent = new AgentPSS();

    List agents = new ArrayList();

    public AgentPSSBean() {
        agents = new AgentPSSDAO().getAll();
        agent = new AgentPSS();
    }

    public void record(ActionEvent actionEvent) {
        new AgentPSSDAO().save(agent);
        agents = new AgentPSSDAO().getAll();
        agent = new AgentPSS();

    }

    public void exclude(ActionEvent actionEvent) {
        new AgentPSSDAO().deleteAgentPSS(agent);
        agents = new AgentPSSDAO().getAll();
        agent = new AgentPSS();
    }

    public AgentPSS getAgentPSS() {
        return agent;
    }

    public void setAgentPSS(AgentPSS agent) {
        this.agent = agent;
    }

    public AgentPSS getAgent() {
        return agent;
    }

    public void setAgent(AgentPSS agent) {
        this.agent = agent;
    }

    public List getAgents() {
        return agents;
    }

    public void setAgents(List agents) {
        this.agents = agents;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

}
