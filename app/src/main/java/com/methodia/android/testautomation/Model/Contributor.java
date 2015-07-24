package com.methodia.android.testautomation.Model;

/**
 * Created by Stefan.Doychev on 24.07.2015.
 */
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor(suppressConstructorProperties = true)
@lombok.ToString
public class Contributor {
    private String login;
    private int id;
    private String avatar_url;
    private String html_url;
    private String followers_url;
    private String repos_url;
    private String type;
    private String site_admin;
    private int contributions;
}
