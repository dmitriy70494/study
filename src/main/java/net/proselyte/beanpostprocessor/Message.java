package net.proselyte.beanpostprocessor;

public class Message {
    private String message;

    public String getMessage() {
        return "Message: " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        System.out.println("Bean is in init phase.");
    }

    public void destroy() {
        System.out.println("Bean will be destroyed now");
    }
}
