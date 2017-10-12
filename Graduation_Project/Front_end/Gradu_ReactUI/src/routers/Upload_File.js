import React from 'react';
import '../css/Body.css';
import $ from "jquery";

class Upload_File extends React.Component {
  uploadAction() {
    var data = new FormData();  
    var imagedata = document.querySelector('input[type="file"]').files[0];
    var text = document.querySelector('select[name="job"]').value;
    var uploadId = document.getElementById("uploadId").value.split('\\').pop();
    alert("filename : " + uploadId);
    data.append("data", imagedata);
    data.append("abc",text);

    fetch("http://localhost:8080/project/fileupload" + "?uploadId=" + uploadId, {
      method: "POST",
      credentials: 'include',
      body: data
    }).then(function (res) {
      if (res.ok) {
        res.json().then(function(data) {
          alert("Perfect! " + data.Names[0] + data.Names[1] + data.Types[0] + data.Types[1]);
        })
      } else if (res.status == 401) {
        alert("Oops! ");
      }
    }, function (e) {
      alert("Error submitting form!");
    });
    setInterval(function(){
    fetch("http://localhost:8080/project/progresscheck" + "?uploadId=" + uploadId, {
      method: "GET",
      credentials: 'include'    
    }).then(function (res) {
      if (res.ok) {
        res.json().then(function(data) {
          alert("Perfect! " + "bytesRead : " + data.bytesRead + "contentLength : " + data.contentLength);
        })
      } else if (res.status == 401) {
        alert("Oops! ");
      }
    }, function (e) {
      alert("Error submitting form!");
    });
  }, 2000);

  }

  render(){
    return(
      <div className="body_main">
              <div className="body_head">
              Upload_File
              </div>
              <div className="body_head2">
        
                <form encType="multipart/form-data" action="">
                  <input type="file" id="uploadId" name="uploadId" defaultValue="uploadId"/>
                  
                  <select name="job">
                    <option value="File">File</option>
                    <option value="Data" selected="Data">Data</option>
                  </select>

                  <input type="button" value="upload" onClick={this.uploadAction.bind(this)}></input>
                </form>
              
               
              </div>

              <div>
                <table className="list_table">
                  <tr className="first_tr">
                    <th>JOB NAME</th>
                    <th>STATUS</th>
                    <th>DATE</th>
                    <th>DESCRIPTION</th>
                  </tr>
                  <tr className="second_tr">
                    <td>job1</td>
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

export default Upload_File;