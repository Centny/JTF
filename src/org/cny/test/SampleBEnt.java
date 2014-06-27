package org.cny.test;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Centny.
 */
@Entity(name = "SAMPLE_B")
@Table(name = "SAMPLE_B")
public class SampleBEnt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SAMPLE_B_TID_GENERATOR", sequenceName = "S_B_SAMPLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAMPLE_B_TID_GENERATOR")
    private long tid;

    private String name;
    private String value;
    private Timestamp time;

    public SampleBEnt() {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}