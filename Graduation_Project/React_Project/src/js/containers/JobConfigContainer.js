import JobConfigPage from '../pages/JobConfigPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	datalist: state.dataList.dataList,
    execlist: state.execList.execList,
    scriptlist : state.scriptList.scriptList,
    imagelist: state.imageList.imageList
})

const mapDispatchToProps = (dispatch) => ({
	addImage : (data) => dispatch(actions.addImage(data)),
	addImageEdit : (data, index) => dispatch(actions.addImageEdit(data, index))
});

const JobConfigContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(JobConfigPage);



export default JobConfigContainer;