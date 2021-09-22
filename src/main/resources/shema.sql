create table if not exists PRODUCT
(
    ID
    BIGINT
    auto_increment
    primary
    key,
    NAME
    VARCHAR
(
    255
),
    PRICE INTERSECT
    );

create table if not exists USERS
(
    ID
    BIGINT
    auto_increment,
    NAME
    VARCHAR
    not
    null,
    constraint
    USERS_PK
    primary
    key
(
    ID
)
    );




create table if not exists USERS_PRODUCT
(
    ID
    BIGINT
    auto_increment,
    USER_ID
    BIGIN
    not
    null,
    PRODUCT_ID
    BIGIN
    not
    null,
    PRICE
    INT
    not
    null,
    COUNT
    INT
    not
    null,
    constraint
    TABLE_NAME_PK
    primary
    key
(
    ID
),
    constraint TABLE_NAME_PRODUCT_ID_FK foreign key
(
    PRODUCT_ID
) references PRODUCT
(
    ID
),
    constraint TABLE_NAME_USER_ID_FK foreign key
(
    USER_ID
) references USERS
(
    ID
),
    );