import paths from './paths';
import Trackers from 'pages/Trackers';
import TrackerFiles from 'pages/TrackerFiles';
import TrackerUserList from 'pages/TrackerUserList';
import UserFiles from 'pages/UserFiles';
import { RouteComponentProps } from 'react-router-dom';

export interface Routes {
  key: string;
  path: string;
  component: React.ComponentType<RouteComponentProps<any>> | React.ComponentType<any>;
  routes?: Routes[];
}

const routes: Routes[] = [
  { key: 'trackers', path: paths.trackers, component: Trackers },
  { key: 'trackerFiles', path: paths.trackerFiles, component: TrackerFiles },
  { key: 'trackerUsers', path: paths.trackerUsers, component: TrackerUserList },
  { key: 'userFiles', path: paths.userFiles, component: UserFiles },
];

export default routes;
