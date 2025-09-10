import React, {useState} from 'react';
import axios from 'axios';

export default function Login({onLogin}){
  const [username,setUsername] = useState('');
  const [password,setPassword] = useState('');
  const [err,setErr] = useState(null);

  const submit = async (e) => {
    e.preventDefault();
    try {
      const resp = await axios.post(`/api/auth/login?username=${username}&password=${password}`);
      const token = resp.data.token;
      localStorage.setItem('mt_token', token);
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      onLogin(token);
    } catch (e) {
      setErr('Login failed');
    }
  }

  return (<div style={{padding:20}}><h3>Login</h3><form onSubmit={submit}><input placeholder='username' value={username} onChange={e=>setUsername(e.target.value)}/><br/><input placeholder='password' type='password' value={password} onChange={e=>setPassword(e.target.value)}/><br/><button type='submit'>Login</button></form>{err && <div style={{color:'red'}}>{err}</div>}</div>)
}
