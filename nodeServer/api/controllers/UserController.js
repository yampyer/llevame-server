/**
 * UserController
 *
 * @description :: Server-side logic for managing users
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */

module.exports = {
	likeUsername: function(req, res){
		var idUser = req.param('idUser');
		var namePattern = req.query.username;

		if(!namePattern) return res.badRequest('username header param missing');

		User.find({ username: {'like':'%'+namePattern+'%'}})
		.exec(function(err, ulike){
			if(err) return res.send(400, err);

			User.findOne({id: idUser})
			.populate('friends')
			.exec(function(err, me){
				if(err) return res.send(400, err);

				var vals = me.friends;
				//eliminar los resultados que son amigos del usuario
				for (var j=0;j<vals.length; j++) {
		      		for(i = ulike.length; i--;){
	    	      		if (ulike[i].id === vals[j].id) ulike.splice(i, 1);
			        }
			    }

			    ulike.forEach(function(u, i, object){
			    	if (u.id === me.id) ulike.splice(i, 1);
			    });
				

				return res.send(ulike);
			});
		});
	},

	addFriend: function(req, res){
		var userId = req.param('idU');
		var body = req.body;

		if(!body.id) 
			return res.badRequest('mising body param: id');

		var friendId = body.id;

		User.findOne({ id : userId })
		.populate('friends')
		.exec(function(err, u){
			if(err) return res.send(400, err);
			
			u.friends.add(friendId);
			
			u.save(function(err){
				if(err) return res.send(400, err);

				//save me as friend of my friend
				User.findOne({ id : friendId })
				.populate('friends')
				.exec(function(err, f){
					if(err) return res.send(400, err);

					f.friends.add(userId);

					f.save(function(err){
						if(err) return res.send(400, err);

						User.findOne({ id : userId })
						.populate('friends')
						.exec(function(err, u){
							if(err) return res.send(400, err);
							return res.send(u);
						});
					});
				});
			});
		});
	},

	removeFriend: function(req, res){
		var userId = req.param('idU');
		var friendId = req.param('idF');

		User.findOne({ id : userId })
		.populate('friends')
		.exec(function(err, u){
			if(err) return res.send(400, err);

			u.friends.remove(friendId);
			u.save(function(err){
				if(err) return res.send(400, err);

				User.findOne({ id : friendId })
				.populate('friends')
				.exec(function(err, f){
					if(err) return res.send(400, err);

					f.friends.remove(userId);
					f.save(function(err){
						if(err) return res.send(400, err);

						User.findOne({ id : userId })
						.populate('friends')
						.exec(function(err, u){
							if(err) return res.send(400, err);
							return res.send(u);
						});
					});
				});
			});
		});
	}

};

