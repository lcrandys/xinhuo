package cn.xinhuo.entity;
import java.util.Set;

public class Model {
private String entityId;
private Set<Item> items;
public String getEntityId() {
        return entityId;
}
public void setEntityId(String entityId) {
        this.entityId = entityId;
}
public Set<Item>  getItems() {
        return items;
}
public void setItems(Set<Item>  items) {
        this.items = items;
}

}
