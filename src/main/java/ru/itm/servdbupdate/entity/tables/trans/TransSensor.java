package ru.itm.servdbupdate.entity.tables.trans;

import ru.itm.servdbupdate.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "trans_sensor", schema = "trans")
public class TransSensor extends AbstractEntity implements Trans{

    @Column(name = "sensor_id")
    private Long sensorId;

    @Column(name = "equip_id")
    private Long equipId;

    @Column(name = "shift_date")
    private LocalDate shiftDate;

    @Column(name = "shift_id")
    private Long shiftId;

    @Column(name = "time_read")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeRead;

    @Column(name = "value_data")
    private Float valueData;

    @Column(name = "trans_coord_id")
    private Long transCoordId;

    @Deprecated
    public TransSensor() {
    }

    public TransSensor(Long sensorId, Long equipId, LocalDate shiftDate, Long shiftId, Calendar timeRead, Float valueData, Long transCoordId) {
        this.sensorId = sensorId;
        this.equipId = equipId;
        this.shiftDate = shiftDate;
        this.shiftId = shiftId;
        this.timeRead = timeRead;
        this.valueData = valueData;
        this.transCoordId = transCoordId;
    }

    public TransSensor(TransSensor t) {
        this.sensorId = t.sensorId;
        this.equipId = t.equipId;
        this.shiftDate = t.shiftDate;
        this.shiftId = t.shiftId;
        this.timeRead = t.timeRead;
        this.valueData = t.valueData;
        this.transCoordId = t.transCoordId;
    }


    @Override
    public String toString() {
        return "trans.trans_sensor" + '{' +
                "\"id\":" + id +
                ", \"sensor_id\":" + sensorId +
                ", \"equip_id\":" + equipId +
                ", \"shift_date\":\"" + localDateToString(shiftDate) + "\"" +
                ", \"shift_id\":" + shiftId +
                ", \"time_read\":\"" + calendarToString(timeRead) + "\"" +
                ", \"value_data\":" + valueData +
                ", \"trans_coord_id\":" + transCoordId +
                '}';
    }

    private String calendarToString(Calendar calendar){
        return (calendar!=null)?calendar.getTime().toString():"";
    }
    private String localDateToString(LocalDate localDate){
        return (localDate!=null)?localDate.toString():"";
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Long getEquipId() {
        return equipId;
    }

    public void setEquipId(Long equipId) {
        this.equipId = equipId;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Calendar getTimeRead() {
        return timeRead;
    }

    public void setTimeRead(Calendar timeRead) {
        this.timeRead = timeRead;
    }

    public Float getValueData() {
        return valueData;
    }

    public void setValueData(Float valueData) {
        this.valueData = valueData;
    }

    public Long getTransCoordId() {
        return transCoordId;
    }

    public void setTransCoordId(Long transCoordId) {
        this.transCoordId = transCoordId;
    }

    @Override
    public Long getEquipIdTrans() {
        return equipId;
    }

    @Override
    public Calendar getTime() {
        return timeRead;
    }

    @Override
    public Long getFirstID() {
        return null;
    }

    @Override
    public Long getSecondID() {
        return null;
    }

    @Override
    public Long getThirdID() {
        return null;
    }
}