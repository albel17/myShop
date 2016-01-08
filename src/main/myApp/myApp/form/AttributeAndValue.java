package myApp.form;

import myApp.entity.AttributesEntity;

public class AttributeAndValue {
    private AttributesEntity attribute;
    private String value;

    public AttributeAndValue() {
    }

    public AttributeAndValue(AttributesEntity attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public AttributesEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributesEntity attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
