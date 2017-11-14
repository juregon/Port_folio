import data from './data';
import exec from './exec';
import script from './script';
import image from './image';
import job from './job';
import joblist from './joblist'

import { combineReducers } from 'redux';

const reducers = combineReducers({
  dataList: data,
  execList: exec,
  scriptList: script,
  imageList: image,
  jobList: joblist,
  jobData: job
});

export default reducers;