import React,{Component} from 'react';

import {ManageUser} from './ManageUser';
import {AssignUser} from './AssignUser';
import {DownloadFile} from './DownloadFile';

export class Content extends Component {

    render(){
        return (
            <div className="content">
                <ManageUser/>
                <AssignUser/>
                <DownloadFile/>
            </div>
        )
    }
}