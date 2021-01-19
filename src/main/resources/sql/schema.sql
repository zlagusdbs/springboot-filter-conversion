--
-- 매핑테이블
--
DROP TABLE IF EXISTS MBER_CUST_MAPNG;

CREATE TABLE MBER_CUST_MAPNG COMMENT '매핑테이블'
(
    MBER_NO VARCHAR(10) NOT NULL COMMENT '클라우드멤버십 회원번호',
    CUST_ID VARCHAR(10) NOT NULL COMMENT '신세계포인트 회원번호',
    PRIMARY KEY (MBER_NO, CUST_ID)
    );
