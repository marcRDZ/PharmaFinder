package com.integratedworlds.mtt.events;

/**
 * Created by mrodriguezd on 18/12/2017.
 */

public class BaseEvent<T> {

    private String message;
    private T resources;

    public BaseEvent(String message) {
        this.message = message;
        this.resources = null;
    }

    public BaseEvent(String message, T resources) {
        this.message = message;
        this.resources = resources;
    }


    public String getMessage() {
        return message;
    }

    public T getResources() {return resources; }
}
