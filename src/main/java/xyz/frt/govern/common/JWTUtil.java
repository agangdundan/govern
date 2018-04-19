package xyz.frt.govern.common;


import io.jsonwebtoken.*;
import org.apache.shiro.codec.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-09-2018
 * @description
 */
public class JWTUtil {

    /**
     * 设置token过期时间为5分钟
     */
    private static final long EXPIRE_TIME = 1 * 60 * 60 * 1000;

    private static final String JWT_SECRET = "922F0EAD90889E9BB3A2A383F9CFBEB8";

    private static final byte[] encodeKey = Base64.decode(JWT_SECRET);

    private static final SecretKey secretKey = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");

    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    /**
     * Creates a jwt
     *
     * @param id
     * @param username
     * @return
     */
    public static String createJwt(String id, String username) {
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(now.getTime() + EXPIRE_TIME);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuer("xyz.frt")
                .setIssuedAt(now)
                .setExpiration(exp)
                .claim("username", username)
                .claim("id", id)
                .signWith(algorithm, secretKey);

        return builder.compact();
    }

    /**
     * Verifies a given jwt to map
     *
     * @param jwt
     * @return
     */
    public static Map<String, Object> parserJwt(String jwt) throws SignatureException {
        Map<String, Object> map = new HashMap<>();
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        map.put(AppConst.KEY_ID, claims.get(AppConst.KEY_ID));
        map.put(AppConst.KEY_USERNAME, claims.get(AppConst.KEY_USERNAME));
        return map;
    }

}
