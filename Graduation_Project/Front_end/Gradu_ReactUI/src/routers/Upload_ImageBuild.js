import React from 'react';
import '../css/Body.css';
import $ from "jquery";

class Upload_ImageBuild extends React.Component {
	uploadAction() {  
		$.ajax({	
			url : "http://localhost:8080/project/testAction",
			dataType : "json",
			type : "POST",
			data : $('#boyru').serializeArray(),
			success:function(data){
				console.log(data.obj);
				console.log(data.obj[0]);
				alert("perfect!" + data.obj);
				alert("perfect!" + data.obj[0]);
			},
			error:function(request, status, error){
				alert("oops!" + request + " ////// " + status +" /////" + error);
			}
		})  
	}
	mongotest() {
	
			var name = $('#inname').val();
			var age = $('#inage').val();
			var obj = new Object();
			alert(name + "aaa" + age);

			obj.name= name;
			obj.age= age;

			$.ajax({
				url : "http://localhost:8080/project/mongoinputajax",
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
              Upload_ImageBuild
              </div>
              <div className="body_head2">
				     	      
              	<form id="boyru">
	                 <input type="text" id="test" name="test" value="gg"/>
	                 <input type="text" id="test2" name="test2" value="gg2"/>
	                 <input type="button" value="upload" onClick={this.uploadAction.bind(this)}></input>
                </form>
                <form id="mongotest">
                	<input type="button" value="mongo" onClick={this.mongotest.bind(this)}></input>
                	<input type="text" id="inname" placeholder="input name"/>
                	<input type="text" id="inage" placeholder="input age"/>
             	</form>


              </div>
        </div>
  	);
  }
};

export default Upload_File;