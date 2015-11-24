package com.computers.sun.service;

import java.util.List;

import com.computers.sun.command.FailedLoginLog;
import com.computers.sun.exception.SpringException;

public interface UserAccountService {

    public List<Object[]> getUserCredentialByUserName(String userName)
            throws SpringException;

    public void saveFailedLoginLog(FailedLoginLog failedLoginLog)
            throws SpringException;

    public List<Object[]> getUserTypesByUser(String userId) throws SpringException;
}
