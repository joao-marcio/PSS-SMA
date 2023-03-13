package Beans;

import DAO.EntityPSSDAO;
import Model.EntityPSS;
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
@ManagedBean(name = "entityBean")
@ViewScoped
public class EntityPSSBean {

    EntityPSS entity = new EntityPSS();

    List entitys = new ArrayList();

    public EntityPSSBean() {
        entitys = new EntityPSSDAO().getAll();
        entity = new EntityPSS();
    }

    public void record(ActionEvent actionEvent) {
        new EntityPSSDAO().save(entity);
        entitys = new EntityPSSDAO().getAll();
        entity = new EntityPSS();

    }

    public void exclude(ActionEvent actionEvent) {
        new EntityPSSDAO().deleteEntityPSS(entity);
        entitys = new EntityPSSDAO().getAll();
        entity = new EntityPSS();
    }

    public EntityPSS getEntityPSS() {
        return entity;
    }

    public void setEntityPSS(EntityPSS entity) {
        this.entity = entity;
    }

    public EntityPSS getEntity() {
        return entity;
    }

    public void setEntity(EntityPSS entity) {
        this.entity = entity;
    }

    public List getEntitys() {
        return entitys;
    }

    public void setEntitys(List entitys) {
        this.entitys = entitys;
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
