import paths from './paths';
import Trackers from 'pages/Trackers';
import TrackerUserList from 'pages/TrackerUserList';
import { RouteComponentProps } from 'react-router-dom';

export interface Routes {
  key: string;
  path: string;
  component: React.ComponentType<RouteComponentProps<any>> | React.ComponentType<any>;
  routes?: Routes[];
}

const routes: Routes[] = [
  { key: 'trackers', path: paths.trackers, component: Trackers },
  { key: 'trackerUsers', path: paths.trackerUsers, component: TrackerUserList },
];

export default routes;
