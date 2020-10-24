package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.User;

public class UserResponse {

    private User user;
    private StatusResponse status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
