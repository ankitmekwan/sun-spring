package com.computers.sun.service.serviceImpl;

import com.computers.sun.command.RequestUrlInfo;
import com.computers.sun.exception.SpringException;
import com.computers.sun.service.FacadeLookup;
import com.computers.sun.service.IpRequestInfoService;

public class IpRequestInfoServiceImpl implements IpRequestInfoService {

    private FacadeLookup facadeLookup;

    public FacadeLookup getFacadeLookup() {
        return facadeLookup;
    }

    public void setFacadeLookup(FacadeLookup facadeLookup) {
        this.facadeLookup = facadeLookup;
    }

    public void saveIpInfo(RequestUrlInfo requestUrlInfo)
            throws SpringException {
        getFacadeLookup().getPersistenceFacade().save(requestUrlInfo);
    }

}
