import { UserManager, WebStorageStateStore } from 'oidc-client';

const settings = {
  authority: process.env.REACT_APP_KEYCLOAK_URL || 'http://localhost:8080/realms/meditrack',
  client_id: process.env.REACT_APP_CLIENT_ID || 'meditrack-client',
  redirect_uri: window.location.origin + '/callback',
  response_type: 'code',
  scope: 'openid profile email',
  userStore: new WebStorageStateStore({ store: window.localStorage })
};

const userManager = new UserManager(settings);

export async function signinRedirect() { return userManager.signinRedirect(); }
export async function signinRedirectCallback() { return userManager.signinRedirectCallback(); }
export function getUser() { return userManager.getUser(); }
export function logout() { return userManager.signoutRedirect(); }
