import { Button, Progress, Space, Table } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';

function FileList({ dataSource }: any) {
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
    {
      title: 'Akcje',
      key: 'action',
      render: (text: string, record: any) => {
        return (
          <Space size={[12, 12]} wrap>
            <Button icon={<DownloadOutlined />}>Pobierz</Button>
          </Space>
        );
      },
    },
  ];

  return (
    <Table
      dataSource={dataSource}
      rowKey="id"
      columns={columns}
      tableLayout="fixed"
      pagination={false}
      expandable={{
        expandedRowRender: (record) => (
          <>
            {record.users?.map((user: any) => {
              return (
                <div key={user.id} style={{ marginBottom: '12px' }}>
                  ID: {user.id}
                  <Progress percent={30} />
                </div>
              );
            })}
          </>
        ),
        rowExpandable: (record) => record.users !== undefined && record.users.length > 0,
      }}
    />
  );
}

export default FileList;
