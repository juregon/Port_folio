import * as types from '../actions/ActionTypes';

const initialState = {
	dataList : []
};

const data = (state=initialState, action) => {
	const {dataList} = state;
	switch(action.type) {
		case types.ADD_DATA_LIST:
			return {
				dataList : [
					...state.dataList,
					...action.data
				]
			};
		case types.ADD_DATA:
			return {
				dataList : [
					...state.dataList,
					action.data
				]
			};
		case types.ADD_DATA_EDIT:
		return {
			dataList : [	
				...dataList.slice(0, action.index),
				action.data,
				...dataList.slice(action.index+1, dataList.length)
			]
		};	
		default:
			return state;
	}

};

export default data;