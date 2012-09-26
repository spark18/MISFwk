package com.shizhong.db.dao;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TINGZINANHUIPREVIEW")
public class TingZiNanHuiPreview implements Displayable {

    private Long id;

    private String type;

    private String area;

    private String picnumber;

    private String road;

    private String stop;

    private String stopnum;

    private String entitynum;

    private String addr;

    private String direction;

    private String line;

    private Date digtime;

    private String finalstop;

    private String nextstop;

    private Date finishdate;

    private Blob image;

    private String comments;

    private String status;

    private String adop;

    private Date adstart;

    private Date adend;

    private Date lastCareDate;

    private String nanhuitingType;

    private PreTransaction transaction;

    @Column(name = "ADDRESS", length = 200)
    public String getAddr() {
        return addr;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ADEND")
    public Date getAdend() {
        return adend;
    }

    @Column(name = "ADOP", length = 200)
    public String getAdop() {
        return adop;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ADSTART")
    public Date getAdstart() {
        return adstart;
    }

    @Column(name = "AREA", length = 45)
    public String getArea() {
        return area;
    }

    @Column(name = "COMMENTS", length = 1000)
    public String getComments() {
        return comments;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DIGTIME")
    public Date getDigtime() {
        return digtime;
    }

    @Column(name = "DIRECTION", length = 45)
    public String getDirection() {
        return direction;
    }

    @Column(name = "ENTITYNUM", length = 200)
    public String getEntitynum() {
        return entitynum;
    }

    @Column(name = "FINALSTOP", length = 45)
    public String getFinalstop() {
        return finalstop;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FINISHDATE")
    public Date getFinishdate() {
        return finishdate;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Column(name = "IMAGE")
    @Lob
    public Blob getImage() {
        return image;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LASTCAREDATE")
    public Date getLastCareDate() {
        return lastCareDate;
    }

    @Column(name = "LINE", length = 45)
    public String getLine() {
        return line;
    }

    @Column(name = "NANHUITINGTYPE")
    public String getNanhuitingType() {
        return nanhuitingType;
    }

    @Column(name = "NEXTSTOP", length = 45)
    public String getNextstop() {
        return nextstop;
    }

    @Column(name = "PICNUMBER", length = 45)
    public String getPicnumber() {
        return picnumber;
    }

    @Column(name = "ROAD", length = 45)
    public String getRoad() {
        return road;
    }

    public String getStatus() {
        return status;
    }

    @Column(name = "STOP", length = 45)
    public String getStop() {
        return stop;
    }

    @Column(name = "STOPNUM", length = 200)
    public String getStopnum() {
        return stopnum;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = PreTransaction.class)
    public PreTransaction getTransaction() {
        return transaction;
    }

    @Column(name = "TYPE", length = 45)
    public String getType() {
        return type;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setAdend(Date adend) {
        this.adend = adend;
    }

    public void setAdop(String adop) {
        this.adop = adop;
    }

    public void setAdperiod(Date adstart) {
        this.adstart = adstart;
    }

    public void setAdstart(Date adstart) {
        this.adstart = adstart;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDigtime(Date digtime) {
        this.digtime = digtime;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setEntitynum(String entitynum) {
        this.entitynum = entitynum;
    }

    public void setFinalstop(String finalstop) {
        this.finalstop = finalstop;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public void setLastCareDate(Date lastCareDate) {
        this.lastCareDate = lastCareDate;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setNanhuitingType(String nanhuitingType) {
        this.nanhuitingType = nanhuitingType;
    }

    public void setNextstop(String nextstop) {
        this.nextstop = nextstop;
    }

    public void setPicnumber(String number) {
        this.picnumber = number;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setStopnum(String stopnum) {
        this.stopnum = stopnum;
    }

    public void setTransaction(PreTransaction transaction) {
        this.transaction = transaction;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TingZiNanHuiPreview [id=" + id + ", type=" + type + ", area=" + area + ", picnumber=" + picnumber
                + ", road=" + road + ", stop=" + stop + ", stopnum=" + stopnum + ", entitynum=" + entitynum + ", addr="
                + addr + ", direction=" + direction + ", line=" + line + ", digdate=" + digtime + ", finalstop="
                + finalstop + ", nextstop=" + nextstop + ", finishdate=" + finishdate + ", image=" + image
                + ", comments=" + comments + ", status=" + status + ", adop=" + adop + ", adstart=" + adstart
                + ", adend=" + adend + ", lastCareDate=" + lastCareDate + ", nanhuitingType=" + nanhuitingType
                + ", transaction=" + transaction + "]";
    }

}
