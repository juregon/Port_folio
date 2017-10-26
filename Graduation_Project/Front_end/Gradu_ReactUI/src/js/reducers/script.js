import * as types from '../actions/ActionTypes';

const initialState = {
	scriptList : []
};

const data = (state=initialState, action) => {
	const {scriptList} = state;
	switch(action.type) {
		case types.ADD_SCRIPT_LIST:
			console.log("2");
			return {
				scriptList : [
					...state.scriptList,
					...action.data
				]
			};
		case types.ADD_SCRIPT:
			console.log("3");
			return {
				scriptList : [
					...state.scriptList,
					action.data
				]
			};
		case types.ADD_SCRIPT_EDIT:
			console.log("4");
		return {
			scriptList : [	
				...scriptList.slice(0, action.index),
				action.data,
				...scriptList.slice(action.index+1, scriptList.length)
			]
		};	
		default:
			return state;
	}

};

export default data;