import React from 'react';
import '../css/Header.css';
import {Link} from 'react-router-dom';

class Header extends React.Component {

    render(){    	
        return (
       
        	<div className="hello">   
 				<div className="div_logo">
 				<Link to="/">
 				<img className="logoimage"src={require('../image/logo.jpg')} />
 				</Link>
 				<span className="category_name">
 					 KAMLAB
 				</span>
 				
 				</div>
 				
 				<ul>
				  <li><Link to="/joblist">JOB LIST</Link></li>
				  <li><Link to="/jobcreate">JOB CREATE</Link></li>
				  <li className="dropdown">
				    <a href="javascript:void(0)" className="dropbtn">UPLOAD</a>
				    <div className="dropdown-content">
				      <Link to="/Upload_File">File</Link>
				      <Link to="/Upload_Component">Component</Link>
				      <Link to="/Upload_Script">Script</Link>
				      <Link to="/Upload_ImageBuild">Image Build</Link>
				    </div>
				  </li>
				</ul>
	        </div>


	        		
			
        );
    }
}

export default Header;