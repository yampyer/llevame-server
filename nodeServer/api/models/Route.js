/**
* Route.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

module.exports = {

  attributes: {
  	name:{
  		type: 'string',
  		required: true
  	},
  	date:{
  		type: 'datetime',
  		required: true
  	},
  	capacity:{
  		type: 'integer',
  		required: true
  	}, 
  	description:{
  		type: 'text'	
  	},
  	licensePlate:{
  		type: 'string',
  		required: true
  	},
  	travelPlan:{
  		collection: 'location',
  		via: 'route'
  	}, 
  	driver: {
  		model: 'user',
  		required: true
  	},
  	state: {
  		type: 'boolean',
  		defaultsTo: false
  	},
  	passengers: {
  		collection: 'user',
  		via: 'routesP'
  	}
  }
};

