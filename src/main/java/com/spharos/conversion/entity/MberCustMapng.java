package com.spharos.conversion.entity;

import com.spharos.conversion.entity.composite.MberCustMapngComposite;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mber_cust_mapng")
@IdClass(MberCustMapngComposite.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MberCustMapng {
    @Id
    @Column(name = "mber_no", length = 10, nullable = false)
    private String mberNo;

    @Id
    @Column(name = "cust_id", length = 100, nullable = false)
    private String custId;
}
