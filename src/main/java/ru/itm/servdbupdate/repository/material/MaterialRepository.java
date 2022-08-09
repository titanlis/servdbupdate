package ru.itm.servdbupdate.repository.material;


import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.material.Material;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface MaterialRepository extends CommonRepository<Material> {
}
