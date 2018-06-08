package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Field {

    private int field;

    public Field() {

    }

    public Field(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
