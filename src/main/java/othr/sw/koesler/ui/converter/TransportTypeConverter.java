package othr.sw.koesler.ui.converter;

import othr.sw.koesler.entity.util.OrderType;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean
public class TransportTypeConverter {
    public SelectItem[] getTransportValues() {
        SelectItem[] items = new SelectItem[OrderType.values().length];
        int i = 0;
        for(OrderType orderType: OrderType.values()) {
            items[i++] = new SelectItem(orderType, orderType.getLabel());
        }
        return items;
    }
}
