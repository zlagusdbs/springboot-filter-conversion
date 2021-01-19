package com.spharos.conversion.entity.composite;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MberCustMapngComposite implements Serializable {
    private String mberNo;
    private String custId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MberCustMapngComposite mberCustMapngComposite = (MberCustMapngComposite) o;
        return Objects.equals(this.mberNo, mberCustMapngComposite.getMberNo()) &&
                Objects.equals(this.custId, mberCustMapngComposite.getCustId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mberNo, this.custId);
    }
}
