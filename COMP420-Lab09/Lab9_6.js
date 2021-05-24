db.cities.find(
    {$or: [
            { city: { $regex: / NEW / } },
            { city: { $regex: /NEW / } }
            ]
    }
)