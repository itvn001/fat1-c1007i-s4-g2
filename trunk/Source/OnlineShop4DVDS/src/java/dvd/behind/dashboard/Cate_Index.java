/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Categories;
import dvd.entity.CateType;
import dvd.libraries.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Cate_Index {

    private dvd.libraries.Permission pe = new Permission();

    public Cate_Index() {
        // dvd.libraries.Permission p = new Permission();
        //p.checkPermissionDisc();
        this.message = "";
    }
    private String idTpeCate = "0";
    private dvd.business.dashboard.Categories cateHand = new Categories();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdTpeCate() {
        return idTpeCate;
    }

    public void setIdTpeCate(String idTpeCate) {
        this.idTpeCate = idTpeCate;
    }
    private List<dvd.entity.Categories> listCategories;

    public List<dvd.entity.Categories> getListCategories() {
        this.listCategories = this.cateHand.listCategories_Main(this.idTpeCate);
        return listCategories;
    }

    public void setListCategories(List<dvd.entity.Categories> listCategories) {
        this.listCategories = listCategories;
    }
    private List<SelectItem> listCateType;

    public List<SelectItem> getListCateType() {
        this.listCateType = new ArrayList<SelectItem>();
        List<dvd.entity.CateType> typec = this.cateHand.listCateType();
        this.listCateType.add(new SelectItem(0, "all"));
        for (CateType cateType : typec) {
            this.listCateType.add(new SelectItem(cateType.getCateTypeID(), cateType.getCateTypeName()));
        }
        return listCateType;
    }
    private List<SelectItem> listTypeFormExtends;

    public List<SelectItem> getListTypeFormExtends() {
        this.listTypeFormExtends = new ArrayList<SelectItem>();
        List<dvd.entity.CateType> typec = this.cateHand.listCateType();
        this.listTypeFormExtends.add(new SelectItem(0, "--select--"));
        for (CateType cateType : typec) {
            this.listTypeFormExtends.add(new SelectItem(cateType.getCateTypeID(), cateType.getCateTypeName()));
        }
        return listTypeFormExtends;
    }

    public void setListTypeFormExtends(List<SelectItem> listTypeFormExtends) {
        this.listTypeFormExtends = listTypeFormExtends;
    }

    public void setListCateType(List<SelectItem> listCateType) {
        this.listCateType = listCateType;
    }

    public String changePublish(Boolean e) {
        if (e.equals(true)) {
            return "ON";
        } else {
            return "OFF";
        }
    }

    public void btncateonoff_Click(String id) {
        if (this.cateHand.publishCate(id) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue", "Update Cate Success", "", "");
        } else {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "", "Please try again !");
        }
    }

    public void changeTypeAdd(ValueChangeEvent e) {
        this.idCateType2 = e.getNewValue().toString();
    }
    private String idCateType2;

    public String getIdCateType2() {
        return idCateType2;
    }

    public void setIdCateType2(String idCateType2) {
        this.idCateType2 = idCateType2;
    }

    public void btnaddnewcate_Click() {
        if (this.idCateType2.equals("0")) {
            FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please Choice Cate Root", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            dvd.entity.Categories bs = new dvd.entity.Categories();
            bs.setCateTypeID(this.idCateType2);
            bs.setCateName(this.cateName);
            if (this.idStatus.equals("1")) {
                bs.setCateStatus(Boolean.TRUE);
            } else {
                bs.setCateStatus(Boolean.FALSE);
            }
            if (this.cateHand.CreateCategories(bs) == true) {
                this.message = dvd.libraries.UImessage.generalMessage("blue", "Add new Categories Success", "", "");
            } else {
                this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "", "Please try again!");
            }
        }
    }
    private String cateName;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    private String idStatus;

    public String getIdStatus() {
        return idStatus;
    }

    public List<SelectItem> forStaticCmbox() {
        List<SelectItem> g = new ArrayList<SelectItem>();
        g.add(new SelectItem(1, "ON"));
        g.add(new SelectItem(0, "OFF"));
        return g;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public void catettChange(ValueChangeEvent e) {
        this.idStatus = e.getNewValue().toString();
    }
    private static int idEdit = 0;
    private String idTypeEdit;

    public void editcate_Click(int id, String idType, String name) {
        Cate_Index.idEdit = id;
        this.idTypeEdit = idType;
        this.cateName = name;
    }

    public void btnupdatecate_Click() {
        if (!this.idTypeEdit.trim().equals("")) {
            if (this.idCateType2.equals("0")) {
                FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please Choice Cate Root", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                dvd.entity.Categories bs = new dvd.entity.Categories();
                bs.setCateID(Cate_Index.idEdit);
                bs.setCateName(cateName);
                if (this.idStatus.equals("1")) {
                    bs.setCateStatus(Boolean.TRUE);
                } else {
                    bs.setCateStatus(Boolean.FALSE);
                }
                bs.setCateTypeID(this.idTypeEdit);
                if (this.cateHand.UpdateCategories(bs) == true) {
                    this.message = dvd.libraries.UImessage.generalMessage("blue", "Update Categories Success", "", "");
                } else {
                    this.message = dvd.libraries.UImessage.generalMessage("red", "Error System", "", "Please try again!");
                }
            }
        } else {
            FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please Choice Item For Edit", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
