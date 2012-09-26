package com.shizhong.db.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOWNLINEPREVIEW")
public class DownLinePreview {

    private Long id;

    private Integer lineindex;

    private String name;

    private String stopAddress;

    private String startend;

    private String alias;

    private String line;

    private String status;

    private PreTransaction transaction;

    private BusTicketPreview busTicketPreview;

    private BusTicketAirPreview busTicketPreviewAir;

    @Column(name = "ALIAS", length = 45)
    public String getAlias() {
        return alias;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicketPreview.class)
    public BusTicketPreview getBusTicketPreview() {
        return busTicketPreview;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicketAirPreview.class)
    public BusTicketAirPreview getBusTicketPreviewAir() {
        return busTicketPreviewAir;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "LINEINDEX")
    public Integer getLineindex() {
        return lineindex;
    }

    @Column(name = "LINE", length = 45)
    public String getLine() {
        return line;
    }

    @Column(name = "NAME", length = 45)
    public String getName() {
        return name;
    }

    @Column(name = "STARTEND", length = 45)
    public String getStartend() {
        return startend;
    }

    public String getStatus() {
        return status;
    }

    @Column(name = "STOPADDRESS", length = 45)
    public String getStopAddress() {
        return stopAddress;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = PreTransaction.class)
    public PreTransaction getTransaction() {
        return transaction;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setBusTicketPreview(BusTicketPreview busTicketPreview) {
        this.busTicketPreview = busTicketPreview;
    }

    public void setBusTicketPreviewAir(BusTicketAirPreview busTicketPreviewAir) {
        this.busTicketPreviewAir = busTicketPreviewAir;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLineindex(Integer index) {
        this.lineindex = index;
    }

    public void setLine(String line) {
        this.line = line;
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

    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    public void setTransaction(PreTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "DownLinePreview [id=" + id + ", lineindex=" + lineindex + ", name=" + name + ", stopAddress="
                + stopAddress + ", startend=" + startend + ", alias=" + alias + ", line=" + line + ", status=" + status
                + ", transaction=" + transaction + ", busTicketPreview=" + busTicketPreview + ", busTicketPreviewAir="
                + busTicketPreviewAir + "]";
    }
}
