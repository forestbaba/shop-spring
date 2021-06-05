package com.forestsoftware.usershop.utility;
import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class Workerclass {


    public static String getGravatar160pxUrl(String email) {
        Gravatar gravatar = new Gravatar();
        gravatar.setSize(160);
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
        return gravatar.getUrl(email);
    }

    public static String getGravatar80pxUrl(String email) {
        Gravatar gravatar = new Gravatar();
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
        return gravatar.getUrl(email);
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
