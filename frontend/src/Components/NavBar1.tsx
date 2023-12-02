import React from 'react'
import logo from '../Images/ME_Logo.png'

function NavBar1() {
  return (
    <div>
        {/* Nav/Top section */}
        <div className="flex justify-between px-10 py-8 bg-[#2D2D2D]">

          <a href='/' style={{fontFamily : "Bebas Neue, cursive"}} className="text-white text-lg" >
            <img src={logo} className='w-24 2xl:w-32' />
          </a>
          
          <div className="flex gap-5 align-middle">
            <div>
              <a style={{fontFamily : "Bebas Neue, cursive"}} className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white" href='/SignUp'>Sign Up</a>
            </div>
            <div>
              <a style={{fontFamily : "Bebas Neue, cursive"}} className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white" href='/Login'>Login</a>
            </div>
          </div>

        </div>
    </div>
  )
}

export default NavBar1