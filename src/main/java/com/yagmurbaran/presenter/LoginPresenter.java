package com.yagmurbaran.presenter;

import com.vaadin.flow.server.VaadinSession;
import com.yagmurbaran.view.LoginView;

public class LoginPresenter {

    public LoginPresenter(LoginView view) {
    }
    public void login(String username, String password) {

        if (authenticate(username, password)) {
            VaadinSession.getCurrent().setAttribute("user", username);
        } else {

        }
    }

    private boolean authenticate(String username, String password) {

        return "admin".equals(username) && "password".equals(password);
    }
}
