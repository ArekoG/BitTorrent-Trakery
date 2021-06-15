import { Tracker } from 'redux/trackersSlice';
import { User } from 'redux/usersSlice';

export function fetchTracers() {
  return new Promise<{ data: Tracker[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              trackerId: '1',
              trackerName: 'Tracker1',
              trackerStatus: 'enable',
              numberOfUsers: 2,
            },
            {
              trackerId: '2',
              trackerName: 'Tracker2',
              trackerStatus: 'disable',
              numberOfUsers: 2,
            },
            {
              trackerId: '3',
              trackerName: 'Tracker3',
              trackerStatus: 'enable',
              numberOfUsers: 2,
            },
          ],
        }),
      500,
    ),
  );
}

export function fetchUsers() {
  return new Promise<{ data: User[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              userId: '1',
              userLogin: 'user1',
              userStatus: 'enable',
            },
            {
              userId: '2',
              userLogin: 'user2',
              userStatus: 'enable',
            },
          ],
        }),
      500,
    ),
  );
}

export function fetchAllUsers() {
  return new Promise<{ data: User[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              userId: '1',
              userLogin: 'user1',
              userStatus: 'enable',
            },
            {
              userId: '2',
              userLogin: 'user2',
              userStatus: 'enable',
            },
            {
              userId: '3',
              userLogin: 'user3',
              userStatus: 'disable',
            },
            {
              userId: '4',
              userLogin: 'user4',
              userStatus: 'enable',
            },
            {
              userId: '5',
              userLogin: 'user5',
              userStatus: 'enable',
            },
            {
              userId: '6',
              userLogin: 'user6',
              userStatus: 'enable',
            },
            {
              userId: '7',
              userLogin: 'user7',
              userStatus: 'enable',
            },
            {
              userId: '8',
              userLogin: 'user8',
              userStatus: 'disable',
            },
            {
              userId: '9',
              userLogin: 'user9',
              userStatus: 'enable',
            },
            {
              userId: '10',
              userLogin: 'user10',
              userStatus: 'disable',
            },
            {
              userId: '11',
              userLogin: 'user11',
              userStatus: 'enable',
            },
          ],
        }),
      500,
    ),
  );
}

export function toggleStatusById(id: string) {
  return new Promise((resolve) => setTimeout(() => resolve(id), 500));
}

const fakeAPI = {
  fetchTracers,
  fetchAllUsers,
  fetchUsers,
  toggleStatusById,
};

export default fakeAPI;
