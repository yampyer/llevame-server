/**
* User.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

module.exports = {

  attributes: {
  	username:{
  		type: 'string',
  		unique: true,
  		required: true
  	}, 
  	password:{
  		type: 'string',
  		required: true
  	},
  	score:{
  		type: 'int',
  		defaultsTo: 0
  	},
  	email:{
  		type: 'email'
  	},
  	friends:{
  		collection: 'user',
  		via: 'friends'
  	},
  	routesP: {//rutas como pasajero
  		collection: 'route',
  		via: 'passengers'
  	},
  	routesD: {//rutas como conductor
  		collection: 'route',
  		via: 'driver'
  	},
  	events:{
  		collection: 'event',
  		via: 'targetUser'
  	}
  }
};

