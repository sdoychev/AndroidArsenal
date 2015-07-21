package com.methodia.android.testautomation.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Stefan.Doychev on 20.07.2015.
 */
/*
public
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor(suppressConstructorProperties = true)
@lombok.ToString
class Repo {
    private int id;
    private String name;
    private String html_url;
    private String description;
    private String created_at;
    private String updated_at;
    private int size;
}
*/

@lombok.ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}