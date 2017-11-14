import * as types from '../actions/ActionTypes';

const initialState = {
	jobList : []
};

const data = (state=initialState, action) => {
	const {jobList} = state;
	switch(action.type) {
		case types.ADD_JOB_LIST:
			return {
				jobList : [
					...state.jobList,
					...action.data
				]
			};
		case types.ADD_JOB:
			return {
				jobList : [
					...state.jobList,
					action.data
				]
			};
		case types.ADD_JOB_EDIT:
			return {
				jobList : [	
					...jobList.slice(0, action.index),
					action.data,
					...jobList.slice(action.index+1, jobList.length)
				]
		};		
		default:
			return state;
	}

};

export default data;


		