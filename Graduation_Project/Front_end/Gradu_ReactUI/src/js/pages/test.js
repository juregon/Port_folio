import React, { Component } from 'react';
import $ from 'jquery';
import FlexBox from '../components/FlexBox';
import TableBox from '../components/TableBox';
import Page from './Page';
import TableTitle from '../components/TableTitle';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import TableHeader from '../components/TableHeader';
import PropTypes from 'prop-types';

class ScriptPage extends Component {
	componentDidMount(){
  		this.props.fetchPostsWithRedux()
  	}
	onData_load(){
		fetch("http://localhost:8080/project/filelist", {
			method : "GET",
		}).then(function (res){
			if(res.ok){
				res.json().then(function(data){	
					/*for(var i=0; i<data.Names.length; i++){
						console.log("Name : " + data.Names[i] + "  Date : " + data.Dates[i] + "  Status : " + data.Statuses[i] + "  Description : " + data.Descriptions[i]);
					}*/
					console.log(data.Names[0]);
				})
			}
			else{
				alert("res.status : " + res.status);
			}
		}, function(e){
			alert("Error submitting form!");
		});
	}

	constructor() {
		super();
		this.uploadAction = this.uploadAction.bind(this);
		this.getProgressAction = this.getProgressAction.bind(this);
		this.state = {};
	}


	uploadAction(type) {
		
		var data = new FormData();
		var fileData = document.querySelector('input[type="file"]').files[0];
		var descriptionText = document.getElementById(type).value;

		data.append("fileData", fileData);
		data.append("abc", descriptionText);

		var d = new Date();
    		var uploadId = d.getTime();

    		var intervalNum = setInterval(()=>this.getProgressAction(uploadId), 2000);
		this.setState({[uploadId] : intervalNum});

		fetch("http://192.168.43.23:8080/project/fileupload?uploadId="+uploadId, {
			method: "POST",
			credentials: "include",
			header: {
				"Accept" : "application/json"
			},
			body: data
		}).then(function(res) {
			console.log(res);
			if( res.ok) {
				clearInterval(this.state[uploadId]);
				alert("finish");
			} else {
				console.log("file upload error");
			}
		}, function(e) {
			alert("error");
		});				

	}

	getProgressAction(uploadId) {		

		var progress;

		fetch("http://192.168.43.23:8080/project/progresscheck?uploadId="+uploadId, {
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


				console.log(br / clstr)
			})});
	
	}	


	render() {
		const Data = this.props.posts;
		const Data2 = {Data};
		console.log(Data);
		console.log(Data2);
		const Dataman = {this.props.posts.map((post) => {
			return(
				<TableItem>{post.Names}
			)
		})}
		return (

			<Page>
				<ul>
				{
          this.props.posts.map((post) =>{
          	return(
            	<li>{post.title}</li>
            )
          })
        }
        </ul>
				<TableBox>
					<button onClick={()=>this.uploadAction("scriptId")}>SCRIPT</button>
					<button onClick={()=>this.onData_load()}>SCRIPT</button>
					<TableHeader title="script" id="scriptId" />
					<TableRow header={true}>
						<TableItem>Data.Names[0]</TableItem>
						
					</TableRow>
					

				</TableBox>
			</Page>
			);
	}
}


export default ScriptPage;