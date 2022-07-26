package ru.itm.servdbupdate.serivce;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.repository.TableRepository;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TablesService {

    private static Logger logger = LoggerFactory.getLogger(TablesService.class);

    @Autowired
    private final TableRepository tableRepository;

    public TablesService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }
    public List<TableVersion> findAll(){
        return tableRepository.findAll();
    }

    /**
     * Найти все таблицы версии которых новее данных
     * @param bkTablesList список поступивших на проверку таблиц с бк
     * @return newTablesList список таблиц, версии которых новее
     */
    public List<TableVersion> findTablesYoungerThanThis(List<TableVersion> bkTablesList){
        List<TableVersion> newTableList = new LinkedList<>();
        List<TableVersion> serverBaseTableList = findAll(); //читаем все поля таблицы с версиями

        tmpPrintTables(serverBaseTableList);

        for(TableVersion bkTab : bkTablesList){
            //находим таблицы на сервере, где версии новее, чем на бк
            serverBaseTableList.stream()
                    .filter(servTab -> servTab.getTableName().equals(bkTab.getTableName())
                            && servTab.getTableVersion() > bkTab.getTableVersion())
                    .forEach(servTab -> newTableList.add(servTab));
            }

        return newTableList;
    }

    private void tmpPrintTables(List<TableVersion> tableVersions) {
        AtomicInteger i= new AtomicInteger(1);
        tableVersions.stream().forEach(t->{
            System.out.println((i.getAndIncrement()) + "\t" + t.getTableName() + "\t" + t.getTableVersion());
        });
    }

}
