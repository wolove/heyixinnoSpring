package hyx.repository.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
@Embeddable
public class FamilyRelationCompositeKey implements Serializable {

    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "target_id")
    private Long targetId;
    @Column(name = "relation_type", length = 32)
    private String relationType;

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
}
