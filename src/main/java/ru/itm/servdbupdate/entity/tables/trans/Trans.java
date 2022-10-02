package ru.itm.servdbupdate.entity.tables.trans;

import java.util.Calendar;

public interface Trans{
    Long getEquipIdTrans();
    Calendar getTime();

    Long getFirstID();
    Long getSecondID();
    Long getThirdID();
    //boolean isForWrite(AbstractEntity abstractEntity);
}
