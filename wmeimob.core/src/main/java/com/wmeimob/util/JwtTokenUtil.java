package com.wmeimob.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_ID = "sub_id";
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    private String header;
    private String secret;
    private Long expiration;
    
    public JwtTokenUtil() {}
    
    public JwtTokenUtil(String header, String secret, Long expiration) {
    	this.header = header;
    	this.secret = secret;
    	this.expiration = expiration;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    public String getIdFromToken(String token) {
        String Id = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            Id = (String) claims.get(CLAIM_KEY_ID);
        } catch (Exception e) {
        	Id = null;
        }
        return Id;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(JwtUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, user.getId());
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

	public Boolean validateToken(String token, JwtUser user) {
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}

