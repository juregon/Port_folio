/*import React, { Component } from 'react';
import TableBox from '../components/TableBox';
import Page from './Page';
import TableTitle from '../components/TableTitle';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import TableHeader from '../components/TableHeader';
import PropTypes from 'prop-types';
import classNames from 'classnames/bind';

class JobListPage extends Component {
	render() {
		return (
			<Page>
				<TableBox>
				<button>Hello</button>
					<button>gogo</button>
					<div>Job List</div>
					
					<TableRow header={true}>
						<TableItem>Name</TableItem>
						<TableItem>Date</TableItem>
						<TableItem>Status</TableItem>
						<TableItem>Description</TableItem>
					</TableRow>
				</TableBox>
			</Page>
			);
	}
}

export default JobListPage;*/

import React, { Component } from 'react';
import TableBox from '../components/TableBox';
import Page from './Page';
import TableTitle from '../components/TableTitle';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import TableHeader from '../components/TableHeader';
import PropTypes from 'prop-types';

import NewTable from '../components/NewTable';
import NewTableRow from '../components/NewTableRow';

import classNames from 'classnames/bind';

class JobListPage extends Component {
	render() {
		return (
			<NewTable title={"Job List"} btnText="Job Create">
				<NewTableRow header={true} name="Name" date="Date" status="Status" description="Description"/>
				



			</NewTable>
			);
	}
}

export default JobListPage;