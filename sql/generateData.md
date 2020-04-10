````sql
set global log_bin_trust_function_creators=TRUE;

# 随机产生字符串
DELIMITER $$
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmlopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE i < n DO
            SET return_str = CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
            SET i = i+1;
        END WHILE;
    RETURN return_str;
END $$

# 随机产生编号
DELIMITER $$
CREATE FUNCTION rand_num() RETURNS INT(5)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET i = FLOOR(100+RAND()*10);
    RETURN i;
END $$

delimiter $$
create procedure insert_user(IN START INT(10), IN max_num INT(10))
begin
    declare i int default 0;
    # set autocommit =0 把autocommit设置成0，把默认提交关闭
    SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        insert into sys_user(user_id, username, name, password, gender, birthday, create_time, create_user) values ((START+i), rand_string(6), 'init', '21232f297a57a5a743894a0e4a801fc3', 'M', now(), now(), 'init');
    UNTIL i = max_num
        END REPEAT;
    COMMIT;
end $$

call insert_user(1000, 3000000);

-- 测试while循环
DELIMITER $$
CREATE PROCEDURE test(IN START INT(10))
BEGIN
    DECLARE i INT DEFAULT 0;
    # set autocommit =0 把autocommit设置成0，把默认提交关闭
    WHILE i < START do
            set i = i + 1;
        end WHILE;
    SELECT i;
END $$
CALL test(10);



````