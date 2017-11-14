import * as types from '../actions/ActionTypes';

const initialState = {
	jobname:"",
	description:"",
	cpus:"",
	mem:"",
	disk:"",
	imagename:""
};

const job = (state = initialState, action) => {
	var target = action.data;
	
	switch(action.type) {
		case types.CHANGE_INPUT:
			return Object.assign({}, state, {
				[target.name] : target.value
			});
		case types.INITIALIZE:
			return {
				jobname:"",
				description:"",
				cpus:"",
				mem:"",
				disk:"",
				imagename:""
			}			
		default:
			return state;
	}
};

export default job;

