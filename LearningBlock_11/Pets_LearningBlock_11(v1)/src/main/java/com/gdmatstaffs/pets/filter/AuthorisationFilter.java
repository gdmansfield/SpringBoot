package com.gdmatstaffs.pets.filter;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Staff;
import com.gdmatstaffs.pets.owner.OwnerService;
import com.gdmatstaffs.pets.staff.StaffService;
import lombok.AllArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebFilter(urlPatterns = {"/*"})
public class AuthorisationFilter implements Filter
{
    private final OwnerService ownerService;
    private final StaffService staffService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI().toLowerCase();

        if (requestURI.contains("checkcredentials") ||
            requestURI.contains("create"))
        {
            // No authorisation required
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            String token = request.getHeader("AUTHORIZATION");

            Staff staff = staffService.checkCredentials(token);
            Owner owner = ownerService.checkCredentials(token);

            if (staff != null && staffIsAuthorised(request))
            {
                request.setAttribute("AuthorisedStaff", staff);
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else if (owner != null && ownerIsAuthorised(request))
            {
                request.setAttribute("AuthorisedOwner", owner);
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
//        else if (staffIsAuthorised(request))
//        {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        else if (ownerIsAuthorised(request))
//        {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        else
//        {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
    }

    private boolean staffIsAuthorised(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI().toLowerCase();
        System.out.println("STAFF REQUEST URI: " + requestURI);
        String token = request.getHeader("AUTHORIZATION");

        Staff staff = staffService.checkCredentials(token);

        if (staff != null)
        {
            if (requestURI.startsWith("/owner/get/") ||
                requestURI.startsWith("/owner/delete/") ||
                requestURI.startsWith("/pet/get/"))
            {
                return true;
            }
            else if (requestURI.startsWith("/staff/logout/"))
            {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                return staff.getId() == id;
            }
        }

        return false;
    }

    private boolean ownerIsAuthorised(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI().toLowerCase();
        System.out.println("OWNER REQUEST URI: " + requestURI);
        String token = request.getHeader("AUTHORIZATION");

        Owner owner = ownerService.checkCredentials(token);

        if (owner != null)
        {
            if (requestURI.startsWith("/pet/add") ||
                requestURI.startsWith("/owner/update/") ||
                requestURI.startsWith("/owner/logout/") ||
                (requestURI.startsWith("/owner/get/") &&
                                !requestURI.contains("all")))
            {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                return owner.getId() == id;
            }
        }

        return false;
    }
}
