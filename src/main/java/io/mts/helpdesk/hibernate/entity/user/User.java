package io.mts.helpdesk.hibernate.entity.user;

import io.mts.helpdesk.hibernate.entity.baseentity.BaseEntity;
import io.mts.helpdesk.util.role.Role;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */

@Entity
@Table(name = "USER")
public class User extends BaseEntity{

    @Length(min = 5,max = 50)
    @NotEmpty
    @Pattern(regexp = "\\S+@\\S+\\.\\S+")
    @Column(unique = true,length = 50)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Length(min=3,max=50)
    @NotEmpty
    @Column(length = 50,nullable = false)
    private String name;

    @Length(min=3,max=50)
    @NotEmpty
    @Column(length = 50,nullable = false)
    private String surname;

    @Length(min = 3,max = 64)
    @NotEmpty
    @Column(length = 64,nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private int failCount=0;

    @Column
    private Date lastLoginTime;

    @Column
    private Date lastLogoutTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }
}
