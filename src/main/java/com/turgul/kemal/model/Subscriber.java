package com.turgul.kemal.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author kemalturgul
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriber", propOrder = {"id", "name", "msisdn"})
public class Subscriber {

    private int    id;
    private String name;
    private String msisdn;

    public Subscriber() {
    }

    public Subscriber(int id, String name, String msisdn) {
        this.id = id;
        this.name = name;
        this.msisdn = msisdn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getJsonString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"id\":\"");
        builder.append(id);
        builder.append("\", \"name\":\"");
        builder.append(name);
        builder.append("\", \"msisdn\":\"");
        builder.append(msisdn);
        builder.append("\"}");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Subscriber [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", msisdn=");
        builder.append(msisdn);
        builder.append("]");
        return builder.toString();
    }

}
