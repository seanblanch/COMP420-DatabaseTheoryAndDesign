ALTER TABLE LGCUSTOMER add primary key (Cust_Code);

ALTER TABLE LGINVOICE add primary key (Inv_Num);

ALTER TABLE LGLINE add constraint fk foreign key (Inv_Num) references LGINVOICE (Inv_Num);

ALTER TABLE LGLINE add primary key (Line_Num);

ALTER TABLE LGBRAND add primary key (Brand_ID);

ALTER TABLE LGPRODUCT add primary key (Prod_Sku);

ALTER TABLE LGEMPLOYEE add primary key (Emp_Num);

ALTER TABLE LGSALARY_HISTORY add constraint fk_anything1 foreign key (Emp_Num) references LGEMPLOYEE (Emp_Num);

ALTER TABLE LGDEPARTMENT add primary key (Dept_Num);

ALTER TABLE LGSUPPLIES add constraint fk2 foreign key (Vend_ID) references LGVENDOR (Vend_ID);

ALTER TABLE LGSUPPLIES add constraint fk3 foreign key (Prod_Sku) references LGPRODUCT (Prod_Sku);

ALTER TABLE LGVENDOR add primary key (Vend_ID);

ALTER TABLE LGINVOICE add constraint fk4 foreign key (Cust_Code) references LGCUSTOMER (Cust_Code);

ALTER TABLE LGINVOICE add constraint fk5 foreign key (Employee_ID) references LGEMPLOYEE (Emp_Num);

ALTER TABLE LGLINE add constraint fk6 foreign key (Prod_Sku) references LGPRODUCT (Prod_Sku);

ALTER TABLE LGPRODUCT add constraint fk7 foreign key (Brand_ID) references LGBRAND (Brand_ID);

ALTER TABLE LGEMPLOYEE add constraint fk8 foreign key (Dept_Num) references LGDEPARTMENT (Dept_Num);

ALTER TABLE LGDEPARTMENT add constraint fk9 foreign key (Emp_Num) references LGEMPLOYEE (Emp_Num);