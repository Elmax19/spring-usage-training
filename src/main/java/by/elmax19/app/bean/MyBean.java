package by.elmax19.app.bean;

public class MyBean {
    private String actionMessage;

    public MyBean(String actionMessage) {
        this.actionMessage = actionMessage;
        System.out.println(this.actionMessage);
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
        System.out.println("-MyBean-: Message Setter invoked.");
    }

    public void init() {
        System.out.println("-MyBean-: Initialization.");
    }

    public void destroy() {
        System.out.println("-MyBean-: Destruction.");
    }
}
