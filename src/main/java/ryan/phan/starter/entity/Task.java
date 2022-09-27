package ryan.phan.starter.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ryan.phan.starter.constant.Priority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = Task.TABLE_NAME)
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;    // Need for serializable
    public static final String TABLE_NAME = "tasks";

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDt;
}
