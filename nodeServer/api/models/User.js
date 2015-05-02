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
  	puntos:{
  		type: 'int',
  		defaultsTo: 0
  	},
  	email:{
  		type: 'email'
  	},
  	amigos:{
  		collection: 'user',
  		via: 'amigos'
  	},
  	rutasP: {//rutas como pasajero
  		collection: 'route',
  		via: 'pasajeros'
  	},
  	rutasC: {//rutas como conductor
  		collection: 'route',
  		via: 'conductor'
  	},
  	notificaciones:{
  		collection: 'event',
  		via: 'usuarioDestino'
  	}
  }
};

