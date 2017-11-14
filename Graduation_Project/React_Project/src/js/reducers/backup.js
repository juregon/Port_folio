import * as types from '../actions/ActionTypes';

const initialState = {
    Data : [11, 22, 3, 4, 5],
    posts : []
};


const counter = (state = initialState, action) => {
  switch (action.type) {
    case types.FETCH_REQUEST:
      return state;
    case types.FETCH_SUCCESS: 
      return {...state, posts: action.payload};
    default:
      return state;
  }
} 

export default counter;
