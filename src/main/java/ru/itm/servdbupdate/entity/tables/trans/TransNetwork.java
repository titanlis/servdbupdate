package ru.itm.servdbupdate.entity.tables.trans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "trans_network")
public class TransNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_network_id_gen")
    @SequenceGenerator(name = "trans_network_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "equip_id", nullable = false)
    private Integer equipId;

    @Column(name = "equip_time")
    private Instant equipTime;

    @Column(name = "trans_coord_id", nullable = false)
    private Integer transCoordId;

    @Lob
    @Column(name = "ap_mac")
    private String apMac;

    @Column(name = "level", precision = 5, scale = 1)
    private BigDecimal level;

    @Column(name = "ping", precision = 5, scale = 1)
    private BigDecimal ping;

    @Column(name = "speed", precision = 5, scale = 1)
    private BigDecimal speed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipId() {
        return equipId;
    }

    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }

    public Instant getEquipTime() {
        return equipTime;
    }

    public void setEquipTime(Instant equipTime) {
        this.equipTime = equipTime;
    }

    public Integer getTransCoordId() {
        return transCoordId;
    }

    public void setTransCoordId(Integer transCoordId) {
        this.transCoordId = transCoordId;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }

    public BigDecimal getPing() {
        return ping;
    }

    public void setPing(BigDecimal ping) {
        this.ping = ping;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

}