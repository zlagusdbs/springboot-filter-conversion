package com.spharos.conversion.repository;

import com.spharos.conversion.entity.MberCustMapng;
import com.spharos.conversion.entity.composite.MberCustMapngComposite;
import com.spharos.conversion.repository.querydsl.MberCustMapngQuerydsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MberCustMapngRepository extends JpaRepository<MberCustMapng, MberCustMapngComposite>, MberCustMapngQuerydsl {
}
