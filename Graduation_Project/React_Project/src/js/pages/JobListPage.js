import React, { Component } from 'react';
import TableBox from '../components/TableBox';
import Page from './Page';
import TableTitle from '../components/TableTitle';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import TableHeader from '../components/TableHeader';
import PropTypes from 'prop-types';
import DialogBg from '../components/DialogBg';
import Dialog from '../components/Dialog';

import NewTable from '../components/NewTable';
import NewTableRow from '../components/NewTableRow';
import * as actions from '../actions';
import { connect } from 'react-redux';

import classNames from 'classnames/bind';

class JobListPage extends Component {
	constructor() {
		super();
		this.state = {
			isDialogOn: false
		};
		this.openDialog = this.openDialog.bind(this);
	}

	openDialog() {
		this.setState(prevState => ({
			isDialogOn : !prevState.isDialogOn
		}));
	}


	render() {
		const { isDialogOn } = this.state;
		const { jobData, onChangeInput } = this.props;
		const { joblist } = this.props;

		const JobTable = joblist.map((data,i) => {
			return (
			       <NewTableRow key={i} name={data.Name} date={data.Date} status={data.Status} description={data.Description} />
				);
		})

		return (
			<div>					
				<DialogBg isDialogOn={isDialogOn}/>
				<Dialog isDialogOn={isDialogOn} btnClick={this.openDialog} jobData={jobData} onChangeInput={onChangeInput}/>
				<NewTable title={"Job List"} btnText="Job Create" btnClick={this.openDialog} firstBtn={true}>
					<NewTableRow header={true} name="Name" date="Date" status="Status" description="Description"/>
					{JobTable}
				</NewTable>
			</div>
			);
	}
}

export default connect(
	(state) => ({
		jobData: state.jobData,
		joblist: state.jobList.jobList
	}),
	(dispatch) => ({
		onChangeInput: (e) => dispatch(actions.changeinput(e.target))
	})
)(JobListPage);
