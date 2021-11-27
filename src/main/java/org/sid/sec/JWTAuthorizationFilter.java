package org.sid.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    	String jwt = request.getHeader(SecurityParams.HEADER_STRING);
    	if(jwt==null || jwt.startsWith(SecurityParams.TOKEN_PREFIX))
    	{
    		filterChain.doFilter(request, response);
    		return;
    	}
    	Claims claims = Jwts.parser()
    			.setSigningKey(SecurityParams.SECRET)
    			.parseClaimsJws(jwt.replace(SecurityParams.TOKEN_PREFIX, ""))
    			.getBody();
    	String username=claims.getSubject();
    	ArrayList<Map<String,String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
    	Collection<GrantedAuthority> authorities = new ArrayList<>();
    	roles.forEach(r->{
    		authorities.add(new SimpleGrantedAuthority(r.get("authority")));
    	});
    	UsernamePasswordAuthenticationToken authenticationUser =
    			new UsernamePasswordAuthenticationToken(username, null,authorities);
    	SecurityContextHolder.getContext().setAuthentication(authenticationUser);
    	filterChain.doFilter(request,response);
    	
       

    }
}
