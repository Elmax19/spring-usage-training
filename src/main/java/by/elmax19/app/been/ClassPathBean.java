package by.elmax19.app.been;

public class ClassPathBean {
    public ClassPathBean() {
        System.out.println("-ClassPathBean-: Constructor invoked");
    }

    public void init() {
        System.out.println("-ClassPathBean-: Initialization.");
    }

    public void destroy() {
        System.out.println("-ClassPathBean-: Destruction.");
    }
}
