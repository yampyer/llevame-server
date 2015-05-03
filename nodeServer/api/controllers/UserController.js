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

				sails.log(me.friends);
				//TODO: eliminar los resultados que son amigos del usuario

				return res.send(ulike);
			});
		});
	}
};

