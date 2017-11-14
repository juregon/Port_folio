import React, { Component } from 'react';
import styles from 'stylesdir/pages/UploadPage.scss';
import FlexBox from '../components/FlexBox';
import TableBox from '../components/TableBox';
import Page from './Page';
import TableTitle from '../components/TableTitle';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import TableHeader from '../components/TableHeader';
import NewTable from '../components/NewTable';
import NewTableRow from '../components/NewTableRow';
import DialogBg from '../components/DialogBg';
import FileUploadDialog from '../components/FileUploadDialog';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

class UploadPage extends Component {
	constructor() {
		super();
		this.onFileChange = this.onFileChange.bind(this);
		this.makeObject = this.makeObject.bind(this);
		this.onDesChange = this.onDesChange.bind(this);
		this.toggleUploadDialog = this.toggleUploadDialog.bind(this);
		this.state = {
			stateman : true,
			isDialogOn : false
		};
	}

	toggleUploadDialog() {
		this.setState(prevState => ({
			isDialogOn: !prevState.isDialogOn
		}));
	}

	toggleBtn() {
		this.setState(prevState=>({
			stateman:!prevState.stateman
		}));
	}

	onFileChange(e) {
		const targetName = e.target.name;
		const targetFile = e.target.files[0];

		this.setState({
			[targetName] : targetFile
		});	
	}

	onDesChange(e) {
		const targetName = e.target.name + "Text";
		const targetText = e.target.value;

		this.setState({
			[targetName]:targetText
		});
	}

	makeObject(fileName, currentDate, status, description) {
		var obj = new Object();
	    	obj.Name = fileName;
	    	obj.Date = currentDate;
	    	obj.Status = "Uploading(0%)";
	    	obj.Description = description;
	    	return obj;
	}


	render() {

		const { datalist, execlist, addData, addDataEdit, addExecEdit, addExec } = this.props;
		const { isDialogOn } =  this.state;

		const DataTable = datalist.map((data,i) => {
			return (
			       <NewTableRow name={data.Name} date={data.Date} status={data.Status} description={data.Description} />
				);
		})

		const ExecTable = execlist.map((data,i) => {
			return (
			       <NewTableRow name={data.Name} date={data.Date} status={data.Status} description={data.Description} />
				);
		})
		
		return (
			<div>
				<DialogBg isDialogOn={isDialogOn}/>
				<FileUploadDialog addDataEdit={addDataEdit} addExecEdit={addExecEdit} datalist={datalist} execlist={execlist} addExec={addExec} addData={addData} pagename={this.state.stateman} isDialogOn={isDialogOn} toggleUploadDialog={this.toggleUploadDialog}/>
				<Page>
					<div className={ cn({'disable':!this.state.stateman})}>
						<NewTable title={"Data List"} toggleUploadDialog={this.toggleUploadDialog} btnText="Exec List" btnClick={this.toggleBtn.bind(this)}>
							<NewTableRow  header={true} name="Name" date="Date" status="Status" description="Description"/>
							{DataTable}
						</NewTable>
					</div>

					<div className={ cn({'disable':this.state.stateman})}>
						<NewTable title={"Exec List"} toggleUploadDialog={this.toggleUploadDialog} btnText="Data List" btnClick={this.toggleBtn.bind(this)}>
							<NewTableRow  header={true} name="Name" date="Date" status="Status" description="Description"/>
							{ExecTable}
						</NewTable>            				            		
					</div>				
				</Page>
			</div>
			
			);
	}
}

export default UploadPage;