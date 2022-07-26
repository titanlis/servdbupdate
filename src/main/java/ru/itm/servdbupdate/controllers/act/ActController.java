package ru.itm.servdbupdate.controllers.act;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itm.servdbupdate.controllers.AbstractController;
import ru.itm.servdbupdate.entity.tables.act.Act;
import ru.itm.servdbupdate.serivce.act.ActService;

@RestController
@RequestMapping("/act")
public class ActController extends AbstractController<Act, ActService> {

    public ActController(ActService service) {
        super(service);
    }
}