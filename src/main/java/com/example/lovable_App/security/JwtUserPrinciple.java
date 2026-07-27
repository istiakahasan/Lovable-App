package com.example.lovable_App.security;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

public record JwtUserPrinciple(Long userId,
                               String userName,
                               List<GrantedAuthority> authorities)  {


}
