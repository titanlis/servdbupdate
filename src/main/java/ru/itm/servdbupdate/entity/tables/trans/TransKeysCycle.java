package ru.itm.servdbupdate.entity.tables.trans;

import ru.itm.servdbupdate.entity.tables.sensor.SensorDataType;

import javax.persistence.*;

@Entity
@Table(name = "trans_keys_cycle")
public class TransKeysCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_keys_cycle_id_gen")
    @SequenceGenerator(name = "trans_keys_cycle_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trans_cycle", nullable = false)
    private TransCycle idTransCycle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trans_sensor", nullable = false)
    private TransSensor idTransSensor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sensor_data_type_id", nullable = false)
    private SensorDataType sensorDataType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransCycle getIdTransCycle() {
        return idTransCycle;
    }

    public void setIdTransCycle(TransCycle idTransCycle) {
        this.idTransCycle = idTransCycle;
    }

    public TransSensor getIdTransSensor() {
        return idTransSensor;
    }

    public void setIdTransSensor(TransSensor idTransSensor) {
        this.idTransSensor = idTransSensor;
    }

    public SensorDataType getSensorDataType() {
        return sensorDataType;
    }

    public void setSensorDataType(SensorDataType sensorDataType) {
        this.sensorDataType = sensorDataType;
    }

}