import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import fakeAPI from 'fakeAPI';
import { RootState } from './store';

interface User {
  id: string;
}

export interface File {
  id: string;
  name: string;
  size: string;
  users: User[];
}

export interface FileState {
  data: File[];
}

const initialState: FileState = {
  data: [],
};

export const fetchTackerFiles = createAsyncThunk('tracker/fetchTackerFiles', async () => {
  const { data } = await fakeAPI.fetchFiles();

  return data;
});

export const fetchUserFiles = createAsyncThunk('tracker/fetchUserFiles', async () => {
  const { data } = await fakeAPI.fetchFiles();

  return data;
});

export const filesSlice = createSlice({
  name: 'files',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTackerFiles.fulfilled, (state, action) => {
        state.data = action.payload;
      })
      .addCase(fetchUserFiles.fulfilled, (state, action) => {
        state.data = action.payload;
      });
  },
});

export const getData = (state: RootState) => state.files.data;

export default filesSlice.reducer;
