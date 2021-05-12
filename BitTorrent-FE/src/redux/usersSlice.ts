import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import fakeAPI from 'fakeAPI';
import api from 'api';
import { RootState } from './store';

export interface User {
  userId: string;
  userLogin: string;
  userStatus: 'enable' | 'disable';
}

export interface UserState {
  data: User[];
  isLoading: boolean;
}

const initialState: UserState = {
  data: [],
  isLoading: false,
};

export const fetchAllUsers = createAsyncThunk('tracker/fetchAllUsers', async () => {
  const result = await api.get('/users');
  // const result = await fakeAPI.fetchAllUsers();

  return result.data;
});

export const fetchUsers = createAsyncThunk('tracker/fetchUsers', async (trackerId: string) => {
  const result = await api.get(`/trackers/${trackerId}/users`);
  // const result = await fakeAPI.fetchUsers();

  return result.data;
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
      .addCase(fetchAllUsers.pending, (state) => {
        state.isLoading = true;
        state.data = [];
      })
      .addCase(fetchAllUsers.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(fetchAllUsers.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = action.payload;
      })
      .addCase(fetchUsers.pending, (state) => {
        state.isLoading = true;
        state.data = [];
      })
      .addCase(fetchUsers.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = action.payload;
      })
      .addCase(activateUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateUser.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(activateUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.userId === action.payload ? { ...value, userStatus: 'enable' } : value,
        );
      })
      .addCase(deactivateUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateUser.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(deactivateUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.userId === action.payload ? { ...value, userStatus: 'disable' } : value,
        );
      });
  },
});

export const getUsers = (state: RootState) => state.users;

export default usersSlice.reducer;
