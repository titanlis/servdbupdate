package ru.itm.servdbupdate.repository.material;


import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.material.MaterialType;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface MaterialTypeRepository extends CommonRepository<MaterialType> {
}
