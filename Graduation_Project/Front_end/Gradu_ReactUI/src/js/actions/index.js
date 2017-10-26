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
