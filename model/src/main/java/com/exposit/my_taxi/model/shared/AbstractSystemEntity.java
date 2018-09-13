package com.exposit.my_taxi.model.shared;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class AbstractSystemEntity implements Serializable {
    private static final long serialVersionUID = -140584652196083091L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
