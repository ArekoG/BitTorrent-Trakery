import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, Space, Table, Tag } from 'antd';
import { PoweroffOutlined } from '@ant-design/icons';
import { getUsers, fetchUsers, activateUser, deactivateUser } from 'redux/usersSlice';

type Params = {
  id: string;
};

function TrackerUserList() {
  const { id: trackerId } = useParams<Params>();
  const { isLoading, data: dataSource } = useSelector(getUsers);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchUsers(trackerId));
  }, [dispatch, trackerId]);

  const columns = [
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
      title: 'Status w trackerze',
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
      width: '35%',
      render: (text: string, record: any) => {
        const status = record.userStatus === 'enable';
        return (
          <Space size={[12, 12]} wrap>
            {status ? (
              <Button
                danger
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(deactivateUser(record.userId))}
              >
                dezaktywuj
              </Button>
            ) : (
              <Button
                className="btn-green"
                icon={<PoweroffOutlined />}
                onClick={() => dispatch(activateUser(record.userId))}
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
      <Card title="Lista użytkowników trackera">
        <Table
          loading={isLoading}
          dataSource={dataSource}
          rowKey="userId"
          columns={columns}
          pagination={false}
          tableLayout="fixed"
        />
      </Card>
    </Space>
  );
}

export default TrackerUserList;
