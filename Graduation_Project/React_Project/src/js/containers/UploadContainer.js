import UploadPage from '../pages/UploadPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	datalist: state.dataList.dataList,
    execlist: state.execList.execList
})

const mapDispatchToProps = (dispatch) => ({
	addData : (data) => dispatch(actions.addData(data)),
	addExec : (data) => dispatch(actions.addExec(data)),
	addDataEdit : (data, index) => dispatch(actions.addDataEdit(data, index)),
	addExecEdit : (data, index) => dispatch(actions.addExecEdit(data, index))
});

const UploadContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(UploadPage);



export default UploadContainer;