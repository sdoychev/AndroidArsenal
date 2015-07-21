package com.methodia.android.testautomation.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Doychev on 21.7.2015 г..
 */
@Table(name = "Sports")
public class Sport extends Model {
    @Column(name = "Name", index = true)
    public String name;

    public Sport() {
        super();
    }

    public Sport(String name) {
        super();
        this.name = name;
    }

    // This method is optional, does not affect the foreign key creation.
    public List<Team> teams() {
        return getMany(Team.class, "Team");
    }

    public String toString() {
        return name;
    }
}