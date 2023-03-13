package Beans;

import DAO.ActivityPSSDAO;
import Model.ActivityPSS;
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
@ManagedBean(name = "activityBean")
@ViewScoped
public class ActivityPSSBean {

    ActivityPSS activity = new ActivityPSS();

    List activitys = new ArrayList();

    public ActivityPSSBean() {
        activitys = new ActivityPSSDAO().getAll();
        activity = new ActivityPSS();
    }

    public void record(ActionEvent actionEvent) {
        new ActivityPSSDAO().save(activity);
        activitys = new ActivityPSSDAO().getAll();
        activity = new ActivityPSS();

    }

    public void exclude(ActionEvent actionEvent) {
        new ActivityPSSDAO().deleteActivityPSS(activity);
        activitys = new ActivityPSSDAO().getAll();
        activity = new ActivityPSS();
    }

    //getters and setters
    public ActivityPSS getActivityPSS() {
        return activity;
    }

    public void setActivityPSS(ActivityPSS activity) {
        this.activity = activity;
    }

    public ActivityPSS getActivity() {
        return activity;
    }

    public void setActivity(ActivityPSS activity) {
        this.activity = activity;
    }

    public List getActivitys() {
        return activitys;
    }

    public void setActivitys(List activitys) {
        this.activitys = activitys;
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
