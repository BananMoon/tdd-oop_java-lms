package nextstep.global.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected Long id;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected BaseEntity() {
        this(0L, LocalDateTime.now(), LocalDateTime.now());
    }

    protected BaseEntity(Long id) {
        this(id, LocalDateTime.now(), LocalDateTime.now());
    }

    protected BaseEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
