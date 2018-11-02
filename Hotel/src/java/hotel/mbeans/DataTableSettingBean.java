package hotel.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author maclee
 */
@ManagedBean
@ViewScoped
public class DataTableSettingBean {
    private String searching = "false";

    public String getSearching() {
        return searching;
    }

    public void setSearching(String searching) {
        this.searching = searching;
    }
    
    
}
