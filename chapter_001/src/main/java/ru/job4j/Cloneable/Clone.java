package ru.job4j.Cloneable;

public class Clone implements Cloneable {

    private String bla;

    private Obj obj;

    public Clone(String bla, Obj obj) {
        this.bla = bla;
        this.obj = obj;
    }

    public String getBla() {
        return bla;
    }

    public void setBla(String bla) {
        this.bla = bla;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Clone{");
        sb.append("bla='").append(bla).append('\'');
        sb.append('}').append(obj);
        return sb.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
