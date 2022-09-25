package ru.itm.servdbupdate.entity.tables.trans;

import javax.persistence.*;

@Entity
@Table(name = "trans_keys_drilling")
public class TransKeysDrilling {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_keys_drilling_id_gen")
    @SequenceGenerator(name = "trans_keys_drilling_id_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trans_drilling", nullable = false)
    private TransDrilling idTransDrilling;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trans_sensor", nullable = false)
    private TransSensor idTransSensor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransDrilling getIdTransDrilling() {
        return idTransDrilling;
    }

    public void setIdTransDrilling(TransDrilling idTransDrilling) {
        this.idTransDrilling = idTransDrilling;
    }

    public TransSensor getIdTransSensor() {
        return idTransSensor;
    }

    public void setIdTransSensor(TransSensor idTransSensor) {
        this.idTransSensor = idTransSensor;
    }

}