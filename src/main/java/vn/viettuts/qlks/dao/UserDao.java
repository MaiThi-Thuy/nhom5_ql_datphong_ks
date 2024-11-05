package vn.viettuts.qlks.dao;

import java.util.ArrayList;
import java.util.List;
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.entity.UserJSON;
import vn.viettuts.qlks.utils.FileUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
public class UserDao {
    private final static String USER_FILE = "user.json";
    private List<User> listUsers;
    private static final String AES = "AES";
    public UserDao() {
        this.listUsers = readListUsers();
        if (listUsers == null) {
            listUsers = new ArrayList<User>();
        }
    }

    /**
     * Lưu các đối tượng user vào file user.json
     * 
     * @param users
     */
    public void writeListUsers(List<User> users) {
        UserJSON userJSON = new UserJSON();
        userJSON.setUsers(users);
        FileUtils.writeJSONtoFile(USER_FILE, userJSON); // updated to JSON
    }

    /**
     * Đọc các đối tượng user từ file user.json
     * 
     * @return list user
     */
    public List<User> readListUsers() {
        List<User> list = new ArrayList<User>();
        UserJSON userJSON = FileUtils.readJSONFile(USER_FILE, UserJSON.class); // updated to JSON
        if (userJSON != null) {
            list = userJSON.getUsers();
        } else {
            System.out.println("not found");
        }
        return list;
    }
    public boolean checkUser(User user) {
        for (User u : listUsers) {
            if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
    public String getUserRole(User user) {
        for (User u : listUsers) {
            if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
                return u.getRole();
            }
        }
        return null;

    }
    public SecretKeySpec createSecretKey(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] key = md.digest(password.getBytes("UTF-8"));
        return new SecretKeySpec(key, AES);
    }
    public String encryptPassword(String password, String userPassword) throws Exception {
        SecretKeySpec secretKey = createSecretKey(userPassword);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedData);
    }
    public  String decryptPassword(String encryptedPassword, String userPassword) throws Exception {
        SecretKeySpec secretKey = createSecretKey(userPassword);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, "UTF-8");
    }

}
