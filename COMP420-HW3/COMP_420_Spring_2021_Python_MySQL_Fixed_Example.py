import mysql.connector
import csv

db = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="",
    database="saleco"
)

cursor = db.cursor()
cursor.execute("select v.* from saleco.vendor v")
result = cursor.fetchall()

with open("vendors.csv", "w") as vendor_file:
    vendor_writer = csv.writer(vendor_file, delimiter=',')
    vendor_writer.writerow(["Vendor Name", "Vendor Contact", "Vendor Phone", "Vendor State"])
    for row in result:
        vendor_writer.writerow([
            row[1], # Vendor Name
            row[2], # Vendor Contact
            row[4], # Vendor Phone
            row[5]  # Vendor State
        ])

cursor.close()
db.close()
