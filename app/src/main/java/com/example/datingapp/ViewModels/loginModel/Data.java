
package com.example.datingapp.ViewModels.loginModel;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("is_user")
    private Boolean isUser;

    public Boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(Boolean isUser) {
        this.isUser = isUser;
    }

}
