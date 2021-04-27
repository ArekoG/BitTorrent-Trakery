import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import fakeAPI from 'fakeAPI';
import { RootState } from './store';

export interface Tracker {
  id: string;
  status: boolean;
  users: number;
}

export interface TrackerState {
  data: Tracker[];
  isLoading: boolean;
}

const initialState: TrackerState = {
  data: [],
  isLoading: false,
};

export const fetchTrackers = createAsyncThunk('trackers/fetchTracers', async () => {
  const { data } = await fakeAPI.fetchTracers();

  return data;
});

export const activateTracker = createAsyncThunk(
  'trackers/activateTracker',
  async (trackerId: string) => {
    await fakeAPI.toggleStatusById(trackerId);
    return trackerId;
  },
);

export const deactivateTracker = createAsyncThunk(
  'trackers/deactivateTracker',
  async (trackerId: string) => {
    await fakeAPI.toggleStatusById(trackerId);
    return trackerId;
  },
);

export const trackersSlice = createSlice({
  name: 'trackers',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTrackers.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(fetchTrackers.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = action.payload;
      })
      .addCase(activateTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateTracker.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.id === action.payload ? { ...value, status: true } : value,
        );
      })
      .addCase(deactivateTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateTracker.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.id === action.payload ? { ...value, status: false } : value,
        );
      });
  },
});

export const getTrackers = (state: RootState) => state.trackers;

export default trackersSlice.reducer;
