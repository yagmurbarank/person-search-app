package com.yagmurbaran.view;

import com.github.javaparser.quality.Nullable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.yagmurbaran.model.Staff;
import com.yagmurbaran.presenter.StaffPresenter;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@PageTitle("Staff")
@Route("staff")
@Uses(Icon.class)
public class StaffView extends Div {

    private Grid<Staff> grid;
    private final Filters filters;
    private final StaffPresenter presenter;

    public StaffView(StaffPresenter presenter) {
        this.presenter = presenter;
        this.filters = new Filters(this::refreshGrid);
        setSizeFull();
        addClassNames("person-view");

        VerticalLayout layout = new VerticalLayout(filters, createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);

        // Add Refresh Button to bottom-right corner
        add(createRefreshButton());
    }

    private Component createGrid() {
        grid = new Grid<>(Staff.class, false);
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        // Add delete button to the grid
        grid.addComponentColumn(staff -> {
            Button deleteButton = new Button("Delete", event -> {
                presenter.deleteStaff(staff);
                refreshGrid();
            });
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR);
            return deleteButton;
        }).setHeader("Actions").setAutoWidth(true);

        grid.setItems(query -> StreamSupport.stream(presenter.getStaffList(VaadinSpringDataHelpers.toSpringPageRequest(query), filters).spliterator(), false));

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;

    }

    private void refreshGrid() {

        grid.getDataProvider().refreshAll();
    }

    private Component createRefreshButton() {
        // Create a button with the refresh icon
        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> {
            // Perform refresh logic
            presenter.addDummyPerson();
            refreshGrid();
        });

        // Apply Vaadin primary button style
        refreshButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Style the button for positioning
        refreshButton.getStyle().set("position", "absolute");
        refreshButton.getStyle().set("bottom", "20px");
        refreshButton.getStyle().set("right", "20px");
        refreshButton.getStyle().set("z-index", "1000");

        add(refreshButton);

        return refreshButton;

    }

    public static class Filters extends Div implements Specification<Staff> {

        public final TextField name = new TextField("Name");

        // Initializes search and reset filters, sets up event handlers
        public Filters(Runnable onSearch) {
            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);
            name.setPlaceholder("First or last name");

            Button searchBtn = new Button("Search");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> {
                onSearch.run();  // Trigger search action
            });
            // Action buttons
            Button resetBtn = new Button("Reset");
            resetBtn.addClickListener(e -> {
                name.clear(); // Clear filter field
                onSearch.run(); // Trigger search action
            });
            Div actions = new Div(searchBtn, resetBtn);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("Actions");

            add(name, actions);
        }

        @Override
        public Predicate toPredicate(@NonNull Root<Staff> root,
                                     @Nullable CriteriaQuery<?> query,
                                     @NonNull CriteriaBuilder criteriaBuilder) {
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
}
