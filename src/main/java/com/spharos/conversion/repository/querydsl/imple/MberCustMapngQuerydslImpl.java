package com.spharos.conversion.repository.querydsl.imple;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.conversion.entity.MberCustMapng;
import com.spharos.conversion.repository.querydsl.MberCustMapngQuerydsl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

import static com.spharos.conversion.entity.QMberCustMapng.mberCustMapng;

@Slf4j
public class MberCustMapngQuerydslImpl extends QuerydslRepositorySupport implements MberCustMapngQuerydsl {
    public MberCustMapngQuerydslImpl() {
        super(MberCustMapng.class);
    }


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MberCustMapng> findByCustid(String custid) {
        JPAQuery<MberCustMapng> jpaQuery = jpaQueryFactory
                .selectFrom(mberCustMapng)
                .where(
                        mberCustMapng.custId.eq(custid)
                );

        return Optional.ofNullable(jpaQuery.fetchOne());
    }
}
