package com.yagmurbaran.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public LoginView(AuthenticationManager authenticationManager) {

        addClassName("login-view");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login");

        loginForm.addLoginListener(event -> {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                event.getUsername(),
                                event.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                UI.getCurrent().navigate("main");
            } catch (AuthenticationException ex) {
                Notification.show("Invalid credentials", 3000, Notification.Position.TOP_CENTER);
                loginForm.setError(true);
            }
        });

        add(
                new H1("Employee Management"),
                loginForm
        );
    }
}
