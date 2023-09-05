package utils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static String encode(String plainTextPassword) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);
        return hashedPassword;
    }

    //kiem tra password nhap vao == passsword db
    public static boolean check(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
