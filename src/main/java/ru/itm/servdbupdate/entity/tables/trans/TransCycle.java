package ru.itm.servdbupdate.entity.tables.trans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trans_cycles")
public class TransCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_cycles_id_gen")
    @SequenceGenerator(name = "trans_cycles_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "shift_date")
    private LocalDate shiftDate;

    @Column(name = "shift_id")
    private Integer shiftId;

    @Column(name = "haul_id")
    private Integer haulId;

    @Column(name = "lu_id")
    private Integer luId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "load_time")
    private Instant loadTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "loc_start_id")
    private Integer locStartId;

    @Column(name = "loc_end_id")
    private Integer locEndId;

    @Column(name = "distance_haul", precision = 12, scale = 2)
    private BigDecimal distanceHaul;

    @Column(name = "distance_haul_gps", precision = 12, scale = 2)
    private BigDecimal distanceHaulGps;

    @Column(name = "distance_haul_sensor", precision = 12, scale = 2)
    private BigDecimal distanceHaulSensor;

    @Column(name = "distance", precision = 12, scale = 2)
    private BigDecimal distance;

    @Column(name = "distance_gps", precision = 12, scale = 2)
    private BigDecimal distanceGps;

    @Column(name = "distance_sensor", precision = 12, scale = 2)
    private BigDecimal distanceSensor;

    @Column(name = "payload", precision = 12, scale = 2)
    private BigDecimal payload;

    @Column(name = "material_id")
    private Integer materialId;

    @Column(name = "mt", precision = 12, scale = 2)
    private BigDecimal mt;

    @Column(name = "bcm", precision = 12, scale = 2)
    private BigDecimal bcm;

    @Column(name = "report", precision = 12, scale = 2)
    private BigDecimal report;

    @Column(name = "plan_lu_id")
    private Integer planLuId;

    @Column(name = "plan_load_loc")
    private Integer planLoadLoc;

    @Column(name = "plan_unl_loc")
    private Integer planUnlLoc;

    @Column(name = "cost_center_id")
    private Integer costCenterId;

    @Column(name = "operator_id")
    private Integer operatorId;

    @OneToMany(mappedBy = "idTransCycle")
    private Set<TransKeysCycle> transKeysCycles = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getHaulId() {
        return haulId;
    }

    public void setHaulId(Integer haulId) {
        this.haulId = haulId;
    }

    public Integer getLuId() {
        return luId;
    }

    public void setLuId(Integer luId) {
        this.luId = luId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Instant loadTime) {
        this.loadTime = loadTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Integer getLocStartId() {
        return locStartId;
    }

    public void setLocStartId(Integer locStartId) {
        this.locStartId = locStartId;
    }

    public Integer getLocEndId() {
        return locEndId;
    }

    public void setLocEndId(Integer locEndId) {
        this.locEndId = locEndId;
    }

    public BigDecimal getDistanceHaul() {
        return distanceHaul;
    }

    public void setDistanceHaul(BigDecimal distanceHaul) {
        this.distanceHaul = distanceHaul;
    }

    public BigDecimal getDistanceHaulGps() {
        return distanceHaulGps;
    }

    public void setDistanceHaulGps(BigDecimal distanceHaulGps) {
        this.distanceHaulGps = distanceHaulGps;
    }

    public BigDecimal getDistanceHaulSensor() {
        return distanceHaulSensor;
    }

    public void setDistanceHaulSensor(BigDecimal distanceHaulSensor) {
        this.distanceHaulSensor = distanceHaulSensor;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getDistanceGps() {
        return distanceGps;
    }

    public void setDistanceGps(BigDecimal distanceGps) {
        this.distanceGps = distanceGps;
    }

    public BigDecimal getDistanceSensor() {
        return distanceSensor;
    }

    public void setDistanceSensor(BigDecimal distanceSensor) {
        this.distanceSensor = distanceSensor;
    }

    public BigDecimal getPayload() {
        return payload;
    }

    public void setPayload(BigDecimal payload) {
        this.payload = payload;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getMt() {
        return mt;
    }

    public void setMt(BigDecimal mt) {
        this.mt = mt;
    }

    public BigDecimal getBcm() {
        return bcm;
    }

    public void setBcm(BigDecimal bcm) {
        this.bcm = bcm;
    }

    public BigDecimal getReport() {
        return report;
    }

    public void setReport(BigDecimal report) {
        this.report = report;
    }

    public Integer getPlanLuId() {
        return planLuId;
    }

    public void setPlanLuId(Integer planLuId) {
        this.planLuId = planLuId;
    }

    public Integer getPlanLoadLoc() {
        return planLoadLoc;
    }

    public void setPlanLoadLoc(Integer planLoadLoc) {
        this.planLoadLoc = planLoadLoc;
    }

    public Integer getPlanUnlLoc() {
        return planUnlLoc;
    }

    public void setPlanUnlLoc(Integer planUnlLoc) {
        this.planUnlLoc = planUnlLoc;
    }

    public Integer getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(Integer costCenterId) {
        this.costCenterId = costCenterId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Set<TransKeysCycle> getTransKeysCycles() {
        return transKeysCycles;
    }

    public void setTransKeysCycles(Set<TransKeysCycle> transKeysCycles) {
        this.transKeysCycles = transKeysCycles;
    }

}