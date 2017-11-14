import JobListPage from '../pages/JobListPage';
import * as actions from '../actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	imagelist: state.imageList.imageList
})

const JobListContainer = connect(
    mapStateToProps
)(JobListPage);



export default JobListContainer;