package com.yagmurbaran.presenter;

import com.yagmurbaran.model.Staff;
import com.yagmurbaran.service.StaffService;
import com.yagmurbaran.view.StaffView;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
public class StaffPresenter {
    private final StaffService staffService;
    private ListIterator<String[]> dummyDataIterator;

    public StaffPresenter(StaffService staffService) {
        this.staffService = staffService;
        resetDummyDataIterator(); // Initialize iterator
    }

    public Iterable<Staff> getStaffList(PageRequest pageRequest, StaffView.Filters filters) {
        return staffService.list(pageRequest, filters);
    }

    public void addDummyPerson() {
        if (dummyDataIterator == null || !dummyDataIterator.hasNext()) {
            resetDummyDataIterator();  // Reset if all data is added
        }

        // Add one person from the list
        if (dummyDataIterator.hasNext()) {
            String[] person = dummyDataIterator.next();
            String identityNumber = person[0];
            String firstName = person[1];
            String lastName = person[2];

            // Add to service
            staffService.addStaff(identityNumber, firstName, lastName);
        }
    }

    public void deleteStaff(Staff staff) {
        staffService.deleteStaff(staff);
    }
    private void resetDummyDataIterator() {
        List<String[]> dummyData = List.of(
                new String[]{"11223344559", "John", "Doe"},
                new String[]{"11223344556", "Ada", "Lovelace"},
                new String[]{"11223344551", "Marie", "Curie"},
                new String[]{"11223344553", "John", "Reese"},
                new String[]{"11223344555", "Harold", "Finch"},
                new String[]{"11223344557", "Walter", "Bishop"}
        );

        dummyDataIterator = dummyData.listIterator();
    }
}
