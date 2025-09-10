import React, {useEffect, useState} from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { signinRedirect, signinRedirectCallback, getUser, logout } from './auth';

function Home(){ return <div><h2>MediTrack</h2><p>Public landing</p></div>; }
function Patient(){ return <div><h3>Patient Portal</h3><p>Book appointments, view bills (protected)</p></div>; }
function Doctor(){ return <div><h3>Doctor Portal</h3><p>Manage appointments (protected)</p></div>; }
function Callback(){ useEffect(()=>{ signinRedirectCallback().then(()=> { window.location = '/'; }) },[]); return <div>Signing in...</div>; }

export default function App(){
  const [user, setUser] = useState(null);
  useEffect(()=>{ getUser().then(u=>setUser(u)); },[]);
  return (<BrowserRouter><div style={{padding:20}}><nav><Link to='/'>Home</Link> | <Link to='/patient'>Patient</Link> | <Link to='/doctor'>Doctor</Link> | {user? <button onClick={()=>logout()}>Logout</button> : <button onClick={()=>signinRedirect()}>Login</button>}</nav><Routes><Route path='/' element={<Home/>} /><Route path='/callback' element={<Callback/>} /><Route path='/patient' element={<Patient/>} /><Route path='/doctor' element={<Doctor/>} /></Routes></div></BrowserRouter>);
}
