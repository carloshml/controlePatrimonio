package jsf;

import jpa.entities.EstadoConservacaoPatrimonio;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.EstadoConservacaoPatrimonioFacade;

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

@ManagedBean(name = "estadoConservacaoPatrimonioController")
@SessionScoped
public class EstadoConservacaoPatrimonioController implements Serializable {

    @EJB
    private jpa.session.EstadoConservacaoPatrimonioFacade ejbFacade;
    private List<EstadoConservacaoPatrimonio> items = null;
    private EstadoConservacaoPatrimonio selected;

    public EstadoConservacaoPatrimonioController() {
    }

    public EstadoConservacaoPatrimonio getSelected() {
        return selected;
    }

    public void setSelected(EstadoConservacaoPatrimonio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getEstadoConservacaoPatrimonioPK().setIdEstadoConservacao(selected.getEstadoConservacao().getId());
        selected.getEstadoConservacaoPatrimonioPK().setIdPatrimonio(selected.getPatrimonio().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setEstadoConservacaoPatrimonioPK(new jpa.entities.EstadoConservacaoPatrimonioPK());
    }

    private EstadoConservacaoPatrimonioFacade getFacade() {
        return ejbFacade;
    }

    public EstadoConservacaoPatrimonio prepareCreate() {
        selected = new EstadoConservacaoPatrimonio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("EstadoConservacaoPatrimonioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("EstadoConservacaoPatrimonioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("EstadoConservacaoPatrimonioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EstadoConservacaoPatrimonio> getItems() {
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

    public List<EstadoConservacaoPatrimonio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EstadoConservacaoPatrimonio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EstadoConservacaoPatrimonio.class)
    public static class EstadoConservacaoPatrimonioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoConservacaoPatrimonioController controller = (EstadoConservacaoPatrimonioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoConservacaoPatrimonioController");
            return controller.getFacade().find(getKey(value));
        }

        jpa.entities.EstadoConservacaoPatrimonioPK getKey(String value) {
            jpa.entities.EstadoConservacaoPatrimonioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.EstadoConservacaoPatrimonioPK();
            key.setIdPatrimonio(Integer.parseInt(values[0]));
            key.setIdEstadoConservacao(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.EstadoConservacaoPatrimonioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPatrimonio());
            sb.append(SEPARATOR);
            sb.append(value.getIdEstadoConservacao());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstadoConservacaoPatrimonio) {
                EstadoConservacaoPatrimonio o = (EstadoConservacaoPatrimonio) object;
                return getStringKey(o.getEstadoConservacaoPatrimonioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstadoConservacaoPatrimonio.class.getName()});
                return null;
            }
        }

    }

}
