import { Layout, Breadcrumb } from 'antd';
import { Link, useLocation } from 'react-router-dom';
import { HomeOutlined } from '@ant-design/icons';
import styles from './MainTemplate.module.css';

function MainTemplate({ children }: any) {
  const { Header, Content, Footer } = Layout;
  const { pathname } = useLocation();

  return (
    <Layout className={styles.layout}>
      <Header>
        <div className={styles.container}>
          <Link to="/">
            <div className={styles.logo} />
          </Link>
        </div>
      </Header>
      <Content className={styles.container}>
        <Breadcrumb style={{ margin: '24px 0' }}>
          <Breadcrumb.Item>
            <HomeOutlined />
          </Breadcrumb.Item>
          {pathname.split('/').map((path) => (
            <Breadcrumb.Item key={path}>{path}</Breadcrumb.Item>
          ))}
        </Breadcrumb>
        <div className={styles.siteLayoutContent}>{children}</div>
      </Content>
      <Footer style={{ textAlign: 'center' }}>BitTorrent - 2ID21A Zespół nr 8</Footer>
    </Layout>
  );
}

export default MainTemplate;
