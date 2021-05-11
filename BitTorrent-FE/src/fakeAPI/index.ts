import { Tracker } from 'redux/trackersSlice';
import { File } from 'redux/filesSlice';
import { User } from 'redux/usersSlice';

export function fetchTracers() {
  return new Promise<{ data: Tracker[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              id: '1',
              status: true,
              users: 2,
            },
            {
              id: '2',
              status: false,
              users: 2,
            },
          ],
        }),
      500,
    ),
  );
}

export function fetchAllUsers() {
  return new Promise<{ data: Tracker[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              id: '1',
              status: true,
              users: 2,
            },
            {
              id: '2',
              status: false,
              users: 2,
            },
          ],
        }),
      500,
    ),
  );
}

export function fetchFiles() {
  return new Promise<{ data: File[] }>((resolve) =>
    setTimeout(
      () =>
        resolve({
          data: [
            {
              id: '1',
              name: 'plik1.txt',
              size: '1024kb',
              users: [{ id: '1' }, { id: '2' }],
            },
            {
              id: '2',
              name: 'plik2.txt',
              size: '2048kb',
              users: [{ id: '1' }, { id: '2' }],
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
              id: '1',
              status: true,
              files: 2,
              trackers: 1,
            },
            {
              id: '2',
              status: false,
              files: 2,
              trackers: 2,
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
  fetchFiles,
  fetchUsers,
  toggleStatusById,
};

export default fakeAPI;
