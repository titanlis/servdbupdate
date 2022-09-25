package ru.itm.servdbupdate.entity.tables.trans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trans_sensor")
public class TransSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_sensor_id_gen")
    @SequenceGenerator(name = "trans_sensor_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sensor_id", nullable = false)
    private Integer sensorId;

    @Column(name = "equip_id", nullable = false)
    private Integer equipId;

    @Column(name = "shift_date", nullable = false)
    private LocalDate shiftDate;

    @Column(name = "shift_id")
    private Integer shiftId;

    @Column(name = "time_read")
    private Instant timeRead;

    @Column(name = "value", nullable = false, precision = 7, scale = 2)
    private BigDecimal value;

    @Column(name = "trans_coord_id")
    private Integer transCoordId;

    @OneToMany(mappedBy = "idTransSensor")
    private Set<TransKeysDrilling> transKeysDrillings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTransSensor")
    private Set<TransKeysCycle> transKeysCycles = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
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

    public Instant getTimeRead() {
        return timeRead;
    }

    public void setTimeRead(Instant timeRead) {
        this.timeRead = timeRead;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getTransCoordId() {
        return transCoordId;
    }

    public void setTransCoordId(Integer transCoordId) {
        this.transCoordId = transCoordId;
    }

    public Set<TransKeysDrilling> getTransKeysDrillings() {
        return transKeysDrillings;
    }

    public void setTransKeysDrillings(Set<TransKeysDrilling> transKeysDrillings) {
        this.transKeysDrillings = transKeysDrillings;
    }

    public Set<TransKeysCycle> getTransKeysCycles() {
        return transKeysCycles;
    }

    public void setTransKeysCycles(Set<TransKeysCycle> transKeysCycles) {
        this.transKeysCycles = transKeysCycles;
    }

}