db.cities.find({
    "location.0": {
        $gte: -124.26,
        $lte: -114.8
    },
    "location.1": {
        $gte: 32.32,
        $lte: 42.00
    }
})
    
