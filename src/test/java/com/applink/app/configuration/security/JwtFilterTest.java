package com.applink.app.configuration.security;

import com.applink.app.configuration.IT;
import com.applink.app.database.service.JwtService;
import com.applink.app.database.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RunWith(MockitoJUnitRunner.class)
class JwtFilterTest {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private UserService userService;

    @Mock
    private JwtService mockJwtService;

    @Mock
    private UserDetailsImp mockUserDetailsImp;


    @Test
    public void testDoFilterInternal_ValidToken() throws IOException, ServletException {
//        // Prepare mock data
//        String validToken = "your_valid_token";
//        String username = "test_user";
//        UserDetails userDetails = userService.getUserByUsername()
//
//        // Mock behavior
//        Mockito.when(mockJwtService.extractUsername(validToken)).thenReturn(username);
//        Mockito.when(mockJwtService.isValid(validToken, userDetails)).thenReturn(true);
//        Mockito.when(mockUserDetailsImp.loadUserByUsername(username)).thenReturn(userDetails);
//
//        // Mock request and response (optional, depending on your test scenario)
//        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
//        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
//
//        // Set authorization header
//        Mockito.when(mockRequest.getHeader("Authorization")).thenReturn("Bearer " + validToken);
//
//        FilterChain filterChain = mock(FilterChain.class);
//
//        // Execute filter
//        jwtFilter.doFilterInternal(mockRequest, mockResponse, filterChain);
//
//        // Verify security context (optional)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNotNull(authentication);
//        assertEquals(username, authentication.getName());
    }

}