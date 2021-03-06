package org.jboss.examples.ticketmonster.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "date", "show_id" }))
@JsonIgnoreProperties("show")
public class Performance implements Serializable {

    /* Declaration of fields */

    /**
     * The synthetic id of the object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>
     * The date and start time of the performance.
     * </p>
     *
     * <p>
     * A Java {@link Date} object represents both a date and a time, whilst an RDBMS splits out Date, Time and Timestamp.
     * Therefore we instruct JPA to store this date as a timestamp using the <code>@Temporal(TIMESTAMP)</code> annotation.
     * </p>
     *
     * <p>
     * The date and time of the performance is required, and the Bean Validation constraint <code>@NotNull</code> enforces this.
     * </p>
     */
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    /**
     * <p>
     * The show of which this is a performance. The <code>@ManyToOne<code> JPA mapping establishes this relationship.
     * </p>
     *
     * <p>
     * The show of which this is a performance is required, and the Bean Validation constraint <code>@NotNull</code> enforces
     * this.
     * </p>
     */
    @ManyToOne
    @NotNull
    private Show show;

    /* Boilerplate getters and setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /* equals() and hashCode() for Performance, using the natural identity of the object */

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Performance that = (Performance) o;

        if (date != null ? !date.equals(that.date) : that.date != null)
            return false;
        if (show != null ? !show.equals(that.show) : that.show != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (show != null ? show.hashCode() : 0);
        return result;
    }
}