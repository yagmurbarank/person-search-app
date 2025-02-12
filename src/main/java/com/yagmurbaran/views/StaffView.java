package com.yagmurbaran.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.yagmurbaran.entity.Staff;
import com.yagmurbaran.services.StaffService;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Person")
@Route("staff")
@Uses(Icon.class)
public class StaffView extends Div {

    private Grid<Staff> grid;

    private final Filters filters;
    private final StaffService staffService;

    public StaffView(StaffService StaffService) {
        this.staffService = StaffService;
        setSizeFull();
        addClassNames("person-view");

        filters = new Filters(this::refreshGrid);
        VerticalLayout layout = new VerticalLayout(filters, createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    public static class Filters extends Div implements Specification<Staff> {

        private final TextField name = new TextField("Name");


        public Filters(Runnable onSearch) {

            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);
            name.setPlaceholder("First or last name");

            // Action buttons
            Button resetBtn = new Button("Reset");
            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            resetBtn.addClickListener(e -> {
                name.clear();
                onSearch.run();
            });
            Button searchBtn = new Button("Search");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> onSearch.run());

            Div actions = new Div(resetBtn, searchBtn);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("actions");

            add(name, actions);
        }


        @Override
        public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (!name.isEmpty()) {
                String lowerCaseFilter = name.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }


    }

    private Component createGrid() {
        grid = new Grid<>(Staff.class, false);
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);

        grid.setItems(query -> staffService.list(VaadinSpringDataHelpers.toSpringPageRequest(query), filters)
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

}
