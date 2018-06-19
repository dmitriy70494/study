package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Entries {

    private List<Field> entry;

    public Entries() { }

    public Entries(List<Field> entry) {
        this.entry = entry;
    }

    public List<Field> getEntry() {
        return entry;
    }

    public void setEntry(List<Field> entry) {
        this.entry = entry;
    }
}
