package Beans;

import DAO.WasAssociatedWithDAO;
import PROV.DM.ProvWasAssociatedWith;
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
@ManagedBean(name = "wasAssociatedWithBean")
@ViewScoped
public class WasAssociatedWithBean {

    ProvWasAssociatedWith wasAssociatedWith = new ProvWasAssociatedWith();

    List wasAssociatedWiths = new ArrayList();

    public WasAssociatedWithBean() {
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new ProvWasAssociatedWith();
    }

    public void record(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().save(wasAssociatedWith);
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new ProvWasAssociatedWith();

    }

    public void exclude(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().deleteWasAssociatedWith(wasAssociatedWith);
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new ProvWasAssociatedWith();
    }

    public ProvWasAssociatedWith getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    public void setWasAssociatedWith(ProvWasAssociatedWith wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    public ProvWasAssociatedWith getAgent() {
        return wasAssociatedWith;
    }

    public void setAgent(ProvWasAssociatedWith wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    public List getAgents() {
        return wasAssociatedWiths;
    }

    public void setAgents(List wasAssociatedWiths) {
        this.wasAssociatedWiths = wasAssociatedWiths;
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
