/**
 * RouteController
 *
 * @description :: Server-side logic for managing routes
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */

module.exports = {
	addPassenger: function(req, res){
		var routeId = req.param('idR');
		var body = req.body;
		var locationId = req.query.pickUp;

		if(!locationId) 
			return res.badRequest('pickUp header param missing');

		if(!body.id) 
			return res.badRequest('mising body param: id');

		var userId = body.id;

		Location.findOne({id : locationId})
		.populate('passengers')
		.exec(function(err, loc){
			if(err) {
				sails.log(err);
				return res.badRequest(err);
			}

			if(loc.route != routeId) {
				return res.badRequest('Location not in this route');
			}

			Route.findOne({ id : routeId })
			.populate('passengers')
			.exec(function(err, r){
				if(err) return res.send(400, err);
				if(r.capacity == 0) return res.badRequest("route at full capacity");

				r.passengers.add(userId);
				r.capacity--;
				r.save(function(err){
					if(err) return res.send(400, err);

					loc.passengers.add(userId);

					loc.save(function(err){
						if(err) return res.badRequest(err);

						Route.findOne({ id : routeId })
						.populate('passengers')
						.exec(function(err, r){
							if(err) return res.send(400, err);
							return res.send(r);
						});
					});

					
				});
			});
		});

		
	},

	removePassenger: function(req, res){
		var routeId = req.param('idR');
		var userId = req.param('idP');

		Route.findOne({ id : routeId })
		.populate('passengers')
		.exec(function(err, r){
			if(err) return res.send(400, err);

			r.passengers.remove(userId);
			r.capacity++;
			r.save(function(err){
				if(err) return res.send(400, err);



				Location.find({route : routeId})
				.populate('passengers')
				.exec(function(err, locs){
					if(err) return res.send(400, err);

					var found = false;
					for (var i = locs.length - 1; i >= 0 && !found; i--) {
						var loc = locs[i];
						for (var j = loc.passengers.length - 1; j >= 0 && !found; j--) {
							if(loc.passengers[j].id == userId){
								found = true;
								locs[i].passengers.remove(userId);

								locs[i].save(function(err){
									if(err) return res.send(400, err);

									Route.findOne({ id : routeId })
									.populate('passengers')
									.exec(function(err, r){
										if(err) return res.send(400, err);
										return res.send(r);
									});
								});
							};
						};
					};

					if(!found){ 
						return res.badRequest('no passenger found in route');
					}
				});
			});
		});
	},
	getPassengers: function(req, res){
		var routeId = req.param('idR');

		Route.findOne({id : routeId})
		.populate('passengers')
		.exec(function(err, r){
			if(err) return res.send(400, err);

			Location.find({route : routeId})
			.populate('passengers')
			.exec(function(err, locs){
				if(err) return res.send(400, err);

				

				for(var i=0;i<r.passengers.length; i++){
					var passenger = r.passengers[i];
					var found = false;
					for(var j=0;j<locs.length && !found; j++){
						var loc = locs[j];

						//revisar si el pasajero esta en la lista
						//de pasajeros de esa ubiacion

						for(var k=0;k<loc.passengers.length && !found; k++){
							var pass = loc.passengers[k];

							if(passenger.id == pass.id){
								passenger.pickUp = loc;
								found = true;
							}
						}
					}

					r.passengers[i] = passenger;
				}

				return res.send(r.passengers);
			});
			
		});
	}
};

