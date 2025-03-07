package com.yagmurbaran.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Main Layout")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H3 logo = new H3("Employee Management");

        com.vaadin.flow.component.avatar.Avatar avatar = new com.vaadin.flow.component.avatar.Avatar("Yagmur");

        MenuBar profileMenu = new MenuBar();
        MenuItem profile = profileMenu.addItem(avatar);
        profile.getSubMenu().addItem("Settings");
        profile.getSubMenu().addItem("Logout");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, profileMenu);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();


        addToNavbar(header);
    }

    private void createDrawer() {
        com.vaadin.flow.component.sidenav.SideNav nav = new com.vaadin.flow.component.sidenav.SideNav();

        nav.addItem(new com.vaadin.flow.component.sidenav.SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create()));
        nav.addItem(new com.vaadin.flow.component.sidenav.SideNavItem("Staff", StaffView.class, VaadinIcon.USERS.create()));
        nav.addItem(new com.vaadin.flow.component.sidenav.SideNavItem("Hello", HelloWorldView.class, VaadinIcon.SMILEY_O.create()));
        nav.addItem(new com.vaadin.flow.component.sidenav.SideNavItem("About", AboutView.class, VaadinIcon.INFO_CIRCLE.create()));

        addToDrawer(nav);
        nav.setSizeFull();
    }

    private void navigateTo(Class<? extends com.vaadin.flow.component.Component> targetView) {
        getUI().ifPresent(ui -> ui.navigate(targetView));
    }
}
