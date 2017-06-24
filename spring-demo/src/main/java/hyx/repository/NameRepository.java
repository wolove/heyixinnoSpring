package hyx.repository;

import hyx.repository.entity.NameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
//@NoRepositoryBean
public interface NameRepository extends JpaRepository<NameEntity, Long> {
}

