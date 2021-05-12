import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Card, Space, Table, Tag } from 'antd';
import { getUsers, fetchUsers } from 'redux/usersSlice';

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
