import React, {useState, useEffect} from 'react';
import Login from './Login';

export default function App(){
  const [token, setToken] = useState(localStorage.getItem('mt_token'));

  useEffect(()=>{
    if (token) {
      // set default header for axios in the frontend (if using)
    }
  }, [token]);

  return (<div style={{padding:20}}>{token ? <div><h2>MediTrack Dashboard — authenticated</h2><p>Protected UI goes here.</p></div> : <Login onLogin={(t)=>setToken(t)}/>}</div>);
}
