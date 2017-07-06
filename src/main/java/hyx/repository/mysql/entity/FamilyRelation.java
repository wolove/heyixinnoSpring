package hyx.repository.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 展示jpa定义复合主键的例子
 * @author hyx(610302) on 2017/6/24 0024.
 */
@Entity
@Table(name = "family_relation")
@IdClass(FamilyRelationCompositeKey.class)
public class FamilyRelation {
    @Id
    private Long sourceId;
    @Id
    private Long targetId;
    @Id
    private String relationType;
    @Column(name = "description")
    private String description;

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
