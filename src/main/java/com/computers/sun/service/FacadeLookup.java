package com.computers.sun.service;

import java.io.Serializable;

import com.computers.sun.persistence.PersistenceFacade;

public class FacadeLookup implements Serializable {

    private PersistenceFacade persistenceFacade;

    public PersistenceFacade getPersistenceFacade() {
        return persistenceFacade;
    }

    public void setPersistenceFacade(
            final PersistenceFacade persistenceFacade) {
        this.persistenceFacade = persistenceFacade;
    }

}
