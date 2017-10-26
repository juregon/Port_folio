import data from './data';
import exec from './exec';
import script from './script';

import { combineReducers } from 'redux';


const reducers = combineReducers({
  dataList: data,
  execList: exec,
  scriptList: script
});

export default reducers;