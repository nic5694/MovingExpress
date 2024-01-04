import React from 'react'
import UserHomeNav from '../Components/UserHomeNav'
import ScrollToTopBtn from '../Components/ScrollToTopBtn'

function UserHomePage() {
  return (
    <div>
      <ScrollToTopBtn />

      <div className='bg-[lightgray]'>
        <UserHomeNav />

        <div>

        </div>
      </div>
      
    </div>
  )
}

export default UserHomePage