package ru.itm.servdbupdate.entity.tables.trans;

import java.util.Calendar;

public interface Trans{
    Long getEquipIdTrans();
    Calendar getTime();
    //boolean isForWrite(AbstractEntity abstractEntity);
}
