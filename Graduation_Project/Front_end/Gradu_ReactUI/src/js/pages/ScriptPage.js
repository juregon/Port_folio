import React, { Component } from 'react';
import styles from 'stylesdir/pages/ScriptPage.scss';
import $ from 'jquery';
//import FlexBox from '../components/FlexBox';
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
const cn = classNames.bind(styles);

class ScriptPage extends Component {
	constructor() {
		super();
		this.state = {
			stateman : true,
			filename: "",
			script: ""
		};
		this.onTextChange = this.onTextChange.bind(this);
		this.makeObject = this.makeObject.bind(this);
	}

	toggleBtn() {
		this.setState(prevState=>({
			stateman:!prevState.stateman
		}));
	}

	onTextChange(e) {
		const name = e.target.name;
		const value = e.target.value;
		this.setState({
			[name]:value
		});
	}

	makeObject(fileName, currentDate, status, description) {
		var obj = new Object();
	    	obj.Name = fileName;
	    	obj.Date = currentDate;
	    	obj.Status = "Uploading";
	    	obj.Description = description;
	    	return obj;
	}

	uploadAction_Script(){
		const { filename, script } = this.state;
		const { scriptlist, addScript, addScriptEdit } = this.props;

		var data = new FormData();
		
		data.append("script", script);
		data.append("script_name", filename);

		var d = new Date();
    	var currentDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    	var newItemObj = this.makeObject(filename, currentDate, "Uploading", "");
    	var listLength = Object.keys(scriptlist).length;
    	addScript(newItemObj);

		fetch("http://localhost:8080/project/scriptupload", {
			method: "POST",
			credentials: "include",
			body: data
		}).then(function(res) {
			console.log(res);
			if( res.ok) {
				var newObj = Object.assign(newItemObj,{Status:"Done"});
				addScriptEdit(newItemObj,listLength);	
			} else {
				var newObj = Object.assign(newItemObj,{Status:"Fail"});
				addScriptEdit(newItemObj,listLength);
			}
		}, function(e) {
			var newObj = Object.assign(newItemObj,{Status:"Fail"});
			addScriptEdit(newItemObj,listLength);
		});
		this.setState(prevState=>({
			stateman:!prevState.stateman
		}));	
	}

	render() {

		const { scriptlist } = this.props;

		const ScriptTable = scriptlist.map((data,i) => {
			return (

			       <NewTableRow name={data.Name} date={data.Date} status={data.Status} description={data.Description} />
				);
		})

		//return (
			return (
			<Page>
			<div className={ cn({'disable':!this.state.stateman})}>
			<NewTable title={"Script List"} btnText="New Script" btnClick={this.toggleBtn.bind(this)}>
				<NewTableRow  header={true} name="Name" date="Date" status="Status" description="Description"/>
				{ScriptTable}



			</NewTable>
			</div>
			<div className={ cn({'disable':this.state.stateman})}>
					<button onClick={this.toggleBtn.bind(this)}>Back</button>
					<div>
						File Name : 
						<input type="text" name="filename" value={this.state.filename} onChange={this.onTextChange}/>
					</div>
	            		<div>
	            			<textarea name="script" value={this.state.script} onChange={this.onTextChange} placeholder="Script" rows="35" cols="130"></textarea>
	            		</div>	            		
	            		<button onClick={this.uploadAction_Script.bind(this)}>Submit</button>	            		
	            		
				</div>
			
			</Page>
			);
			/*
			<Page>
				<div className={ cn({'disable':!this.state.stateman})}>
					<TableBox>
						<button onClick={this.toggleBtn.bind(this)}>New Script</button>
						
						<div className={cn('head-text')}>Script</div>
						<TableRow header={true}>
							<TableItem>Name</TableItem>
							<TableItem>Date</TableItem>
							<TableItem>Status</TableItem>
							<TableItem>Description</TableItem>
						</TableRow>
						{ScriptTable}
					</TableBox>
				</div>

				<div className={ cn({'disable':this.state.stateman})}>
					<button onClick={this.toggleBtn.bind(this)}>Back</button>
					<div>
						File Name : 
						<input type="text" name="filename" value={this.state.filename} onChange={this.onTextChange}/>
					</div>
	            		<div>
	            			<textarea name="script" value={this.state.script} onChange={this.onTextChange} placeholder="Script" rows="35" cols="130"></textarea>
	            		</div>	            		
	            		<button onClick={this.uploadAction_Script.bind(this)}>Submit</button>	            		
	            		
				</div>
			</Page>*/
		//);
	}
}


export default ScriptPage;