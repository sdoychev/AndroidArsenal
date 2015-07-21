package com.methodia.android.testautomation.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Doychev on 21.7.2015 г..
 */
@Table(name = "Teams")
public class Team extends Model {
    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "Sport", index = true)
    public Sport sport;

    public Team() {
        super();
    }

    public Team(String name, Sport sport) {
        super();
        this.name = name;
        this.sport = sport;
    }

    public String toString() {
        return name + " playing " + sport.toString() + ".";
    }
}
