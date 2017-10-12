import React from 'react';
import '../css/Body.css';

class Body extends React.Component {

    render(){
        return (
        	<div className="body_main">
            <div className="body_head">
            Job List
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
}

export default Body;