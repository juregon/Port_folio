import * as types from '../actions/ActionTypes';

const initialState = {
	execList : []
};

const data = (state=initialState, action) => {
	const {execList} = state;
	switch(action.type) {
		case types.ADD_EXEC_LIST:
			return {
				execList : [
					...state.execList,
					...action.data
				]
			};
		case types.ADD_EXEC:
			return {
				execList : [
					...state.execList,
					action.data
				]
			};
		case types.ADD_EXEC_EDIT:
			return {
				execList : [	
					...execList.slice(0, action.index),
					action.data,
					...execList.slice(action.index+1, execList.length)
				]
			};	
		default:
			return state;
	}

};

export default data;