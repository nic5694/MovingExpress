import React from 'react'
import UserHomeNav from '../Components/UserHomeNav'
import ScrollToTopBtn from '../Components/ScrollToTopBtn'

function UserHomePage() {
  return (
    <div>
      <div className='bg-[lightgray]'>
        <UserHomeNav />

        <ScrollToTopBtn />

        <div>User Home Page</div>
      </div>
    </div>
  )
}

export default UserHomePage