package com.exposit.my_taxi.model.shared;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractVersionEntity extends AbstractSystemEntity {
    @Version
    @Column(columnDefinition = "default 0", nullable = false)
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
