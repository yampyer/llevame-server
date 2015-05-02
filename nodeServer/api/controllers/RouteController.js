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

		if(!body.id) 
			return res.badRequest('mising body param: id');

		var userId = body.id;

		Route.findOne({ id : routeId })
		.populate('passengers')
		.exec(function(err, r){
			if(err) return res.send(400, err);
			if(r.capacity == 0) return res.badRequest("route at full capacity");

			r.passengers.add(userId);
			r.capacity--;
			r.save(function(err){
				if(err) return res.send(400, err);

				Route.findOne({ id : routeId })
				.populate('passengers')
				.exec(function(err, r){
					if(err) return res.send(400, err);
					return res.send(r);
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

				Route.findOne({ id : routeId })
				.populate('passengers')
				.exec(function(err, r){
					if(err) return res.send(400, err);
					return res.send(r);
				});
			});
		});
	}
};

