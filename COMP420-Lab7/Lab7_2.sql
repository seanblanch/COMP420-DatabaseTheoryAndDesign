use saleco;

DROP PROCEDURE IF EXISTS sp_purchase_order;

DELIMITER //
CREATE PROCEDURE sp_purchase_order (IN productCode VARCHAR(10),IN customerCode INT, IN amount_purchased INT)
BEGIN 
	START TRANSACTION;
	SET @next_invoice_number = (SELECT MAX(INV_NUMBER) FROM INVOICE) + 1;    
	INSERT INTO invoice(INV_NUMBER, CUS_CODE, INV_DATE) VALUES (@next_invoice_number, customerCode, now());
    
    SET @updated_qoh = (SELECT P_QOH FROM PRODUCT WHERE P_CODE = productCode) - amount_purchased;
    SET @price_of_item = (SELECT P_PRICE FROM PRODUCT WHERE P_CODE = productCode);
    SET @amount_spent = @price_of_item * amount_purchased;
    IF @updated_qoh > 0 THEN
		UPDATE PRODUCT
			SET P_QOH = @updated_qoh
			WHERE P_CODE = productCode;
		UPDATE CUSTOMER
			SET CUS_BALANCE = CUS_BALANCE + @amount_spent
		WHERE CUS_CODE = customerCode;
		INSERT INTO line(INV_NUMBER, LINE_NUMBER, P_CODE, LINE_UNITS, LINE_PRICE) values (@next_invoice_number, 1, productCode, amount_purchased, @price_of_item);
		COMMIT;
    ELSE
		ROLLBACK;
	END IF;
END //
DELIMITER ;