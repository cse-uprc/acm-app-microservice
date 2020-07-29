package com.acm.app.user.client.domain;
import com.acm.service.globals.enums.WebRole;
import org.springframework.stereotype.Component;
@Component
public class User
{

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String bioDesc;
    private String password;
    private String username;
    private WebRole userGroup;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getBioDesc()
    {
        return bioDesc;
    }

    public void setBioDesc(String bioDesc)
    {
        this.bioDesc = bioDesc;
    }

    public WebRole getWebRoles()
    {
        return userGroup;
    }

    public void setWebRoles(WebRole userType)
    {
        userGroup = userType;
    }
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String passoword)
    {
        this.password = passoword;
    }
    public boolean isNull()
    {
        return username == null && password == null && id == 0;
    }
}
