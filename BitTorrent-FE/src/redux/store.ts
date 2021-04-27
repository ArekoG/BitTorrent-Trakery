import { configureStore } from '@reduxjs/toolkit';
import trackersReducer from './trackersSlice';
import filesReducer from './filesSlice';
import usersReducer from './usersSlice';

export const store = configureStore({
  reducer: {
    trackers: trackersReducer,
    files: filesReducer,
    users: usersReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
