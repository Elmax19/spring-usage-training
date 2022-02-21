package by.elmax19.app.been;

public class AnotherClassPathBean extends Bean {
    public AnotherClassPathBean() {
        System.out.println("-AnotherClassPathBean-: Constructor invoked");
    }

    public void init() {
        System.out.println("-AnotherClassPathBean-: Initialization.");
    }

    public void destroy() {
        System.out.println("-AnotherClassPathBean-: Destruction.");
    }
}
