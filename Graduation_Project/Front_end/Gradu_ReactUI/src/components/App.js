import React from 'react';
import Header from './Header';
import Body from './Body';
import {BrowserRouter as Router, Route} from 'react-router-dom';

import JobList from '../routers/JobList';
import JobCreate from '../routers/JobCreate';
import Upload_File from '../routers/Upload_File';
import Upload_Component from '../routers/Upload_Component';
import Upload_Script from '../routers/Upload_Script';
import Upload_ImageBuild from '../routers/Upload_ImageBuild';


const App = () =>{
    return(
        <Router>
            <div>
                <Header/>
                <Route exact path="/" component={JobList}/>
                <Route path="/JobList" component={JobList}/>
                <Route path="/JobCreate" component={JobCreate}/>
                <Route path="/Upload_File" component={Upload_File}/>
                <Route path="/Upload_Component" component={Upload_Component}/>
                <Route path="/Upload_Script" component={Upload_Script}/>
                <Route path="/Upload_ImageBuild" component={Upload_ImageBuild}/>
            </div>
        </Router>
        );
};

export default App;