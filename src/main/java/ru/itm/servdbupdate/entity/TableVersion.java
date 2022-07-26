package ru.itm.servdbupdate.entity;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "table_version", schema = "config")
public class TableVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "tb_version")
    private Integer tableVersion;

    @Column(name = "update_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updateDateTime;

    public TableVersion() {
    }

    public TableVersion(String tableName, Integer tableVersion) {
        this.tableName = tableName;
        this.tableVersion = tableVersion;
    }

    public TableVersion(TableVersion tV) {
        id = tV.id;
        tableName=tV.tableName;
        tableVersion=tV.tableVersion;
        updateDateTime=tV.updateDateTime;
    }

    /**
     * Метод для табличного вывода данных о версии таблиц.
     * @return str форматированная строка для вывода столбцами
     */
    public String toStringShort() {
        String s = "                         ";
        return "TableVersion{" +
                "id=" + id +
                ",\ttableName='" + tableName + "\'," + s.substring(tableName.length()) +
                "\ttableVersion=" + tableVersion + "," + s.substring(21+tableVersion.toString().length()) +
                "\tupdateDateTime=" + updateDateTime.getTime() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableVersion() {
        return tableVersion;
    }

    public Calendar getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Calendar updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void setTableVersion(Integer tableVersion) {
        this.tableVersion = tableVersion;
    }


}
