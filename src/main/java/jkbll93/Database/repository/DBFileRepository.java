package jkbll93.Database.repository;

import jkbll93.Database.model.DBDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBDocument, String> {
}

