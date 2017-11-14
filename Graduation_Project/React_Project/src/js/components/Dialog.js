import React from 'react';

import ImageRow from './ImageRow';
import Input from './Input';
import NewJobInput from './NewJobInput';
import DialogBtn from './DialogBtn';

import * as actions from '../actions';
import { connect } from 'react-redux';

import styles from 'stylesdir/components/Dialog.scss';
import classNames from 'classnames/bind';

const cn = classNames.bind(styles);



class Dialog extends React.Component {
	constructor() {
		super();
		this.clickCreateBtn = this.clickCreateBtn.bind(this);
		this.makeObject = this.makeObject.bind(this);
	}

	makeObject(fileName, currentDate, status, description) {
		var obj = new Object();
	    	obj.Name = fileName;
	    	obj.Date = currentDate;
	    	obj.Status = status;
	    	obj.Description = description;
	    	return obj;
	}

	clickCreateBtn() {
		const { btnClick, jobData, addJob, addJobEdit, joblist } = this.props;
		var d = new Date();
    	var currentDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    	var newItemObj = this.makeObject(jobData.jobname, currentDate, "Uploading", jobData.description);
    	var listLength = Object.keys(joblist).length;
    	jobData.currentDate = currentDate;
    	addJob(newItemObj);
    	btnClick();
		fetch("http://192.168.197.131:8080/project/jobcreate", {
			method: "POST",
			dataType: "json",
			credentials: "include",
			headers: {
				"Accept" : "application/json",
				"Content-Type" : 'application/json'
			},
			body: JSON.stringify(jobData)
		}).then(function(res) {
			if( res.ok) {
				var newObj = Object.assign(newItemObj,{Status:"Done"});
				addJobEdit(newItemObj,listLength);	
			} else {
				var newObj = Object.assign(newItemObj,{Status:"Fail"});
				addJobEdit(newItemObj,listLength);
			}
		}.bind(this), function(e) {
			var newObj = Object.assign(newItemObj,{Status:"Fail"});
			addJobEdit(newItemObj,listLength);
		}.bind(this));
	}

	render() {

		const { isDialogOn, btnClick, jobData, onChangeInput, imagelist } = this.props;

		const ImageList = imagelist.map((data,i) => {
			return (
				   <ImageRow key={i} name={data.Name} className={cn('image-item')} onChangeInput={onChangeInput}/>
				);
		})

		return (
			<div className={cn('box',{'disable':!isDialogOn})}>
				<div className={cn('left-box')}>
					<div className={cn('left-row-box')}>
						<ImageRow name="Image List" className={cn('image-header')}/>
						{ImageList}
						
					</div>
				</div>
				<div className={cn('right-box')}>
					<div className={cn('title')}>
						New Job
					</div>

					<NewJobInput title="JOB NAME" inputclass={cn('long-input')} name="jobname" value={jobData.jobname} onChangeInput={onChangeInput}/>
					<NewJobInput title="DESCRIPTION" inputclass={cn('long-input')} name="description" value={jobData.description} onChangeInput={onChangeInput}/>
					<NewJobInput title="CPUS" inputclass={cn('short-input')} boxclass={cn('float')} placeholder="0.5" name="cpus" value={jobData.cpus} onChangeInput={onChangeInput}/>
					<NewJobInput title="MEM(MIB)" inputclass={cn('short-input')} boxclass={cn('float')} placeholder="128" name="mem" value={jobData.mem} onChangeInput={onChangeInput}/>
					<NewJobInput title="DISK(MIB)" inputclass={cn('short-input')} boxclass={cn('float')} placeholder="0" name="disk" value={jobData.disk} onChangeInput={onChangeInput}/>
					
					<div className={cn('image-title')}>
						Selected Image
					</div>
					<div className={cn('image-show')}>
						{jobData.imagename}
					</div>


				</div>
				<div className={cn('bottom-box')}>
					<DialogBtn className={cn('cancel-btn')} btnClick={btnClick}>Cancel</DialogBtn>
					<DialogBtn className={cn('create-btn')} btnClick={this.clickCreateBtn}>Create</DialogBtn>
				</div>
			</div>
			);
	}
}
//imagelist: state.imageList.imageList
//export default Dialog;

export default connect(
	(state) => ({
		imagelist: state.imageList.imageList,
		joblist: state.jobList.jobList
	}),
	(dispatch) => ({
		addJob : (data) => dispatch(actions.addJob(data)),
		addJobEdit : (data, index) => dispatch(actions.addJobEdit(data, index)),
		onChangeInput: (e) => dispatch(actions.changeinput(e.target))
	})
)(Dialog);