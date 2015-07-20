package com.methodia.android.testautomation.Model;

/**
 * Created by Stefan.Doychev on 20.07.2015.
 */
public
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor(suppressConstructorProperties = true)
@lombok.ToString
class User {
    private String login;
    private int id;
    private String avatar_url;
    private String html_url;
    private String repos_url;
    private String type;
    private boolean site_admin;
}

