package com.gdmatstaffs.pets.staff;

import com.gdmatstaffs.pets.entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffFactory
{
    public StaffDTO create(Staff staff)
    {
        if (staff == null)
        {
            return null;
        }

        StaffDTO staffDTO =
                new StaffDTO(
                        staff.getId(),
                        staff.getEmail(),
                        staff.getName(),
                        staff.getToken());

        return staffDTO;
    }
}
