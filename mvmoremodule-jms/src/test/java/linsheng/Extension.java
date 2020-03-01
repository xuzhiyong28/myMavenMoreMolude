package linsheng;

public class Extension {

    public Extension(String firstName, String lastName, String ext, String extType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ext = ext;
        this.extType = extType;
    }

    public Extension(String firstName, String lastName, String ext) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ext = ext;
    }

    public Extension() {
    }

    private String firstName;
    private String lastName;
    private String ext;
    private String extType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getExtType() {
        return extType;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }
}
