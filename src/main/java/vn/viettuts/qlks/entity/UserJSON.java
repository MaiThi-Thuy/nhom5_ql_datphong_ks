package vn.viettuts.qlks.entity;

import java.util.List;
import vn.viettuts.qlks.entity.User;
public class UserJSON {
    
    private List<User> user;

    public List<User> getUsers() {
        return user;
    }

    public void setUsers(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserXML [users=" + user + "]";
    }
}
