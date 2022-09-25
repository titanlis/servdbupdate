package ru.itm.servdbupdate.entity.tables.trans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trans_drilling")
public class TransDrilling {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_drilling_id_gen")
    @SequenceGenerator(name = "trans_drilling_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "equip_id", nullable = false)
    private Integer equipId;

    @Column(name = "shift_date", nullable = false)
    private LocalDate shiftDate;

    @Column(name = "shift_id")
    private Integer shiftId;

    @Column(name = "hole_id", nullable = false)
    private Integer holeId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "x", nullable = false, precision = 10, scale = 2)
    private BigDecimal x;

    @Column(name = "y", nullable = false, precision = 10, scale = 2)
    private BigDecimal y;

    @Column(name = "z", nullable = false, precision = 10, scale = 2)
    private BigDecimal z;

    @Column(name = "fact_deep", nullable = false, precision = 10, scale = 2)
    private BigDecimal factDeep;

    @Column(name = "fact_angle", nullable = false, precision = 10, scale = 2)
    private BigDecimal factAngle;

    @Column(name = "fact_azimuth", nullable = false, precision = 10, scale = 2)
    private BigDecimal factAzimuth;

    @OneToMany(mappedBy = "idTransDrilling")
    private Set<TransKeysDrilling> transKeysDrillings = new LinkedHashSet<>();

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

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getHoleId() {
        return holeId;
    }

    public void setHoleId(Integer holeId) {
        this.holeId = holeId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getFactDeep() {
        return factDeep;
    }

    public void setFactDeep(BigDecimal factDeep) {
        this.factDeep = factDeep;
    }

    public BigDecimal getFactAngle() {
        return factAngle;
    }

    public void setFactAngle(BigDecimal factAngle) {
        this.factAngle = factAngle;
    }

    public BigDecimal getFactAzimuth() {
        return factAzimuth;
    }

    public void setFactAzimuth(BigDecimal factAzimuth) {
        this.factAzimuth = factAzimuth;
    }

    public Set<TransKeysDrilling> getTransKeysDrillings() {
        return transKeysDrillings;
    }

    public void setTransKeysDrillings(Set<TransKeysDrilling> transKeysDrillings) {
        this.transKeysDrillings = transKeysDrillings;
    }

}