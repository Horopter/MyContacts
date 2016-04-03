package com.horopter.mycontacts;

/**
 * Created by Horopter on 4/1/2016.
 */
public class Contact {
    private String name;
    private String uri;
    private Integer id;
    private String lookup;

    public Contact(String n,String u, Integer i, String l)
    {
        name = n;
        uri = u;
        id = i;
        lookup = l;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getUri()
    {
        return uri;
    }
    public void setUri(String uri)
    {
        this.uri=uri;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id=id;
    }
    public String getLookup()
    {
        return lookup;
    }
    public void setLookup(String lookup)
    {
        this.lookup=lookup;
    }

}
