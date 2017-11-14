import React, { Component } from 'react';
import { Route } from 'react-router-dom';

import JobListPage from './pages/JobListPage';
import UploadPage from './containers/UploadContainer';
import ScriptPage from './containers/ScriptContainer';
import JobConfigPage from './containers/JobConfigContainer';

import Header from './components/Header';

import styles from 'stylesdir/index.scss';

const App = () => {	
	return(
		<div>
			<Header />
			<Route exact path="/" component={JobListPage}/>
			<Route path="/upload" component={UploadPage}/>
			<Route path="/script" component={ScriptPage}/>
			<Route path="/config" component={JobConfigPage}/>
		</div>
		);	
}

export default App;