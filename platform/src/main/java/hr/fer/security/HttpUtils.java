package hr.fer.security;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpUtils {

    public static String decodeUsernameFromBasicAuth(HttpServletRequest request) {
        String basicAuth = request.getHeader("authorization");
        if (basicAuth != null && basicAuth.startsWith("Basic")) {
            String encodedUsernamePassword = basicAuth.substring("Basic ".length()).trim();
            byte[] decoded = Base64.getDecoder().decode(encodedUsernamePassword);
            String decodedUsernamePassword = new String(decoded, StandardCharsets.UTF_8);
            return decodedUsernamePassword.split(":")[0];
        }
        return null;
    }

}