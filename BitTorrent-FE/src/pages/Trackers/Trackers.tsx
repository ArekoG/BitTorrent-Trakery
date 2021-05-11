import { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Space, Table, Tag } from 'antd';
import { FolderOutlined, PoweroffOutlined, TeamOutlined } from '@ant-design/icons';
import {
  getTrackers,
  fetchTrackers,
  activateTracker,
  deactivateTracker,
} from 'redux/trackersSlice';
import { getUsers, fetchUsers, activateUser, deactivateUser } from 'redux/usersSlice';
import paths from 'routes/paths';

type Params = {
  id: string;
};

function Trackers() {
  const { id: trackerId } = useParams<Params>();
  const { isLoading, data: dataSource } = useSelector(getTrackers);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTrackers());
  }, [dispatch]);

  const columnsTracker = [
    {
      title: 'id',
      key: 'id',
      dataIndex: 'id',
    },
    {
      title: 'Status',
      key: 'status',
      dataIndex: 'status',
      render: (status: boolean) => {
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
      key: 'users',
      dataIndex: 'users',
    },
    {
      title: 'Akcje',
      key: 'action',
      width: '35%',
      render: (text: string, record: any) => {
        return (
          <Space size={[12, 12]} wrap>
            <Link to={paths.trackerFiles.replace(':id', record.id)}>
              <Button icon={<FolderOutlined />}>pliki</Button>
            </Link>
            <Link to={paths.trackerUsers.replace(':id', record.id)}>
              <Button icon={<TeamOutlined />}>użytkownicy</Button>
            </Link>
            {record.status ? (
              <Button
                danger
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(deactivateTracker(record.id))}
              >
                dezaktywuj
              </Button>
            ) : (
              <Button
                className="btn-green"
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(activateTracker(record.id))}
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
      key: 'id',
      dataIndex: 'id',
    },
    {
      title: 'Status',
      key: 'status',
      dataIndex: 'status',
      render: (status: boolean) => {
        const color = status ? 'green' : 'red';
        return (
          <Tag color={color} key={status.toString()}>
            {status ? 'aktywny' : 'nieaktywny'}
          </Tag>
        );
      },
    },
    {
      title: 'Liczba plików',
      key: 'files',
      dataIndex: 'files',
    },
    {
      title: 'ID używanego trackera',
      key: 'trackers',
      dataIndex: 'trackers',
    },
    {
      title: 'Akcje',
      key: 'action',
      render: (text: string, record: any) => {
        return (
          <Space size={[12, 12]} wrap>
            <Link
              to={paths.userFiles.replace(':trackerId', trackerId).replace(':userId', record.id)}
            >
              <Button icon={<FolderOutlined />}>pliki</Button>
            </Link>
            {record.status ? (
              <Button
                danger
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(deactivateUser(record.id))}
              >
                dezaktywuj
              </Button>
            ) : (
              <Button
                className="btn-green"
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(activateUser(record.id))}
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
    <div>
      <h1>Lista trackerów</h1>
    <Table
      loading={isLoading}
      dataSource={dataSource}
      rowKey="id"
      columns={columnsTracker}
      pagination={false}
      tableLayout="fixed"
    />
    <h1 style={{marginTop: '5%'}}>Lista wszystkich użytkowników</h1>
    <Table
      loading={isLoading}
      dataSource={dataSource}
      rowKey="id"
      columns={columnsUsers}
      pagination={false}
      tableLayout="fixed"
    />
    </div>
  );
}

export default Trackers;
