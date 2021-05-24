db.cities.update(
    { _id: "93010" },
    { $set: { population: "46,063"}}
    )
    
db.cities.find({ _id : "93010"})