package com.computers.sun.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.computers.sun.command.FailedLoginLog;
import com.computers.sun.exception.SpringException;
import com.computers.sun.persistence.QueryOptions;
import com.computers.sun.service.FacadeLookup;
import com.computers.sun.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {

    private FacadeLookup facadeLookup;
    private JavaMailSenderImpl mailSender;

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(final JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public FacadeLookup getFacadeLookup() {
        return facadeLookup;
    }

    public void setFacadeLookup(final FacadeLookup facadeLookup) {
        this.facadeLookup = facadeLookup;
    }

    @Override
    public List<Object[]> getUserCredentialByUserName(final String userName)
            throws SpringException {
        final Map<String, Object> options = new HashMap<String, Object>();
        options.put("email", userName);
        return getFacadeLookup().getPersistenceFacade().findByNamedQueryC(
                "get.user.credential.by.email", new QueryOptions(options));
    }

    @Override
    public void saveFailedLoginLog(final FailedLoginLog failedLoginLog)
            throws SpringException {
        getFacadeLookup().getPersistenceFacade().save(failedLoginLog);
    }

    @Override
    public List<Object[]> getUserTypesByUser(final String userId)
            throws SpringException {
        final Map<String, Object> options = new HashMap<String, Object>();
        options.put("userId", Long.valueOf(userId));
        final List<Object[]> lstResult = getFacadeLookup()
                .getPersistenceFacade()
                .findByNamedQueryC("get.user.type.id.and.user.type.by.user.id",
                        new QueryOptions(options));

        return lstResult;
    }
}
