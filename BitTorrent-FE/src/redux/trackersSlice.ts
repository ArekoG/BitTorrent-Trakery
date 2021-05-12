import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
// import fakeAPI from 'fakeAPI';
import api from 'api';
import { RootState } from './store';

export interface Tracker {
  trackerId: string;
  trackerName: string;
  trackerStatus: 'enable' | 'disable';
  numberOfUsers: number;
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
  const result = await api.get('/trackers');
  // const result = await fakeAPI.fetchTracers();

  return result.data;
});

export const activateTracker = createAsyncThunk(
  'trackers/activateTracker',
  async (trackerId: string) => {
    await api.post(`/trackers/${trackerId}/enable`);
    // await fakeAPI.toggleStatusById(trackerId);
    return trackerId;
  },
);

export const deactivateTracker = createAsyncThunk(
  'trackers/deactivateTracker',
  async (trackerId: string) => {
    await api.post(`/trackers/${trackerId}/disable`);
    // await fakeAPI.toggleStatusById(trackerId);
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
      .addCase(fetchTrackers.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(fetchTrackers.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = action.payload;
      })
      .addCase(activateTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(activateTracker.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(activateTracker.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.trackerId === action.payload ? { ...value, trackerStatus: 'enable' } : value,
        );
      })
      .addCase(deactivateTracker.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deactivateTracker.rejected, (state) => {
        state.isLoading = false;
      })
      .addCase(deactivateTracker.fulfilled, (state, action) => {
        state.isLoading = false;
        state.data = state.data.map((value) =>
          value.trackerId === action.payload ? { ...value, trackerStatus: 'disable' } : value,
        );
      });
  },
});

export const getTrackers = (state: RootState) => state.trackers;

export default trackersSlice.reducer;
