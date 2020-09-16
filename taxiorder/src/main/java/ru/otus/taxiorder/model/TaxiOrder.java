package ru.otus.taxiorder.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.otus.common.dto.RouteDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "taxi_order")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class TaxiOrder {

    @Id
    @GeneratedValue(
            generator = "sequence",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "sequence",
            allocationSize = 10
    )
    private Long id;
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
    private Date requestDate;
    private Date orderDate;
    private Date cancelDate;
    private Date startDate;
    private Date finishDate;
    private UUID clientId;
    private String clientPhone;
    private UUID taxiId;
    private String taxiPhone;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private RouteDTO route;
    private long price;
    private long clientMark;
}
