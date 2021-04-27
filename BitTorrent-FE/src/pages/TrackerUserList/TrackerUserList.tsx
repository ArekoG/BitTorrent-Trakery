import { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Space, Table, Tag } from 'antd';
import { FolderOutlined, PoweroffOutlined } from '@ant-design/icons';
import { getUsers, fetchUsers, activateUser, deactivateUser } from 'redux/usersSlice';
import paths from 'routes/paths';

type Params = {
  id: string;
};

function TrackerUserList() {
  const { id: trackerId } = useParams<Params>();
  const { isLoading, data: dataSource } = useSelector(getUsers);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchUsers());
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
      title: 'Liczba plików',
      key: 'files',
      dataIndex: 'files',
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

export default TrackerUserList;
