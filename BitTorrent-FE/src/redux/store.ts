import { configureStore } from '@reduxjs/toolkit';
import trackersReducer from './trackersSlice';
import usersReducer from './usersSlice';

export const store = configureStore({
  reducer: {
    trackers: trackersReducer,
    users: usersReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
