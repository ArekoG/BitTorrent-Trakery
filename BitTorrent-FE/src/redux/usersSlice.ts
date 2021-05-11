import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import fakeAPI from 'fakeAPI';
import { RootState } from './store';

export interface User {
  id: string;
  status: boolean;
  files: number;
  trackers: number;
}

export interface UserState {
  data: User[];
  isLoading: boolean;
}

const initialState: UserState = {
  data: [],
  isLoading: false,
};

export const fetchUsers = createAsyncThunk('tracker/fetchUsers', async () => {
  const { data } = await fakeAPI.fetchUsers();

  return data;
});

export const activateUser = createAsyncThunk('users/activateUser', async (trackerId: string) => {
  await fakeAPI.toggleStatusById(trackerId);
  return trackerId;
});

export const deactivateUser = createAsyncThunk(
  'users/deactivateUser',
  async (trackerId: string) => {
    await fakeAPI.toggleStatusById(trackerId);
    return trackerId;
  },
);

export const usersSlice = createSlice({
  name: 'users',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUsers.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = action.payload;
      })
      .addCase(activateUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.id === action.payload ? { ...value, status: true } : value,
        );
      })
      .addCase(deactivateUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.id === action.payload ? { ...value, status: false } : value,
        );
      });
  },
});

export const getUsers = (state: RootState) => state.users;

export default usersSlice.reducer;
