import React from 'react';

import DialogBtn from './DialogBtn';
import Input from './Input';
import styles from 'stylesdir/components/ImageDialog.scss';

import classNames from 'classnames/bind';
import $ from 'jquery';
const cn = classNames.bind(styles);

const MenuItem = ({name, onClick}) => {
	return (
		<div className={cn('menu-item')} onClick={onClick}>
			{name}
		</div>
		);
}
const ConfigItem = ({header, name, description,onClick}) => {
	return (
		<div className={cn('item',{'table-header':header})}>
			<div className={cn('click-box')} onClick={()=>onClick(name)}>				
				<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M0 0h24v24H0z" fill="none"/>
				    <path d="M18 4H6c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 14H6V6h12v12z"/>
				</svg>
			</div>
			<div className={cn('item-name')}>
				{name}
			</div>
			<div className={cn('item-des')}>
				{description}
			</div>
		</div>
		);
}

class ImageDialog extends React.Component {
	constructor() {
		super();
		this.state = {
			os : "",
			component : "",
			data : "",
			exec : "",
			script : "",
			currentPageNum:0,
			jobname:"",
			description: ""
		};
		this.checkPage = this.checkPage.bind(this);
		this.setPage = this.setPage.bind(this);
		this.clickOs = this.clickOs.bind(this);
		this.clickComponent = this.clickComponent.bind(this);
		this.clickData = this.clickData.bind(this);
		this.clickExec = this.clickExec.bind(this);
		this.clickScript = this.clickScript.bind(this);
		this.imageBuild = this.imageBuild.bind(this);
		this.makeObject = this.makeObject.bind(this);
		this.onChangeInput = this.onChangeInput.bind(this);
		this.closeModal = this.closeModal.bind(this);
	}
	closeModal() {
		const { toggleDialog } = this.props;
		this.setState({
			jobname:"",
			description:""
		});
		toggleDialog();
	}

	onChangeInput(e) {
		this.setState({
			[e.target.name]:e.target.value
		});
	}

	checkPage(pageNum) {
		const { currentPageNum } = this.state;

		if(currentPageNum == pageNum) {
			return true;
		} else {
			return false;
		}
	}

	setPage(pageNum) {
		this.setState({currentPageNum: pageNum});
	}

	clickOs(name){
		alert(name);
		this.setState({
			os : name
		})
	}

	clickComponent(name){
		alert(name);
		this.setState({
			component : name
		})
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

	makeObject(fileName, currentDate, status, description) {
		var obj = new Object();
	    	obj.Name = fileName;
	    	obj.Date = currentDate;
	    	obj.Status = status;
	    	obj.Description = description;
	    	return obj;
	}

	imageBuild(){

		const { addImageEdit, imagelist, addImage, datalist, execlist, addData, addExec, addDataEdit, addExecEdit, pagename, toggleDialog } = this.props;

		var d = new Date();
    	var currentDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();

		var obj = new Object();
		var image = this.state.os;
		var component = this.state.component;
		var file_execute = this.state.exec;
		var file_data = this.state.data;
		var script = this.state.script;
		var image_name = this.state.jobname;
		var description = this.state.description

		var newItemObj = this.makeObject(image_name, currentDate, "Building", description);
    	var listLength = Object.keys(imagelist).length;
	    addImage(newItemObj);
		
		obj.image = image;
		obj.component = component;
		obj.file_execute = file_execute;
		obj.file_data = file_data;
		obj.script = script;
		obj.image_name = image_name;
		obj.description = description;
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
			url : "http://192.168.197.131:8080/project/imagebuild",
			type: "POST",
			dataType: "json",
			data: JSON.stringify(obj),
			contentType: 'application/json',
			success:function(data){
				var newObj = Object.assign(newItemObj,{Status:"Done"});
				addImageEdit(newItemObj,listLength);
			},
			error: function(data, status, er){
				var newObj = Object.assign(newItemObj,{Status:"Done"});
				addImageEdit(newItemObj,listLength);
			}
		})
		this.closeModal();
	}


	render() {
		const { isDialogOn, toggleDialog, datalist, execlist, scriptlist } = this.props;

		const DataTable = datalist.map((data,i)=>{
			return (
				<ConfigItem key={i} name={data.Name} description={data.Description} onClick={this.clickData}/>
			);
		});

		const ExecTable = execlist.map((data,i)=>{
		   return ( 	
				<ConfigItem key={i} name={data.Name} description={data.Description} onClick={this.clickExec}/>
		   );
		});

		const ScriptTable = scriptlist.map((data,i)=>{
		   return ( 	
				<ConfigItem key={i} name={data.Name} description={data.Description} onClick={this.clickScript}/>
		   );
		});


		return (
			<div className={cn('box',{'disable':!isDialogOn})}>
				<div className={cn('header')}>
					<div className={cn('header-title')}>NEW JOB CONFIGURATION</div>
					<Input className={cn('jobname-input')} placeholder="JOB NAME" type="text" name="jobname" value={this.state.jobname} onChangeInput={this.onChangeInput}/>
					<Input className={cn('des-input')} placeholder="DESCRIPTION" type="text" name="description" value={this.state.description} onChangeInput={this.onChangeInput}/>
				</div>
				
				<div className={cn('left-box')}>
					<MenuItem name="OS" onClick={()=>this.setPage(0)}/>
					<MenuItem name="COMPONENT" onClick={()=>this.setPage(1)}/>
					<MenuItem name="DATA" onClick={()=>this.setPage(2)}/>
					<MenuItem name="EXEC" onClick={()=>this.setPage(3)}/>
					<MenuItem name="SCRIPT" onClick={()=>this.setPage(4)}/>
				</div>
				<div className={cn('right-box')}>
					
					<ConfigItem header={true} name="NAME" description="DESCRIPTION" />

					<div className={cn({'disable':!this.checkPage(0)})}>
						<ConfigItem name="UBUNTU" description="ubuntu:14.04" onClick={this.clickOs}/>
						<ConfigItem name="CENTOS" description="Centos 7" onClick={this.clickOs}/>
					</div>

					<div className={cn({'disable':!this.checkPage(1)})}>
						<ConfigItem name="Java" description="JDK" onClick={this.clickComponent}/>
						<ConfigItem name="Python" description="Python" onClick={this.clickComponent}/>
					</div>

					<div className={cn({'disable':!this.checkPage(2)})}>
						{DataTable}
					</div>

					<div className={cn({'disable':!this.checkPage(3)})}>
						{ExecTable}
					</div>

					<div className={cn({'disable':!this.checkPage(4)})}>
						{ScriptTable}
					</div>

					
					

				</div>

				<div className={cn('bottom-box')}>
					<DialogBtn className={cn('cancel-btn')} btnClick={this.closeModal}>Cancel</DialogBtn>
					<DialogBtn className={cn('create-btn')} btnClick={this.imageBuild}>Create</DialogBtn>
				</div>
			</div>
			);
	}
}

export default ImageDialog;
