package ru.itm.servdbupdate.repository.status;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.status.StatusCategory;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface StatusCategoryRepository extends CommonRepository<StatusCategory> {
}
