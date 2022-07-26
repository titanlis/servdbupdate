package ru.itm.servdbupdate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itm.servdbupdate.entity.TableVersion;

/** Интерфейс доступа к бд*/
public interface TableRepository extends JpaRepository<TableVersion, Long> { }
