package ru.itm.servdbupdate.serivce.act;

import org.springframework.stereotype.Service;
import ru.itm.servdbupdate.entity.tables.operator.Act;
import ru.itm.servdbupdate.repository.operator.ActRepository;
import ru.itm.servdbupdate.serivce.AbstractService;

@Service
public class ActService extends AbstractService<Act, ActRepository> {
    public ActService(ActRepository repository) {
        super(repository);
    }
}