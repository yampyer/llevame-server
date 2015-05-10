/**
* Event.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

module.exports = {

  attributes: {
  	message: {
  		type: 'string',
  		required: true
  	},
  	targetUser: {
  		model: 'user',
  		required: true
  	},
  	isNotification: {
  		type: 'boolean',
  		required: true
  	},
  	//solo para invitaciones
  	acepted: {
  		type: 'boolean'
  	},
  	type: { //invitacion a una ruta o de amistad 0-ruta, 1-amistad
  		type: 'integer',
  	},
  	sender: {
  		model: 'user'
  	},
  	route: {
  		model: 'route'
  	},
  	pickup: {
  		model: 'location'
  	}
  }
};

