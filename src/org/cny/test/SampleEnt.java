package org.cny.test;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Centny.
 */
@Entity(name = "SAMPLE")
@Table(name = "SAMPLE")
public class SampleEnt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SAMPLE_TID_GENERATOR", sequenceName = "S_SAMPLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAMPLE_TID_GENERATOR")
    private long tid;

    private String name;
    private String value;

    public SampleEnt() {
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}