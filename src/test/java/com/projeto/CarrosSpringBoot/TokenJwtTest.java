package com.projeto.CarrosSpringBoot;

import com.projeto.CarrosSpringBoot.seguranca.jwt.JwtUtil;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosSpringBootApplication.class)
public class TokenJwtTest {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Test
    public void testToken() {

        // Le usuário
        UserDetails user = userDetailsService.loadUserByUsername("admin");
        assertNotNull(user);

        // Gera token
        String jwtToken = JwtUtil.createToken(user);
        System.out.println(jwtToken);
        assertNotNull(jwtToken);

        // Valida Token
        boolean ok = JwtUtil.isTokenValid(jwtToken);
        assertTrue(ok);

        // Valida login
        String login = JwtUtil.getLogin(jwtToken);
        assertEquals("admin", login);

        // Valida roles
        List<GrantedAuthority> roles = JwtUtil.getRoles(jwtToken);
        assertNotNull(roles);
        System.out.println(roles);
        String role = roles.get(0).getAuthority();
        assertEquals(role, "ROLE_ADMIN");
    }

}
