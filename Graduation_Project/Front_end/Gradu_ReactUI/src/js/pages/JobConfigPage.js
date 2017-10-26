import React from 'react';

import $ from 'jquery';
import TableHeader from '../components/TableHeader';
import TableBox from '../components/TableBox';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';

import Page from './Page';
import ItemListBox from '../components/ItemListBox';
import ItemRow from '../components/ItemRow';

import styles from 'stylesdir/pages/JobConfigPage.scss';
import NewTable from '../components/NewTable';
import NewTableRow from '../components/NewTableRow';


import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

class JobConfigPage extends React.Component {
	constructor() {
		super();
		this.clickData = this.clickData.bind(this);
		this.clickExec = this.clickExec.bind(this);
		this.clickScript = this.clickScript.bind(this);
		this.build = this.build.bind(this);
		this.state = {
			os : "ubuntu:16.04",
			component : "Java",
			data : "",
			exec : "",
			script : "",
			stateman : true
		};
	}

	toggleBtn() {
		this.setState(prevState=>({
			stateman:!prevState.stateman
		}));
	}

	clickData(name){
		alert(name);
		this.setState({
			data : name
		})	
	}

	clickExec(name){
		alert(name);
		this.setState({
			exec : name
		})
	}

	clickScript(name){
		alert(name);
		this.setState({
			script : name
		})	
	}

	build(){
		//console.log(this.state.os);
		var obj = new Object();
		var image = this.state.os;
		var component = this.state.component;
		var file_execute = this.state.exec;
		var file_data = this.state.data;
		var script = this.state.script;
		var image_name = "testman";
		
		obj.image = image;
		obj.component = component;
		obj.file_execute = file_execute;
		obj.file_data = file_data;
		obj.script = script;
		obj.image_name = image_name;
		console.log(obj);
		/*fetch("http://localhost:8080/project/dockertest", {
			method: "POST",
			dataType: "json",
			credentials: "include",
			header: {
				"Accept" : "application/json"
			},
			body: JSON.stringfy(obj)
		}).then(function(res) {
			if( res.ok) {
				alert("success");		
			} 
			else {
				alert("fail")
			}
		}.bind(this), function(e) {
			alert("fail")
		}.bind(this));*/

		$.ajax({
			url : "http://localhost:8080/project/dockertest",
			type: "POST",
			dataType: "json",
			data: JSON.stringify(obj),
			contentType: 'application/json',
			success:function(data){
				alert("success ajax...");
			},
			error: function(data, status, er){
				alert("error: ");
			}
		})
	}
	onFileChange(e) {
		const targetName = e.target.name;
		const targetFile = e.target.files[0];

		this.setState({
			[targetName] : targetFile
		});	
	}

	render() {
		const { datalist, execlist, scriptlist } = this.props;

		const DataTable = datalist.map((data,i)=>{
			return (
				<ItemRow key={i} index={i} id={data.Name} clickCheckbox={this.clickData} name={data.Name} description={data.Description}/>
			);
		});

		const ExecTable = execlist.map((data,i)=>{
		   return ( 	
				<ItemRow key={i} index={i} id={data.Name} clickCheckbox={this.clickExec} name={data.Name} description={data.Description}/>
		   );
		});

		const ScriptTable = scriptlist.map((data,i)=>{
		   return ( 	
				<ItemRow key={i} index={i} id={data.Name} clickCheckbox={this.clickScript} name={data.Name} description={data.Description}/>
		   );
		});

		return (

			<Page>

				<div className={ cn({'disable':!this.state.stateman})}>	
					<NewTable title={"Image List"} btnText="Image Create" btnClick={this.toggleBtn.bind(this)}>
						<NewTableRow header={true} name="Name" date="Date" status="Status" description="Description"/>
					</NewTable>
				</div>
				<div className={cn('select-area')}>
					<div className={ cn({'disable':this.state.stateman})}>
					<NewTable title={"Image List"} btnText="Image List" btnClick={this.toggleBtn.bind(this)}>
						<ItemListBox>
							<div className={cn('title-box')}>OS</div>
							<ItemRow name="NAME" description="DESCRIPTION"/>
							<ItemRow name="UBUNTU" description="OS"/>
							<ItemRow name="CENTOS" description="OS"/>
						</ItemListBox>

						<ItemListBox>
							<div className={cn('title-box')}>Component</div>
							<ItemRow name="NAME" description="DESCRIPTION"/>
							<ItemRow name="Java" description="JDK"/>
						</ItemListBox>
						<ItemListBox>
							<div className={cn('title-box')}>Data</div>
							<ItemRow name="NAME" description="DESCRIPTION"/>
							{DataTable}
						</ItemListBox>

						<ItemListBox>
							<div className={cn('title-box')}>Exec</div>
							<ItemRow name="NAME" description="DESCRIPTION"/>
							{ExecTable}
						</ItemListBox>
						<ItemListBox>
							<div className={cn('title-box')}>Script</div>
							<ItemRow name="NAME" description="DESCRIPTION"/>
							{ScriptTable}
						</ItemListBox>
						<div className={cn('save-btn')} onClick={this.build}>
							Save
						</div>
						</NewTable>
					</div>

					
					
				</div>
			</Page>	


						

			);
	}
}

export default JobConfigPage;