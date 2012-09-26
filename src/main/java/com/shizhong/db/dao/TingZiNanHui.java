package com.shizhong.db.dao;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TINGZINANHUI")
public class TingZiNanHui implements Displayable {
    private Long id;

    private Integer entityindex;

    private Boolean modify;

    private String type;

    private String area;

    private String picnumber;

    private String road;

    private String stop;

    private String stopnum;

    private String entitynum;

    private String address;

    private String direction;

    private String line;

    private Date digtime;

    private String finalstop;

    private String nextstop;

    private Date finishdate;

    private Blob image;

    private String comments;

    private String status;

    private String entityType = "TingZiNanHui";

    private String adop;

    private Date adstart;

    private Date adend;

    private Date lastCareDate;

    private String nanhuitingType;

    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return address;
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

    @Column(name = "ENTITYINDEX")
    public Integer getEntityindex() {
        return entityindex;
    }

    @Column(name = "ENTITYNUM", length = 200)
    public String getEntitynum() {
        return entitynum;
    }

    @Column(name = "ENTITYTYPE", length = 200)
    public String getEntityType() {
        return entityType;
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

    @Column(name = "MODIFY")
    public Boolean getModify() {
        return modify;
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

    @Column(name = "STATUS", length = 200)
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

    @Column(name = "TYPE", length = 45)
    public String getType() {
        return type;
    }

    public void setAddress(String addr) {
        this.address = addr;
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

    public void setDigtime(Date digdate) {
        this.digtime = digdate;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setEntityindex(Integer index) {
        this.entityindex = index;
    }

    public void setEntitynum(String entitynum) {
        this.entitynum = entitynum;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
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

    public void setModify(Boolean modify) {
        this.modify = modify;
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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TingZiNanHui [id=" + id + ", entityindex=" + entityindex + ", modify=" + modify + ", type=" + type
                + ", area=" + area + ", picnumber=" + picnumber + ", road=" + road + ", stop=" + stop + ", stopnum="
                + stopnum + ", entitynum=" + entitynum + ", address=" + address + ", direction=" + direction
                + ", line=" + line + ", digdate=" + digtime + ", finalstop=" + finalstop + ", nextstop=" + nextstop
                + ", finishdate=" + finishdate + ", image=" + image + ", comments=" + comments + ", status=" + status
                + ", entityType=" + entityType + ", adop=" + adop + ", adstart=" + adstart + ", adend=" + adend
                + ", lastCareDate=" + lastCareDate + ", nanhuitingType=" + nanhuitingType + "]";
    }

}
