import React from 'react';
import ReactDOM from 'react-dom';
import { createStore} from 'redux';
import { Provider } from 'react-redux';


import Root from './Root';
import * as actions from './actions';
import reducers from './reducers';

import registerServiceWorker from './registerServiceWorker';


function getData(urlList,store) {
	urlList.map((url,i) => {
		fetch(url, {
			method: 'GET'
		}).then((response) => response.json())
		.then((datajson) => {
			switch(i) {
				case 0 :
					store.dispatch(actions.addDataList(datajson));
					break;
				case 1:
					store.dispatch(actions.addExecList(datajson));
					break;
				case 2:
					store.dispatch(actions.addScriptList(datajson));
					break;
				case 3:
					store.dispatch(actions.addImageList(datajson));
					break;
				case 4:
					store.dispatch(actions.addJobList(datajson));
					break;
				default:
					console.log("initialize data err");
					break;

			}
		});
	});
}

function requestAllData(ip, store) {
	const type = [1, 2, 3, 4, 5];
	const urlList = type.map(num=>('http://'+ip+'/project/list/'+num));
	getData(urlList, store);
}



var x = 0;
const store = createStore(reducers);
if( x==0) {
	requestAllData('192.168.197.131:8080', store);
	
}
x=1;

//const store = createStore(reducers);
const render = () => ReactDOM.render(
	<Root store={store}/>, 
	document.getElementById('root')
);

//store.subscribe(render);
render();
	
registerServiceWorker();