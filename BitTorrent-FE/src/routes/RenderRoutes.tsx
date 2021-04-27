import { Redirect, Route, Switch } from 'react-router-dom';
import { Result } from 'antd';
import paths from './paths';
import RouteWithSubRoutes from './RouteWithSubRoutes';
import { Routes } from './routes';

function RenderRoutes({ routes }: any) {
  return (
    <Switch>
      <Redirect exact from={paths.home} to={paths.trackers} />
      {routes &&
        routes.map((route: Routes) => {
          return <RouteWithSubRoutes {...route} />;
        })}
      <Route
        component={() => (
          <Result
            status="404"
            title="404"
            subTitle="Przepraszamy, odwiedzona strona nie istnieje."
          />
        )}
      />
    </Switch>
  );
}

export default RenderRoutes;
