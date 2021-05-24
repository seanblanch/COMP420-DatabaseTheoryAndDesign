use largeco;

CREATE VIEW LG_RECENT_CUST_PURCHASES 
AS
SELECT
	lgcustomer.CUST_CODE,
    lgcustomer.CUST_FNAME,
    lgcustomer.CUST_LNAME,
    lginvoice.INV_NUM,
    lginvoice.INV_DATE,
    lgline.LINE_NUM,
    lgline.LINE_QTY,
    lgline.LINE_PRICE,
    lgproduct.PROD_SKU,
    lgproduct.PROD_DESCRIPT,
    lgproduct.PROD_PRICE
FROM
	lgcustomer
JOIN
	lginvoice ON lgcustomer.cust_code=lginvoice.cust_code
JOIN
	lgline ON lginvoice.inv_num=lgline.inv_num
JOIN
	lgproduct ON lgline.prod_sku=lgproduct.prod_sku;
	