package labworx.io.services.account.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Audited
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractDateModel {

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "createdDate", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdDate = new Date();

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "modifiedDate", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date lastModifiedDate = new Date();

    public abstract Long getId();
}
