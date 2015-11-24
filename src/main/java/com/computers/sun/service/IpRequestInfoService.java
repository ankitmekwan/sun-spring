package com.computers.sun.service;

import com.computers.sun.command.RequestUrlInfo;
import com.computers.sun.exception.SpringException;

public interface IpRequestInfoService {
    public void saveIpInfo(RequestUrlInfo requestUrlInfo)
            throws SpringException;
}
