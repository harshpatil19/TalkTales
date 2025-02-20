import React, { useContext } from 'react'
import UserContext from '../../Context/UserContext'
import Base from '../../components/Base'

function ProfileInfo() {
  const user=useContext(UserContext)
  return (
  <Base>
  <div>ProfileInfo</div>
  <h1>Welcome {user.name}</h1>
  
  </Base>
  )
}

export default ProfileInfo
