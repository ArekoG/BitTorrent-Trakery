import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, Space, Table, Tag } from 'antd';
import { PoweroffOutlined, TeamOutlined } from '@ant-design/icons';
import {
  getTrackers,
  fetchTrackers,
  activateTracker,
  deactivateTracker,
} from 'redux/trackersSlice';
import {
  getUsers,
  fetchAllUsers,
  activateUserGlobal,
  deactivateUserGlobal,
} from 'redux/usersSlice';
import paths from 'routes/paths';

function Trackers() {
  const { isLoading: isLoadingTrackers, data: dataTrackers } = useSelector(getTrackers);
  const { isLoading: isLoadingUsers, data: dataUsers } = useSelector(getUsers);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTrackers());
    dispatch(fetchAllUsers());
  }, [dispatch]);

  const columnsTackers = [
    {
      title: 'id',
      key: 'trackerId',
      dataIndex: 'trackerId',
    },
    {
      title: 'Nazwa',
      key: 'trackerName',
      dataIndex: 'trackerName',
    },
    {
      title: 'Status',
      key: 'trackerStatus',
      dataIndex: 'trackerStatus',
      render: (trackerStatus: string) => {
        const status = trackerStatus === 'enable';
        const color = status ? 'green' : 'red';
        return (
          <Tag color={color} key={status.toString()}>
            {status ? 'aktywny' : 'nieaktywny'}
          </Tag>
        );
      },
    },
    {
      title: 'Liczba użytkowników',
      key: 'numberOfUsers',
      dataIndex: 'numberOfUsers',
    },
    {
      title: 'Akcje',
      key: 'action',
      width: '35%',
      render: (text: string, record: any) => {
        const status = record.trackerStatus === 'enable';
        return (
          <Space size={[12, 12]} wrap>
            <Link to={paths.trackerUsers.replace(':id', record.trackerId)}>
              <Button icon={<TeamOutlined />}>użytkownicy</Button>
            </Link>
            {status ? (
              <Button
                danger
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(deactivateTracker(record.trackerId))}
              >
                dezaktywuj
              </Button>
            ) : (
              <Button
                className="btn-green"
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(activateTracker(record.trackerId))}
              >
                aktywuj
              </Button>
            )}
          </Space>
        );
      },
    },
  ];

  const columnsUsers = [
    {
      title: 'id',
      key: 'userId',
      dataIndex: 'userId',
    },
    {
      title: 'login',
      key: 'userLogin',
      dataIndex: 'userLogin',
    },
    {
      title: 'Status',
      key: 'userStatus',
      dataIndex: 'userStatus',
      render: (userStatus: string) => {
        const status = userStatus === 'enable';
        const color = status ? 'green' : 'red';
        return (
          <Tag color={color} key={status.toString()}>
            {status ? 'aktywny' : 'nieaktywny'}
          </Tag>
        );
      },
    },
    {
      title: 'Akcje',
      key: 'action',
      render: (text: string, record: any) => {
        const status = record.userStatus === 'enable';
        return (
          <Space size={[12, 12]} wrap>
            {status ? (
              <Button
                danger
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(deactivateUserGlobal(record.userId))}
              >
                dezaktywuj
              </Button>
            ) : (
              <Button
                className="btn-green"
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(activateUserGlobal(record.userId))}
              >
                aktywuj
              </Button>
            )}
          </Space>
        );
      },
    },
  ];

  return (
    <Space direction="vertical" size="large">
      <Card title="Lista trackerów">
        <Table
          loading={isLoadingTrackers}
          dataSource={dataTrackers}
          rowKey="trackerId"
          columns={columnsTackers}
          pagination={false}
          tableLayout="fixed"
        />
      </Card>
      <Card title="Lista użytkowników">
        <Table
          loading={isLoadingUsers}
          dataSource={dataUsers}
          rowKey="trackerId"
          columns={columnsUsers}
          pagination={false}
          tableLayout="fixed"
        />
      </Card>
    </Space>
  );
}

export default Trackers;
