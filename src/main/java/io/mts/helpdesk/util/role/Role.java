package io.mts.helpdesk.util.role;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
public enum Role {

    ADMIN("ADMIN"),
    TEAM_LEAD("TEAM_LEAD"),
    TECHNICIAN("TECHNICIAN");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
