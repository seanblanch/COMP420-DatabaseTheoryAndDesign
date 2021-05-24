use largeco;

CREATE TABLE USER_ACCOUNT(
    Username INT AUTO_INCREMENT,
    FName VARCHAR(50),
    LName VARCHAR(50),
    EMPorCUST ENUM('customer', 'employee'),
    EMP_NUM DECIMAL(6,0),
    CUST_CODE DECIMAL(38,0),
    Password VARBINARY(10),
    PRIMARY KEY ( Username ),
    FOREIGN KEY (EMP_NUM) REFERENCES lgemployee(EMP_NUM),
    FOREIGN KEY (CUST_CODE) REFERENCES lgcustomer(CUST_CODE)
);

#Write a stored proceedure
#5 employees 5 Customer and insert into user acct
#select all row from employee
#limit to 5
#select all row from customer
#limit to 5
#insert into USER_ACCOUNT


INSERT INTO user_account (FName, LName, EMPorCUST, EMP_NUM, Password)
SELECT EMP_FNAME, EMP_LNAME, 'employee', EMP_NUM, 'Password'
FROM lgemployee 
LIMIT 5;

INSERT INTO user_account (FName, LName, EMPorCUST, CUST_CODE, Password)
SELECT CUST_FNAME, CUST_LNAME, 'customer', CUST_CODE, 'Password'
FROM lgcustomer
LIMIT 5;