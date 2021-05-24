db.cities.updateMany(
    {},
    {$set: {
        documentOwner: "Sean Blanchard",
        updateDate: new Date()}
    }
)

db.cities.find({})
