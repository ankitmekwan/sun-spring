package com.computers.sun.security;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.computers.sun.command.FailedLoginLog;
import com.computers.sun.exception.SpringException;
import com.computers.sun.service.UserAccountService;

public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LogManager
            .getLogger(UserDetailsServiceImpl.class);

    private UserAccountService userAccountService;

    private String userName;

    private String password;

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException, DataAccessException {
        SunUser sunUser = null;
        try {
            if (userName != null && !userName.equals("")) {
                List<Object[]> userCredential = getUserAccountService()
                        .getUserCredentialByUserName(userName);
                boolean activeUser = false;
                if (userCredential != null && !userCredential.isEmpty()
                        && userCredential.get(0) != null) {
                    if (userCredential.get(0)[5] != null
                            || userCredential.get(0)[5].toString()
                                    .equals("1")) {
                        activeUser = true;
                    }
                    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                    List<Object[]> UserTypesLst = getUserAccountService()
                            .getUserTypesByUser(
                                    userCredential.get(0)[0].toString());
                    if (UserTypesLst != null && !UserTypesLst.isEmpty()) {
                        for (Object[] obj : UserTypesLst) {
                            if (obj[1] != null) {
                                authorities.add(new SimpleGrantedAuthority(
                                        obj[1].toString()));
                            }
                        }
                    }

                    sunUser = new SunUser(userCredential.get(0)[1].toString(),
                            userCredential.get(0)[3].toString(), activeUser,
                            true, true, true, authorities);
                    sunUser.setUserId(
                            Long.valueOf(userCredential.get(0)[0].toString()));
                    sunUser.setEmail(userCredential.get(0)[2] != null
                            ? userCredential.get(0)[2].toString() : "");
                    sunUser.setFullName(userCredential.get(0)[4] != null
                            ? userCredential.get(0)[4].toString() : "");
                } else {
                    FailedLoginLog tblFailedLogin = new FailedLoginLog();
                    tblFailedLogin.setLoginUserName(userName);
                    tblFailedLogin.setLoginPassword(null);
                    tblFailedLogin.setIpAddress(null);
                    tblFailedLogin.setLoginDateTime(new Date());
                    tblFailedLogin.setReason("Invalid Usename.");
                    getUserAccountService().saveFailedLoginLog(tblFailedLogin);
                }
            }
        } catch (SpringException e) {
            LOGGER.error("Error: ", e);
        }
        return sunUser;
    }

}
