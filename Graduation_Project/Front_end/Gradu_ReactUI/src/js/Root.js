import React, { Component } from 'react';
import JobListPage from './pages/JobListPage';
import UploadPage from './pages/UploadPage';
import Header from './components/Header';
import styles from 'stylesdir/index.scss';
import { BrowserRouter, Route } from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import App from './App';

const Root = ({store}) => {
	return(
		<Provider store={store}>
	      <BrowserRouter>
	        <Route path="/" component={App}/>
	      </BrowserRouter>
	    </Provider>
	);
}
export default Root;