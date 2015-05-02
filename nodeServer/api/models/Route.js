/**
* Route.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

module.exports = {

  attributes: {
  	nombre:{
  		type: 'string',
  		required: true
  	},
  	fecha:{
  		type: 'datetime',
  		required: true
  	},
  	capacidad:{
  		type: 'integer',
  		required: true
  	}, 
  	descripcion:{
  		type: 'text'	
  	},
  	placa:{
  		type: 'string',
  		required: true
  	},
  	recorrido:{
  		collection: 'location',
  		via: 'ruta'
  	}, 
  	conductor: {
  		model: 'user',
  		required: true
  	},
  	estado: {
  		type: 'boolean',
  		defaultsTo: false
  	},
  	pasajeros: {
  		collection: 'user',
  		via: 'rutasP'
  	}
  }
};

