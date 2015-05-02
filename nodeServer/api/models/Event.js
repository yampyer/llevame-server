/**
* Event.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

module.exports = {

  attributes: {
  	mensaje: {
  		type: 'string',
  		required: true
  	},
  	usuarioDestino: {
  		model: 'user'
  	},
  	esNotificacion: {
  		type: 'boolean',
  		required: true
  	},
  	//solo para invitaciones
  	aceptado: {
  		type: 'boolean'
  	},
  	tipo: {
  		type: 'integer',
  	},
  	creador: {
  		model: 'user'
  	},
  	ruta: {
  		model: 'route'
  	},
  	pickup: {
  		model: 'location'
  	}
  }
};

