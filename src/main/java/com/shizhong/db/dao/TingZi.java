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
@Table(name = "TINGZI")
public class TingZi implements Displayable {
    private Long id;

    private Integer entityindex;

    private Boolean modify;

    private String type;
    
    private String equiptype;

    private String area;

    private String number;

    private String road;

    private String stop;

    private String address;

    private String direction;

    private String line;
    
    private String factory;

    private Date digdate;

    private String finalstop;

    private String nextstop;

    private Date finishdate;

    private Blob image;

    private String comments;

    private String status;

    private String entityType = "TingZi";

    @Column(name = "ENTITYTYPE", length = 200)
    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return address;
    }
    
    @Column(name= "FACTORY", length= 800)
    public String getFactory() {
        return factory;
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
    @Column(name = "DIGDATE", nullable = false)
    public Date getDigdate() {
        return digdate;
    }

    @Column(name = "DIRECTION", length = 45)
    public String getDirection() {
        return direction;
    }

    @Column(name = "FINALSTOP", length = 45)
    public String getFinalstop() {
        return finalstop;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FINISHDATE", nullable = false)
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

    @Column(name = "LINE", length = 45)
    public String getLine() {
        return line;
    }

    @Column(name = "MODIFY")
    public Boolean getModify() {
        return modify;
    }

    @Column(name = "NEXTSTOP", length = 45)
    public String getNextstop() {
        return nextstop;
    }

    @Column(name = "NUMBER", length = 45)
    public String getNumber() {
        return number;
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

    @Column(name = "ENTITYINDEX")
    public Integer getEntityindex() {
        return entityindex;
    }

    @Column(name = "TYPE", length = 45)
    public String getType() {
        return type;
    }

    @Column(name = "EQUIPTYPE", length = 45)
    public String getEquiptype() {
        return equiptype;
    }

    public void setAddress(String addr) {
        this.address = addr;
    }
    
    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDigdate(Date digdate) {
        this.digdate = digdate;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public void setLine(String line) {
        this.line = line;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public void setNextstop(String nextstop) {
        this.nextstop = nextstop;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public void setEntityindex(Integer index) {
        this.entityindex = index;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setEquiptype(String equiptype) {
        this.equiptype = equiptype;
    }

    @Override
    public String toString() {
        return "TingZi [id=" + id + ", entityindex=" + entityindex + ", modify=" + modify + ", type=" + type
                + ", area=" + area + ", number=" + number + ", road=" + road + ", stop=" + stop + ", address="
                + address + ", direction=" + direction + ", line=" + line + ", digdate=" + digdate + ", finalstop="
                + finalstop + ", nextstop=" + nextstop + ", finishdate=" + finishdate + ", image=" + image
                + ", comments=" + comments + ", status=" + status + ", entityType=" + entityType + "]";
    }

}
