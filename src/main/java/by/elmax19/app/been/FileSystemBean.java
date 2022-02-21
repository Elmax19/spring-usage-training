package by.elmax19.app.been;

public class FileSystemBean extends Bean {
    public FileSystemBean() {
        System.out.println("-FileSystemBean-: Constructor invoked");
    }

    public void init() {
        System.out.println("-FileSystemBean-: Initialization.");
    }

    public void destroy() {
        System.out.println("-FileSystemBean-: Destruction.");
    }
}
