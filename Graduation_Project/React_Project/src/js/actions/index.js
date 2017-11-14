import * as types from './ActionTypes';

export const addDataList = (data) => ({
	type: types.ADD_DATA_LIST,
	data
});
export const addExecList = (data) => ({
	type: types.ADD_EXEC_LIST,
	data
});
export const addScriptList = (data) => ({
	type: types.ADD_SCRIPT_LIST,
	data
});
export const addImageList = (data) => ({
	type: types.ADD_IMAGE_LIST,
	data
});
export const addJobList = (data) => ({
	type: types.ADD_JOB_LIST,
	data
});

export const addData = (data) => ({
	type: types.ADD_DATA,
	data
});
export const addExec = (data) => ({
	type: types.ADD_EXEC,
	data
});
export const addScript = (data) => ({
	type: types.ADD_SCRIPT,
	data
});
export const addImage = (data) => ({
	type: types.ADD_IMAGE,
	data
});
export const addJob = (data) => ({
	type: types.ADD_JOB,
	data
});

export const addDataEdit = (data, index) => ({
	type: types.ADD_DATA_EDIT,
	data,
	index
});
export const addExecEdit = (data, index) => ({
	type: types.ADD_EXEC_EDIT,
	data,
	index
});
export const addScriptEdit = (data, index) => ({
	type: types.ADD_SCRIPT_EDIT,
	data,
	index
});
export const addImageEdit = (data, index) => ({
	type: types.ADD_IMAGE_EDIT,
	data,
	index
});
export const addJobEdit = (data, index) => ({
	type: types.ADD_JOB_EDIT,
	data,
	index
});

export const changeinput = (data) => ({
	type: types.CHANGE_INPUT,
	data: data
});

export const initialize = (data) => ({
	type: types.INITIALIZE,
	data: data
});