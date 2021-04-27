import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Space, Table, Tag } from 'antd';
import { FolderOutlined, PoweroffOutlined, TeamOutlined } from '@ant-design/icons';
import {
  getTrackers,
  fetchTrackers,
  activateTracker,
  deactivateTracker,
} from 'redux/trackersSlice';
import paths from 'routes/paths';

function Trackers() {
  const { isLoading, data: dataSource } = useSelector(getTrackers);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTrackers());
  }, [dispatch]);

  const columns = [
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

  return (
    <Table
      loading={isLoading}
      dataSource={dataSource}
      rowKey="id"
      columns={columns}
      pagination={false}
      tableLayout="fixed"
    />
  );
}

export default Trackers;
