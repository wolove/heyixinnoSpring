package hyx.repository.mysql;

import hyx.repository.mysql.entity.NameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
public interface NameRepository extends JpaRepository<NameEntity, Long> {
}

