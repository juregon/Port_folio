import ScriptPage from '../pages/ScriptPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	scriptlist: state.scriptList.scriptList
})

const mapDispatchToProps = (dispatch) => ({
	addScript : (data) => dispatch(actions.addScript(data)),
	addScriptEdit : (data, index) => dispatch(actions.addScriptEdit(data, index))
});

const ScriptContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(ScriptPage);

export default ScriptContainer;