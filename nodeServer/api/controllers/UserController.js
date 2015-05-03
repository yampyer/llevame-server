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
				//TODO: eliminar los resultados que son amigos del usuario
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
	}
};

