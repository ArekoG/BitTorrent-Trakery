import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import api from 'api';
// import fakeAPI from 'fakeAPI';
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

export const activateUserGlobal = createAsyncThunk(
  'users/activateUserGlobal',
  async (userId: string) => {
    await api.post(`/users/${userId}/enabled`);
    // await fakeAPI.toggleStatusById(userId);
    return userId;
  },
);

export const deactivateUserGlobal = createAsyncThunk(
  'users/deactivateUserGlobal',
  async (userId: string) => {
    await api.post(`/users/${userId}/disabled`);
    // await fakeAPI.toggleStatusById(userId);
    return userId;
  },
);

export const activateUserInTracker = createAsyncThunk(
  'users/activateUserInTracker',
  async ({ trackerId, userId }: { trackerId: string; userId: string }) => {
    await api.post(`/trackers/${trackerId}/users/${userId}/enabled`);
    // await fakeAPI.toggleStatusById(userId);
    return userId;
  },
);

export const deactivateUserInTracker = createAsyncThunk(
  'users/deactivateUserInTracker',
  async ({ trackerId, userId }: { trackerId: string; userId: string }) => {
    await api.post(`/trackers/${trackerId}/users/${userId}/disabled`);
    // await fakeAPI.toggleStatusById(userId);
    return userId;
  },
);

function activateUser(state: any, action: any) {
  state.isLoading = false;
  state.data = state.data.map((value: any) =>
    value.userId === action.payload ? { ...value, userStatus: 'enable' } : value,
  );
}

function deactivateUser(state: any, action: any) {
  state.isLoading = false;
  state.data = state.data.map((value: any) =>
    value.userId === action.payload ? { ...value, userStatus: 'disable' } : value,
  );
}

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
      .addCase(activateUserGlobal.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateUserGlobal.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(activateUserGlobal.fulfilled, (state, action) => {
        activateUser(state, action);
      })
      .addCase(deactivateUserGlobal.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateUserGlobal.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(deactivateUserGlobal.fulfilled, (state, action) => {
        deactivateUser(state, action);
      })
      .addCase(activateUserInTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateUserInTracker.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(activateUserInTracker.fulfilled, (state, action) => {
        activateUser(state, action);
      })
      .addCase(deactivateUserInTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateUserInTracker.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(deactivateUserInTracker.fulfilled, (state, action) => {
        deactivateUser(state, action);
      });
  },
});

export const getUsers = (state: RootState) => state.users;

export default usersSlice.reducer;
