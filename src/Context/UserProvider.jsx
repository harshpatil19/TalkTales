import React, { useEffect, useState } from 'react'
import UserContext from './UserContext'

function userProvider({children}) {
    const[user,setUser]=useState({
        name: 'Harsh'
    })
    useEffect(()=>{
        setUser({
            name:'Harsh Patil'
        })
    },[])
  return (
    <UserContext.Provider value={user}>
        {children}
    </UserContext.Provider>
  )
}

export default userProvider
