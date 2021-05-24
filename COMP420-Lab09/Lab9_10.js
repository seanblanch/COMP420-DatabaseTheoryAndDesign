db.cities.mapReduce(    
                    function() {emit(this.city, this._id); },
                     function(key, values){return Array.sum(values); },
                     {
                         out: "zip_counter"
                     }
    )
    
    
    
