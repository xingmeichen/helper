package com.mabel.pojo.form.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-05 15:51
 **/
@Data
@Accessors(chain = true)
public class LoginForm {

    public static final Map<Integer, String> REGISTER_ERROR = Maps.newHashMap();

    @NotNull
    private String userName;
    @NotNull
    private String password;

    /**
     * To encrypt password
     * */
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12, new SecureRandom("Helper_Encrypt_Salt".getBytes())));
    }

    /**
     * To check if enterPassword is equal to originPassword
     * */
    public static boolean checkPassword(String enterPassword, String originEncryptPassword) {
        return BCrypt.checkpw(enterPassword, originEncryptPassword);
    }

    public static String generateToken(Integer userId) {
        Algorithm algorithm = Algorithm.HMAC256("com.mabel.Helper.Secret");
        String token = JWT.create().withIssuer("com.mabel.Helper.Secret")
                .withClaim("loginTime", new Date())
                .withClaim("userId", userId)
                .sign(algorithm);
        return token;
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJsb2dpblRpbWUiOjE1NTUxMzM1NDcsImlzcyI6ImNvbS5tYWJlbC5IZWxwZXIuU2VjcmV0IiwidXNlcklkIjoxfQ" +
                ".s1qgE-Knp6IrVkvvog9hbgTI280N54_K6-Fa_7XIP0o";

        String encryptPassword = encryptPassword("abc567yz");
        System.out.println(encryptPassword);
    }
}