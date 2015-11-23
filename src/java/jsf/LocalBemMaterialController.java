package jsf;

import jpa.entities.LocalBemMaterial;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.LocalBemMaterialFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "localBemMaterialController")
@SessionScoped
public class LocalBemMaterialController implements Serializable {

    @EJB
    private jpa.session.LocalBemMaterialFacade ejbFacade;
    private List<LocalBemMaterial> items = null;
    private LocalBemMaterial selected;

    public LocalBemMaterialController() {
    }

    public LocalBemMaterial getSelected() {
        return selected;
    }

    public void setSelected(LocalBemMaterial selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getLocalBemMaterialPK().setIdBemMaterial(selected.getBemMaterial().getId());
        selected.getLocalBemMaterialPK().setIdLocal(selected.getLocal().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setLocalBemMaterialPK(new jpa.entities.LocalBemMaterialPK());
    }

    private LocalBemMaterialFacade getFacade() {
        return ejbFacade;
    }

    public LocalBemMaterial prepareCreate() {
        selected = new LocalBemMaterial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("LocalBemMaterialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("LocalBemMaterialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("LocalBemMaterialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LocalBemMaterial> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<LocalBemMaterial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LocalBemMaterial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LocalBemMaterial.class)
    public static class LocalBemMaterialControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LocalBemMaterialController controller = (LocalBemMaterialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "localBemMaterialController");
            return controller.getFacade().find(getKey(value));
        }

        jpa.entities.LocalBemMaterialPK getKey(String value) {
            jpa.entities.LocalBemMaterialPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.LocalBemMaterialPK();
            key.setIdBemMaterial(Integer.parseInt(values[0]));
            key.setIdLocal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.LocalBemMaterialPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdBemMaterial());
            sb.append(SEPARATOR);
            sb.append(value.getIdLocal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LocalBemMaterial) {
                LocalBemMaterial o = (LocalBemMaterial) object;
                return getStringKey(o.getLocalBemMaterialPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LocalBemMaterial.class.getName()});
                return null;
            }
        }

    }

}
