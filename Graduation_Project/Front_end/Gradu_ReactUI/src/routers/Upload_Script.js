import React from 'react';
import '../css/Body.css';

class Upload_Script extends React.Component{
	uploadAction_Script(){
		var script_title = document.querySelector('select[name="script_name"]').value;

		data.append("title", script_title);

	}

	render(){
		return(
			<div className="body_main">
	            <div className="body_head">
	            Upload_Script
	            </div>
	            <br/><br/>
	            <div>
	            	<form action="test6/scriptUpload" method="post">
	            		<center>File Name : <input type="text" name="script_name"/></center><br/>
	            		<textarea name="script" rows="35" cols="130">This is a textarea.</textarea>
	            		<input type="submit" onClick={this.uploadAction_Script.bind(this)}/>
	            	</form>
	            </div>
	            
	        </div>
		);
	}
};

export default Upload_Script;