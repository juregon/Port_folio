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

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

class UploadPage extends Component {
	constructor() {
		super();
		this.uploadAction = this.uploadAction.bind(this);
		this.getProgressAction = this.getProgressAction.bind(this);
		this.onFileChange = this.onFileChange.bind(this);
		this.makeObject = this.makeObject.bind(this);
		this.onDesChange = this.onDesChange.bind(this);
		this.state = {
			stateman : true
		};
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

	uploadAction(type) {		
		const { datalist, execlist, addData, addExec, addDataEdit, addExecEdit } = this.props;

		var data = new FormData();

		const fileData = this.state[type];
		const desText = this.state[type+'Text'];
		const fileName = fileData.name;

		data.append("fileData", fileData);
		data.append("descrip", desText);
		data.append("type", type);

		var d = new Date();
    	var uploadId = d.getTime();
    	var currentDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();

    	var newItemObj = this.makeObject(fileName, currentDate, "Uploading", desText);
    	var listLength = Object.keys(datalist).length;
    	var listLength2 = Object.keys(execlist).length;
	    if(type=="Data") {
	    		addData(newItemObj); 	
	    } else {
	    		addExec(newItemObj);
	    }
		fetch("http://localhost:8080/project/fileupload?uploadId="+uploadId, {
			method: "POST",
			credentials: "include",
			header: {
				"Accept" : "application/json"
			},
			body: data
		}).then(function(res) {
			if( res.ok) {
				clearInterval(this.state[uploadId]);
				var newObj = Object.assign(newItemObj,{Status:"Done"});
				if(type=="Data") {
					addDataEdit(newItemObj,listLength);	
				} else {
					addExecEdit(newItemObj,listLength2);
				}
				
			} 
			else {
				var newObj = Object.assign(newItemObj,{Status:"Fail"});
				if(type=="Data") {
					addDataEdit(newItemObj,listLength);	
				} else {
					addExecEdit(newItemObj,listLength2);
				}
			}
		}.bind(this), function(e) {
			var newObj = Object.assign(newItemObj,{Status:"Fail"});
			if(type=="Data") {
				addDataEdit(newItemObj,listLength);	
			} else {
				addExecEdit(newItemObj,listLength2);
			}
		}.bind(this));
		console.log(uploadId);
		var signal = setInterval(()=>this.getProgressAction(uploadId, type, newItemObj, listLength, listLength2), 1000);
		this.setState({
			[uploadId] : signal
		})
		
	}

	getProgressAction(uploadId, type, newItemObj, listLength, listLength2) {		
		const { addDataEdit, addExecEdit } = this.props;
		var progress;
		fetch("http://localhost:8080/project/progresscheck?uploadId="+uploadId, {
			method: "GET",
			credentials: "include",
			headers: {
				"Accept" : 'application/json'
			}
		}).then(response => {
			response.json().then(data => ({
				data: data,
				status: response.status
			})).then(res => {
				

				let br = res.data.bytesRead * 100;
				var clstr = res.data.contentLength
				clstr *= 1;
				var newObj = Object.assign(newItemObj,{Status:"Uploading(" + Math.ceil(br / clstr) + "%)"});
				if(type=="Data") {
					addDataEdit(newItemObj,listLength);	
				} else {
					addExecEdit(newItemObj,listLength2);
				}

			})});
	
	}	


	render() {

		const { datalist, execlist } = this.props;

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
		/*const DataTable = datalist.map(function(data,i) {
		   return (
		   	<TableRow header={true}	key={i}>
		       <TableItem>{data.Name}</TableItem>
		       <TableItem>{data.Date}</TableItem>
		       <TableItem>{data.Status}</TableItem>
		       <TableItem>{data.Description}</TableItem>
		    </TableRow>
		   );
		});
		const ExecTable = execlist.map(function(data,i) {
		   return (
		   	<TableRow header={true}	key={i}>
		       <TableItem>{data.Name}</TableItem>
		       <TableItem>{data.Date}</TableItem>
		       <TableItem>{data.Status}</TableItem>
		       <TableItem>{data.Description}</TableItem>
		    </TableRow>
		   );
		});*/
		return (
			/*<Page>
				<TableBox>
					<button onClick={()=>this.uploadAction("Data")}>UPLOAD</button>					
					<TableHeader title="data" id="Data" onFileChange={this.onFileChange} onDesChange={this.onDesChange} />
					<TableRow header={true}>
						<TableItem>File Name</TableItem>
						<TableItem>Date</TableItem>
						<TableItem>Status</TableItem>
						<TableItem>Description</TableItem>
					</TableRow>
					{DataTable}					
				</TableBox>
				<TableBox>
					<button onClick={()=>this.uploadAction("Exec")}>UPLOAD</button>					
					<TableHeader title="exec" id="Exec" onFileChange={this.onFileChange} onDesChange={this.onDesChange} />
					<TableRow header={true}>
						<TableItem>File Name</TableItem>
						<TableItem>Date</TableItem>
						<TableItem>Status</TableItem>
						<TableItem>Description</TableItem>
					</TableRow>
					{ExecTable}
				</TableBox>
			</Page>*/
			<Page>
				<div className={ cn({'disable':!this.state.stateman})}>
					<NewTable title={"Data List"} btnText="Exec List" btnClick={this.toggleBtn.bind(this)}>
						<button onClick={()=>this.uploadAction("Data")}>UPLOAD</button>					
						<TableHeader title="data" id="Data" onFileChange={this.onFileChange} onDesChange={this.onDesChange} />
						<NewTableRow  header={true} name="Name" date="Date" status="Status" description="Description"/>
						{DataTable}
					</NewTable>
				</div>

				<div className={ cn({'disable':this.state.stateman})}>
					<NewTable title={"Exec List"} btnText="Data List" btnClick={this.toggleBtn.bind(this)}>
						<button onClick={()=>this.uploadAction("Exec")}>UPLOAD</button>					
						<TableHeader title="exec" id="Exec" onFileChange={this.onFileChange} onDesChange={this.onDesChange} />
						<NewTableRow  header={true} name="Name" date="Date" status="Status" description="Description"/>
						{ExecTable}
					</NewTable>            				            		
				</div>
			
			</Page>
			);
	}
}

export default UploadPage;