package com.shizhong.db.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOWNLINE")
public class DownLine {

    private Long id;

    private Integer lineindex;
    
    private Boolean modify;

    private String name;

    private String stopaddress;

    private String startend;

    private String alias;

    private String line;

    private String status;

    private BusTicket busTicket;

    private BusTicketAir busTicketAir;

    @Column(name = "ALIAS", length = 45)
    public String getAlias() {
        return alias;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicket.class)
    public BusTicket getBusTicket() {
        return busTicket;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicketAir.class)
    public BusTicketAir getBusTicketAir() {
        return busTicketAir;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "LINE", length = 45)
    public String getLine() {
        return line;
    }

    @Column(name = "LINEINDEX")
    public Integer getLineindex() {
        return lineindex;
    }

    @Column(name = "MODIFY")
    public Boolean getModify() {
        return modify;
    }

    @Column(name = "NAME", length = 45)
    public String getName() {
        return name;
    }

    @Column(name = "STARTEND", length = 45)
    public String getStartend() {
        return startend;
    }

    @Column(name = "STATUS", length = 200)
    public String getStatus() {
        return status;
    }

    @Column(name = "STOPADDRESS", length = 45)
    public String getStopaddress() {
        return stopaddress;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setBusTicket(BusTicket busTicket) {
        this.busTicket = busTicket;
    }

    public void setBusTicketAir(BusTicketAir busTicketAir) {
        this.busTicketAir = busTicketAir;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setLineindex(Integer index) {
        this.lineindex = index;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartend(String startend) {
        this.startend = startend;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStopaddress(String stopAddress) {
        this.stopaddress = stopAddress;
    }

    @Override
    public String toString() {
        return "DownLine [id=" + id + ", lineindex=" + lineindex + ", modify=" + modify + ", name=" + name
                + ", stopaddress=" + stopaddress + ", startend=" + startend + ", alias=" + alias + ", line=" + line
                + ", status=" + status + ", busTicket=" + busTicket + ", busTicketAir=" + busTicketAir + "]";
    }
}
