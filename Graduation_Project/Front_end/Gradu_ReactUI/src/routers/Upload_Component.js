import React from 'react';
import '../css/Body.css';
import $ from 'jquery';

class Upload_Component extends React.Component {
	dockertest() {
		var obj = new Object();
		var image = $('#image').val();
		var component = $('#component').val();
		var file_execute = $('#file_execute').val();
		var file_data = $('#file_data').val();
		var script = $('#script').val();
		var image_name = $('#image_name').val();
		
		obj.image = image;
		obj.component = component;
		obj.file_execute = file_execute;
		obj.file_data = file_data;
		obj.script = script;
		obj.image_name = image_name;

		$.ajax({
			url : "http://localhost:8080/project/dockertest",
			type: "POST",
			dataType: "json",
			data: JSON.stringify(obj),
			contentType: 'application/json',
			success:function(data){
				alert("success ajax..." + '/' + data.name + '/' + data.age);
			},
			error: function(data, status, er){
				alert("error: " + data + " status: " + status + " er: " + er);
			}
		})
			
	}

	render(){
		return(
			<div className="body_main">
	            <div className="body_head">
	            Upload_Component
	             </div>
	            <div className="body_head2">
	            <form id="dockertest">
                	<input type="button" value="dockerman" onClick={this.dockertest.bind(this)}></input>
                	<input type="text" id="image" placeholder="input image"/>
                	<input type="text" id="component" placeholder="input component"/>
                	<input type="text" id="file_execute" placeholder="input file_execute"/>
                	<input type="text" id="file_data" placeholder="input file_data"/>
                	<input type="text" id="script" placeholder="input script"/>
                	<input type="text" id="image_name" placeholder="input name"/>
             	</form>
             	</div>
             	<div>
                <table className="list_table" id="example-table-1">
                  <tr className="first_tr">
                    <th>JOB NAME</th>
                    <th>STATUS</th>
                    <th>DATE</th>
                    <th>DESCRIPTION</th>
                  </tr>
                  <tr className="second_tr">
                    <td id="test1" value="123">job1</td>
                    <td>running</td>
                    <td>2017/7/14</td>
                    <td>1</td>
                  </tr>
                  <tr className="second_tr">
                    <td>job2</td>
                    <td>running</td>
                    <td>2017/7/15</td>
                    <td>2</td>
                  </tr>
                  <tr className="second_tr">
                    <td>job3</td>
                    <td>pending</td>
                    <td>2017/7/18</td>
                    <td>3</td>
                  </tr>
                  <tr className="second_tr">
                    <td>job4</td>
                    <td>pending</td>
                    <td>2017/7/18</td>
                    <td>4</td>
                  </tr>
                 </table>
              </div>
	           

	            
	        </div>
		);
	}
};

export default Upload_Component;