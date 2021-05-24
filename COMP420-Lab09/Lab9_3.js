db.getCollection("cities").find(
    {$or: [
            {"city": "NEW YORK"},
            {"city": "CALIFORNIA"}
            ]
    }
).sort({"city": 1})
