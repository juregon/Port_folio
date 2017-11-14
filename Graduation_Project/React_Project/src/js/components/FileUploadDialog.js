import React from 'react';

import Input from './Input';
import styles from 'stylesdir/components/FileUploadDialog.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

class FileUploadDialog extends React.Component {
	constructor() {
		super();
		this.state = {
			filename:"",
			description: ""
		};
		this.handleChange = this.handleChange.bind(this);
		this.handleInput = this.handleInput.bind(this);
		this.cancelAction = this.cancelAction.bind(this);
		this.uploadAction = this.uploadAction.bind(this);
		this.makeObject = this.makeObject.bind(this);
		this.getProgressAction = this.getProgressAction.bind(this);
	}

	makeObject(fileName, currentDate, status, description) {
		var obj = new Object();
	    	obj.Name = fileName;
	    	obj.Date = currentDate;
	    	obj.Status = "Uploading(0%)";
	    	obj.Description = description;
	    	return obj;
	}

	handleChange(e) {
		this.setState({
			filename : e.target.files[0]
		});
	}

	cancelAction() {
		const { toggleUploadDialog } = this.props;
		this.setState({
			filename:"",
			description:""
		});
		toggleUploadDialog();
	}

	uploadAction() {		
		const { datalist, execlist, addData, addExec, addDataEdit, addExecEdit, pagename, toggleUploadDialog } = this.props;

		var data = new FormData();
		console.log(this.state.filename);
		var type;
		if(pagename == true)
			type = "Data";
		else
			type = "Exec";
		const fileData = this.state.filename;
		const desText = this.state.description;
		const fileName = this.state.filename.name;
		

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
		fetch("http://192.168.197.131:8080/project/fileupload?uploadId="+uploadId, {
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
		toggleUploadDialog();
		console.log(uploadId);
		var signal = setInterval(()=>this.getProgressAction(uploadId, type, newItemObj, listLength, listLength2), 1000);
		this.setState({
			[uploadId] : signal
		})

		
	}

	getProgressAction(uploadId, type, newItemObj, listLength, listLength2) {		
		const { addDataEdit, addExecEdit } = this.props;
		var progress;
		fetch("http://192.168.197.131:8080/project/progresscheck?uploadId="+uploadId, {
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

	handleInput(e) {
		this.setState({
			[e.target.name]:e.target.value
		});
	}


	render() {
		const { isDialogOn, toggleUploadDialog } = this.props;
		const { filename ,description } = this.state;

		return (
			<div className={ cn('box',{'disable':!isDialogOn}) }>
				<div className={ cn('title')}>
					File Upload
				</div>
				
				<div className={cn('fileBox')}>
					<input type="text" className={cn('fileName')} value={filename.name} name="filename"/>
					<label htmlFor="uploadBtn" className={cn('btn_file')} >찾아보기</label>
					<input type="file" id="uploadBtn" className={cn('uploadBtn')} onChange={this.handleChange}/>
				</div>
				<Input className={cn('description-input')} type="text" placeholder="description" value={description} name="description" onChangeInput={this.handleInput}/>
				<div className={cn('btn-box')}>
					<div className={cn('left')} onClick={this.cancelAction.bind(this)}>
						CANCEL
					</div>
					<div className={cn('right')} onClick={this.uploadAction.bind(this)}>
						UPLOAD
					</div>
				</div>
			</div>

			);
	}
}

export default FileUploadDialog;