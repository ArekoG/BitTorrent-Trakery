import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Table } from 'antd';
import { getData, fetchTackerFiles } from 'redux/filesSlice';

function UserFiles() {
  const dataSource = useSelector(getData);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTackerFiles());
  }, [dispatch]);

  const columns = [
    {
      title: 'id',
      key: 'id',
      dataIndex: 'id',
    },
    {
      title: 'Nazwa pliku',
      key: 'name',
      dataIndex: 'name',
    },
    {
      title: 'Rozmiar pliku',
      key: 'size',
      dataIndex: 'size',
    },
  ];

  return (
    <Table
      dataSource={dataSource}
      rowKey="id"
      columns={columns}
      tableLayout="fixed"
      pagination={false}
    />
  );
}

export default UserFiles;
