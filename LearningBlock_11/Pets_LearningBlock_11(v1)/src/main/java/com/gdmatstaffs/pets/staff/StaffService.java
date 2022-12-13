package com.gdmatstaffs.pets.staff;

import com.gdmatstaffs.pets.entity.Staff;
import com.gdmatstaffs.pets.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StaffService
{
    private final StaffRepository staffRepository;
    private final StringHasher stringHasher;

    public void clearToken(int staffId)
    {
        Staff staff = staffRepository.findById(staffId).orElse(null);

        if (staff != null)
        {
            staff.setToken(null);
            staffRepository.save(staff);
        }
    }

    public Staff checkCredentials(StaffCredentialsDTO creds)
    {
        Staff staff = staffRepository.findByEmail(creds.getEmail());

        if (staff != null && staff.getPassword().equals(creds.getPassword()))
        {
            String token =
                    stringHasher.hashString(
                            staff.getEmail() + ":" + LocalDate.now().toString());
            staff.setToken(token);
            staff = staffRepository.save(staff);
            return staff;
        }

        return null;
    }

    public Staff checkCredentials(String token)
    {
        Staff staff = staffRepository.findByToken(token);
        if (staff != null && staff.getToken() != null)
        {
            return staff;
        }

        return null;
    }
}
