import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getData, fetchTackerFiles } from 'redux/filesSlice';
import FileList from 'components/FileList';

function UserFiles() {
  const dataSource = useSelector(getData);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTackerFiles());
  }, [dispatch]);

  return <FileList dataSource={dataSource} />;
}

export default UserFiles;
