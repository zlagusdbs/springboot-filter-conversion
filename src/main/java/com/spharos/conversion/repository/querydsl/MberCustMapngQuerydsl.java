package com.spharos.conversion.repository.querydsl;

import com.spharos.conversion.entity.MberCustMapng;

import java.util.Optional;

public interface MberCustMapngQuerydsl {
    Optional<MberCustMapng> findByCustid(String custid);
}
