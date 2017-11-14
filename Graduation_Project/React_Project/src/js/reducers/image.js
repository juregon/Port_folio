import * as types from '../actions/ActionTypes';

const initialState = {
	imageList : []
};

const data = (state=initialState, action) => {
	const {imageList} = state;
	switch(action.type) {
		case types.ADD_IMAGE_LIST:
			return {
				imageList : [
					...state.imageList,
					...action.data
				]
			};
		case types.ADD_IMAGE:
			return {
				imageList : [
					...state.imageList,
					action.data
				]
			};
		case types.ADD_IMAGE_EDIT:
			return {
				imageList : [	
					...imageList.slice(0, action.index),
					action.data,
					...imageList.slice(action.index+1, imageList.length)
				]
			};	
		default:
			return state;
	}

};

export default data;