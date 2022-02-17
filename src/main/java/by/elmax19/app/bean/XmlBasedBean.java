package by.elmax19.app.bean;

public class XmlBasedBean {
    private String actionMessage;

    public XmlBasedBean(String actionMessage) {
        this.actionMessage = actionMessage;
        System.out.println(this.actionMessage);
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
        System.out.println("-XmlBasedBean-: Message Setter invoked.");
    }

    public void init() {
        System.out.println("-XmlBasedBean-: Initialization.");
    }

    public void destroy() {
        System.out.println("-XmlBasedBean-: Destruction.");
    }
}
