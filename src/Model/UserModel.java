package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserModel {
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty userApellidoP = new SimpleStringProperty();
    private final StringProperty userApellidoM = new SimpleStringProperty();
    private final StringProperty tipoDeChamba = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();

    public UserModel() {
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getUserApellidoP() {
        return userApellidoP.get();
    }

    public StringProperty userApellidoPProperty() {
        return userApellidoP;
    }

    public void setUserApellidoP(String userApellidoP) {
        this.userApellidoP.set(userApellidoP);
    }

    public String getUserApellidoM() {
        return userApellidoM.get();
    }

    public StringProperty userApellidoMProperty() {
        return userApellidoM;
    }

    public void setUserApellidoM(String userApellidoM) {
        this.userApellidoM.set(userApellidoM);
    }

    public String getTipoDeChamba() {
        return tipoDeChamba.get();
    }

    public StringProperty tipoDeChambaProperty() {
        return tipoDeChamba;
    }

    public void setTipoDeChamba(String tipoDeChamba) {
        this.tipoDeChamba.set(tipoDeChamba);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
