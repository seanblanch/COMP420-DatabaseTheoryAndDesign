db.cities.aggregate([
    {$match: {city: "LOS ANGELES"}},
    {$group: {_id: "$city",
                total: {$sum: "$population"}}}
    ])
    
db.cities.find(
    {"city": "LOS ANGELES"}
    )

