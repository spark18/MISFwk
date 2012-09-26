package com.shizhong.db.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SZTRANSACTION")
public class SZTransaction {
    public static enum Type {
        LIGANCOMMIT, LIGANMODIFY
    }

    private Long id;

    private Date timeStamp;

    private User user;

    private User approver;

    private String type;

    private String comments;

    private LiGan ligan;

    private TingZi tingzi;

    private TingZiNanHui tingzinanhui;

    private YangZhaoDian yangzhaodian;

    private UpLine upline;

    private DownLine downline;

    private BusTicket busticket;

    private BusTicketAir busticketair;

    @ManyToOne(cascade = CascadeType.ALL)
    public User getApprover() {
        return approver;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicket.class)
    public BusTicket getBusticket() {
        return busticket;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = BusTicketAir.class)
    public BusTicketAir getBusticketair() {
        return busticketair;
    }

    @Column(name = "COMMENTS", length = 2000)
    public String getComments() {
        return comments;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = DownLine.class)
    public DownLine getDownline() {
        return downline;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = LiGan.class)
    public LiGan getLigan() {
        return ligan;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = TingZi.class)
    public TingZi getTingzi() {
        return tingzi;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = TingZiNanHui.class)
    public TingZiNanHui getTingzinanhui() {
        return tingzinanhui;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = YangZhaoDian.class)
    public YangZhaoDian getYangzhaodian() {
        return yangzhaodian;
    }

    @Column(name = "TYPE", length = 45)
    public String getType() {
        return type;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = UpLine.class)
    public UpLine getUpline() {
        return upline;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public void setBusticket(BusTicket busticket) {
        this.busticket = busticket;
    }

    public void setBusticketair(BusTicketAir busticketair) {
        this.busticketair = busticketair;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDownline(DownLine downline) {
        this.downline = downline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLigan(LiGan ligan) {
        this.ligan = ligan;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TIMESTAMP", nullable = false)
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTingzi(TingZi tingzi) {
        this.tingzi = tingzi;
    }

    public void setTingzinanhui(TingZiNanHui tingzi) {
        this.tingzinanhui = tingzi;
    }
    
    public void setYangzhaodian(YangZhaoDian yangzhaodian) {
        this.yangzhaodian = yangzhaodian;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpline(UpLine upline) {
        this.upline = upline;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SZTransaction [id=" + id + ", timeStamp=" + timeStamp + ", user=" + user + ", approver=" + approver
                + ", type=" + type + ", comments=" + comments + ", ligan=" + ligan + ", tingzi=" + tingzi
                + ", tingzinanhui=" + tingzinanhui + ", upline=" + upline + ", downline=" + downline + ", busticket="
                + busticket + ", busticketair=" + busticketair + "]";
    }

}
