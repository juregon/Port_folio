/*import ScriptPage from '../pages/ScriptPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	posts: state.posts,
	Data : state.Data
})


function fetchPosts() {
 	const URL = "http://localhost:8080/project/list/3";
  	return fetch(URL, { method: 'GET', dataType: "json"})
     .then( response => Promise.all([response, response.json()]));
}

const CounterContainer = connect(
    mapStateToProps
)(ScriptPage);

export default CounterContainer;*/

import JobConfigPage from '../pages/JobConfigPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	datalist: state.dataList.dataList,
    execlist: state.execList.execList,
    scriptlist : state.scriptList.scriptList
})

/*const mapDispatchToProps = (dispatch) => ({
	addData : (data) => dispatch(actions.addData(data)),
	addExec : (data) => dispatch(actions.addExec(data)),
	addDataEdit : (data, index) => dispatch(actions.addDataEdit(data, index)),
	addExecEdit : (data, index) => dispatch(actions.addExecEdit(data, index))
});*/

const JobConfigContainer = connect(
    mapStateToProps
)(JobConfigPage);



export default JobConfigContainer;